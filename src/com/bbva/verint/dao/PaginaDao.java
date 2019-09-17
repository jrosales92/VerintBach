package com.bbva.verint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bbva.verint.bean.CfdiBean;
import com.bbva.verint.bean.VerintBean;

public class PaginaDao {

	private String sysdate;
	private String VERINT;


	public void idPagina(Connection conn, VerintBean verint , Statement st) throws SQLException{

		PreparedStatement ps = null;
		ResultSet rs = null;

		String query ="SELECT MAX(CD_PAGINA) AS PAGINA FROM GORAPR.TATN005_PAGINA WHERE CD_VERSION = ? AND CD_DOCUMENTO = ? AND CD_EXPEDIENTE = ? AND NB_ARCHIVO = ?";
		try{
			ps = conn.prepareStatement(query);
			ps.setInt(1, verint.getTypeOperation());
			ps.setInt(2, verint.getSize());
			ps.setInt(3, verint.getProduct());
			ps.setString(4, verint.getContractId());

			rs = ps.executeQuery();
			if(rs.next()){
				verint.setNumeroPagina(rs.getInt("pagina") +1);
				createPagina(conn, verint, st);

				// TODO: handle exception
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			rs=null;
			ps = null;
		}


	}


	public void createPagina (Connection conn, VerintBean bean, Statement st) throws SQLException{


		bean.setIdCarpeta(1);
		bean.setFolioDigitalizacion("VERINT@G"+bean.getIdGabinete()+"C"+bean.getIdCarpeta()+"D"+bean.getIdDocumento()+"V"+bean.getIdVersion()+"P"+bean.getNumeroPagina());
		System.out.println(bean.getFolioDigitalizacion());
		String query = "INSERT INTO GORAPR.TATN005_PAGINA (CD_PAGINA, CD_VERSION,CD_DOCUMENTO, CD_EXPEDIENTE, CD_APLICACION , "
				+ "CD_FOLIO, NB_ARCHIVO, NU_SIZE, NB_EXTENSION, NB_SHA1, TM_DIGITALIZACION , CD_USUARIO)  VALUES "
				+ " ( "+ bean.getNumeroPagina()+" , "+bean.getIdVersion()+" , "+bean.getIdDocumento()+" , "+bean.getIdGabinete()+" , "
				+ " "+ bean.getTituloAplicacion()+" , '"+ bean.getFolioDigitalizacion()+"' ,"
				+ " '"+bean.getNameRecord()+"' , "+bean.getSize()+", '"+bean.getExt().toLowerCase()+"' , '"+bean.getSha1n()+"', '+sysdate+' , '+VERINT+')";
		System.out.println(query);
		st.addBatch(query);



	}
}
