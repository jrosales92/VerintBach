package com.bbva.verint.dao;


import static java.lang.String.format;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.bancomer.pia.dsmngr.DataSourceManager;
import com.bbva.verint.bean.CfdiBean;
import com.bbva.verint.bean.VerintBean;
import com.bbva.verint.manager.SequenceManager;
import com.bbva.verint.parametros.ParametrosVerint;
import com.bbva.verint.parametros.ParametrosVerint;


public class ExpedienteDao  extends DataSourceManager{

	private static Logger log = Logger.getLogger(ExpedienteDao.class);
	
	public VerintBean idExpedinte (Connection conn, VerintBean verint , Statement st) throws SQLException{

		PreparedStatement ps = null;
		ResultSet rs = null;
		DocumentoDao doc = new DocumentoDao();
		int idAplicacion = 8;

		String query = "SELECT CD_EXPEDIENTE FROM GORAPR.TATN002_EXPEDIENTE  WHERE CD_CLIENTE = ? AND CD_APLICACION = ? ";
		try {

			ps = conn.prepareStatement(query);
			ps.setString(1, verint.getCustomerId());
			ps.setInt(2, idAplicacion);

			rs = ps.executeQuery();
			if(rs.next()){
				verint.setIdGabinete(rs.getInt("CD_EXPEDIENTE"));
			}else{
				secuenceExpediente(conn, verint, st); 
			}

			log.info("Expediente " + verint.getIdGabinete());
			verint =  doc.idDocumento(conn, verint, st) ;	
			st.executeBatch();
		} catch (SQLException e) {

			e.printStackTrace();
			conn.rollback();
			verint = null;
		}finally {
			if(rs !=null)rs.close();
			if(ps != null) ps.close();
			rs = null;
			ps = null;
		}

		return verint;

	}


	public int secuenceExpediente (Connection conn, VerintBean verint, Statement st) throws SQLException{
		int idExpediente = -1;

		idExpediente = 	nextValExpediente(conn, verint.getTituloAplicacion());
		verint.setIdGabinete(idExpediente);
		createExpediente(conn, verint, st);

		return idExpediente;

	}
	
	public int getIdAplicacion ( String apliacion) throws Exception{
		Connection conn = null;
		if ("P".equals(ParametrosVerint.AMBIENTE)) {
			conn = getConnectionStatic();
		}else if("T".equals(ParametrosVerint.AMBIENTE)){
			conn 				= AlmacenaDocto.Buscaconexion();
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		DocumentoDao doc = new DocumentoDao();
		int cd_aplicacion = -1;

		String query = "SELECT CD_APLICACION FROM GORAPR.TATN001_APLICACION WHERE NB_APLICACION = ? ";
		try {

			ps = conn.prepareStatement(query);
			ps.setString(1, apliacion);
		

			rs = ps.executeQuery();
			if(rs.next()){
				cd_aplicacion  = rs.getInt("CD_APLICACION") ;
			}

			
	
		} catch (SQLException e) {

			e.printStackTrace();
			conn.rollback();
		
		}finally {
			if (conn!= null)conn.close();
			if(rs !=null)rs.close();
			if(ps != null) ps.close();
			rs = null;
			ps = null;
			conn = null;
		}


		return cd_aplicacion;

	}


	private static synchronized int nextValExpediente (Connection conn, String string) throws SQLException{			
		String  titulo_aplicacion = "GORAPR.TATN002_EXPEDIENTE";
		return SequenceManager.getInstance().nextValue(conn, format(titulo_aplicacion));

	}


	public int createExpediente (Connection conn, VerintBean verint, Statement st) throws SQLException{
		int insert = 0;
		String query =  "INSERT INTO GORAPR.TATN002_EXPEDIENTE (CD_EXPEDIENTE,CD_APLICACION,CD_CLIENTE,CD_CUENTA,CD_CR) VALUES "
				+ " ( "+verint.getIdGabinete()+ " , "+verint.getTituloAplicacion()+" ,'N/A' , "
				+ "'"+verint.getCustomerId()+"', '0')";

		st.addBatch(query);
		return insert;
	}


	public String getTituloAplicacion(String keyIntervener) {
		// TODO Auto-generated method stub
		return null;
	}

}
