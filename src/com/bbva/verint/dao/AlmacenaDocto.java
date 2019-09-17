package com.bbva.verint.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bancomer.pia.dsmngr.DataSourceManager;
import com.bbva.verint.archiving.MapArchiving;
import com.bbva.verint.bean.VerintBean;
import com.bbva.verint.bean.VerintBean;
import com.bbva.verint.parametros.ParametrosVerint;
import com.bbva.verint.parametros.ParametrosVerint;
import com.syc.rig.client.RigClient;


public class AlmacenaDocto extends DataSourceManager {
	private static Logger log = Logger.getLogger(AlmacenaDocto.class);
	
	public JSONObject addFile (VerintBean verint) throws Exception{
		log.info("ADDFILE ");
		Connection conn 	= null;
		
		ExpedienteDao exp   = new ExpedienteDao();
		if ("P".equals(ParametrosVerint.AMBIENTE)) {
			conn = getConnectionStatic();
		}else if("T".equals(ParametrosVerint.AMBIENTE)){
			conn 				= Buscaconexion();
		}
		try{
		log.info("CEANDO EXPEDIENTE");
		Statement st 	= conn.createStatement();
		verint 			= exp.idExpedinte(conn, verint, st);
		
		if (verint.getIdGabinete() != 0 && verint.getIdDocumento()!= 0 && verint.getNumeroPagina() != 0  && verint.getIdVersion() != 0){
			Map<String, Object> metadata 	= new HashMap<String, Object>();
			MapArchiving m 					= new MapArchiving();
			metadata 						=  m.generaMetadata(verint);
			
		}else {
			conn.rollback();
			log.error("Error al escribir en Base de Datos");
		}
			
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Connection Buscaconexion() throws Exception{		
		log.info("Conexion D ------ ");
		Connection conn = null; 
		Class.forName("oracle.jdbc.OracleDriver") ;
		//
		//		PRODUCCION
		//		String cadena="jdbc:oracle:thin:@150.100.151.112:1521/LMSBPMX1";
		//		String user= "ZDBSLMS";
		//		String pass= "ZDBSLMSX12";
		//		
		//		TEST
//		String cadena="jdbc:oracle:thin:@150.50.102.249:1521:lmsbdmx1";
//		String user= "zdbslms";
//		String pass= "xfirma123";
		
//		TEST ATHAN MSD
		String cadena="jdbc:oracle:thin:@150.50.102.249:1521:bmatnd01";
		String user= "ZATNMXD1";
		String pass= "zaqwsX019";
		conn = DriverManager.getConnection(cadena,user, pass);
				
		//CALIDAD

//		String cadena="jdbc:oracle:thin:@150.50.103.198:1521/BMATNC01";
//		String user= "ZATNMXC1";
//		String pass= "zaqwsx019";
//		conn = DriverManager.getConnection(cadena,user, pass);

		conn.setAutoCommit(false);
		return conn;
	}

	private JSONObject GetJson(String  obj){

		JSONObject	regreso = new JSONObject(obj);

		return regreso;
	}


}
