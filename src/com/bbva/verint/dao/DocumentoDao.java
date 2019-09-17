package com.bbva.verint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bbva.verint.bean.CfdiBean;
import com.bbva.verint.bean.VerintBean;

public class DocumentoDao {


	public VerintBean idDocumento(Connection conn, VerintBean verint, Statement st) throws SQLException{

		PreparedStatement ps = null;
		ResultSet rs = null;
		VersionDao version = new VersionDao();

		String query ="SELECT CD_DOCUMENTO FROM GORAPR.TATN003_DOCUMENTO  WHERE CD_APLICACION = ?  AND  CD_EXPEDIENTE =  ? AND TP_DOC = ?  AND NB_BECA =  ? AND NB_GENERACION = ? AND TX_DESCRIPCION = ? ";
		try{
			ps = conn.prepareStatement(query);
			ps.setString(1, verint.getTituloAplicacion());
			ps.setLong(2, verint.getIdGabinete());
			ps.setLong(3, verint.getDocumentKey());
			ps.setString(4, verint.getTypeMatrix());
			ps.setString(5, verint.getContactIdVerint());
			ps.setString(6, verint.getSha1n());
			PaginaDao  pag = new PaginaDao();
			rs = ps.executeQuery();
			if(rs.next()){
				
				verint.setIdDocumento(rs.getInt("cd_documento"));
				verint.setIdVersion(version.maxVersion(conn, verint.getIdGabinete(), verint.getIdDocumento(), verint.getTituloAplicacion())+1);   
				version.createVersion(conn,verint, st);
				verint.setNumeroPagina(1);	
				pag.createPagina(conn, verint,st);	

			}else{
				verint.setIdDocumento(maxDoc(conn, verint.getIdGabinete(), verint.getTituloAplicacion())); 
				int insert = createDocumento(conn, verint,st);
				verint.setIdVersion( 1);  ;
				version.createVersion(conn, verint, st);
				verint.setNumeroPagina(1);	
				pag.createPagina(conn, verint,st);	
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(ps!=null) ps.close();
			if(rs!=null)rs.close();
			ps = null;
			rs  = null;
		}


		return verint;
	}


	private int maxDoc(Connection conn, long idGabinete, String tituloAplicacion) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int maxDoc(Connection conn, long idExpediente, int idAplicacion) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		int idDocumento = -1;
		String query ="SELECT MAX(CD_DOCUMENTO) AS DOCUMENTO FROM GORAPR.TATN003_DOCUMENTO  WHERE CD_EXPEDIENTE = ?  AND CD_APLICACION = ?";
		try{
			ps = conn.prepareStatement(query);
			ps.setLong(1, idExpediente);
			ps.setInt(2, idAplicacion);
			rs = ps.executeQuery();
			if(rs.next()){
				idDocumento = rs.getInt("documento") + 1;
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(ps!=null) ps.close();
			if(rs!=null)rs.close();
			ps = null;
			rs  = null;
		}
		return idDocumento;
	}



	public int createDocumento (Connection conn, VerintBean bean , Statement st) throws SQLException{


		String query = "INSERT INTO  GORAPR.TATN003_DOCUMENTO (CD_DOCUMENTO, CD_EXPEDIENTE,CD_APLICACION,NB_DOCUMENTO,TP_DOC, NB_BECA, NB_GENERACION, TX_DESCRIPCION) "			
				+ " VALUES  ( "+bean.getIdDocumento()+" , "+bean.getIdGabinete()+" , "+bean.getTituloAplicacion()+" , '"+bean.getDocumentKey()+"' , '"+ bean.getTypeMatrix()+"' , '"+bean.getContactIdVerint()+"' , '"+bean.getSha1n()+"')";

		st.addBatch(query);
//		System.out.println(query);


		return 1;

	}

}



