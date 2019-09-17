package com.bbva.verint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bbva.verint.bean.CfdiBean;
import com.bbva.verint.bean.VerintBean;

public class VersionDao {
	
	
	public int createVersion (Connection conn, VerintBean verint, Statement st) throws SQLException{	

		String query = "INSERT INTO GORAPR.TATN004_VERSION (CD_VERSION, CD_DOCUMENTO, CD_EXPEDIENTE,CD_APLICACION) VALUES "
				+ " ( "+verint.getIdVersion()+" , "+verint.getIdDocumento()+" , "+verint.getIdGabinete()+" , "+verint.getTituloAplicacion()+" )";
		
		st.addBatch(query);
	
	
		return 1;
		
		
		
	}
	
	public int maxVersion(Connection conn, long idExpediente, int cd_documento, String cd_aplicacion ) throws SQLException{

		PreparedStatement ps = null;
		ResultSet rs = null;
		int version = -1;
		String query ="SELECT MAX(CD_VERSION) AS VERSION FROM GORAPR.TATN004_VERSION  WHERE CD_EXPEDIENTE = ?  AND CD_DOCUMENTO = ? AND CD_APLICACION = ?";
		try{
			ps = conn.prepareStatement(query);
			ps.setLong(1, idExpediente);
			ps.setInt(2, cd_documento);
			ps.setString(3, cd_aplicacion);
			rs = ps.executeQuery();
			if(rs.next()){
				version = rs.getInt("version");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(ps!=null) ps.close();
			if(rs!=null) rs.close();
			ps = null;
			rs = null;
		}
		return version;
	}



}
