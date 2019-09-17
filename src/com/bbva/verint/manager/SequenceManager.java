package com.bbva.verint.manager;

import static java.lang.String.format;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bancomer.pia.dsmngr.DataSourceManager;
import com.bbva.verint.bean.Sequence;




/* Tabla requerida para esta clase
 *
	CREATE TABLE imx_sequence (
		seq_name VARCHAR(20) NOT NULL,
		seq_value NUMBER DEFAULT ,
	);
 *
 */

public class SequenceManager extends DataSourceManager {

	private static final Logger log = Logger.getLogger(SequenceManager.class);

	private static SequenceManager seqMangr = null;

//	private SequenceManager() {
//		super.init(TraeJndi.getStrJndi());
//	}

	public static SequenceManager getInstance() {
		if (seqMangr == null) {
			seqMangr = new SequenceManager();
		}

		return seqMangr;
	}

	public static int delete(Connection conn, String name) throws SQLException {

		int retVal = 0;
		PreparedStatement psDelete = null;

		try {
			psDelete = conn.prepareStatement("DELETE FROM imx_sequence WHERE seq_name = ?");

			psDelete.setString(1, name);

			retVal = psDelete.executeUpdate();
		} finally {
			if (psDelete != null)
				try {
					psDelete.close();
				} catch (SQLException e) {
					log.warn("Cerrando PreparedStatement", e);
				}
		}

		return retVal;
	}

	public static Sequence insert(Connection conn, int cd_aplicacion, int value) throws SQLException {

		Sequence retVal = null;
		PreparedStatement psInsert = null;

		try {
			psInsert = conn.prepareStatement("INSERT INTO GORAPR.TATN006_SEQUENCE (CD_APLICACION, CD_VALUE) VALUES (?, ?)");

			psInsert.setInt(1, cd_aplicacion);
			psInsert.setInt(2, value);

			if (psInsert.executeUpdate() == 1)
				retVal = new Sequence(cd_aplicacion, value);
		} finally {
			if (psInsert != null)
				try {
					psInsert.close();
				} catch (SQLException e) {
					log.warn("Cerrando PreparedStatement", e);
				}
		}

		return retVal;
	}

	public static Sequence update(Connection conn, int cd_aplicacion, int cd_value) throws SQLException {

		Sequence retVal = null;
		PreparedStatement psUpdate = null;

		try {
			psUpdate = conn.prepareStatement("UPDATE  GORAPR.TATN006_SEQUENCE SET CD_VALUE = ? WHERE CD_APLICACION = ?");

			psUpdate.setInt(1, cd_value);
			psUpdate.setInt(2, cd_aplicacion);

			if (psUpdate.executeUpdate() == 1)
				retVal = new Sequence(cd_aplicacion, cd_value);
		} finally {
			if (psUpdate != null)
				try {
					psUpdate.close();
				} catch (SQLException e) {
					log.warn("Cerrando PreparedStatement", e);
				}
		}

		return retVal;
	}


	public static int maxValue(Connection conn, String tableName, String columnName, int cd_aplicacion) throws SQLException {

		int retVal = 1;
		ResultSet rs = null;
		
		try {
			String query = format("SELECT MAX(%s) FROM %s WHERE CD_APLICACION  = "+cd_aplicacion+"", columnName, tableName);
			log.info("Tahis temp: " + query);
			rs = conn.createStatement().executeQuery(query);
			if (rs.next())
				retVal = rs.getInt(1);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				log.warn("Cerrando ResultSet", e);
			}

			rs = null;
		}

		return retVal;
	}

	public int currentValue(String name) throws SQLException {

		int retVal = 1;
		Connection conn = null;
		PreparedStatement psSelect = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			synchronized (seqMangr) {
				psSelect = conn.prepareStatement("SELECT seq_value FROM imx_sequence WHERE seq_name = ?");

				psSelect.setString(1, name);

				rs = psSelect.executeQuery();
				if (rs.next())
					retVal = rs.getInt(1);
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				log.warn("Cerrando ResultSet", e);
			}

			try {
				if (psSelect != null)
					psSelect.close();
			} catch (Exception e) {
				log.warn("Cerrando PreparedStatement", e);
			}

			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				log.warn("Cerrando conexion a base de datos", e);
			}

			rs = null;
			psSelect = null;
			conn = null;
		}

		return retVal;
	}

	public int nextValue(Connection conn, int string) throws SQLException {

		int retVal = -1;
		PreparedStatement psUpdate = null, psSelect = null;
		ResultSet rs = null;

		try {
//			conn = getConnection();
			
//			conn = AlmacenaDocto.Buscaconexion();
//			if ("P".equals(ParametrosBecas.AMBIENTE)) {
//				conn = getConnectionStatic();
//			}else if("T".equals(ParametrosBecas.AMBIENTE)){
//				conn 				= AlmacenaDocto.Buscaconexion();
//			}
			// Bloqueamos el registro incrementando al nuevo valor
			psUpdate = conn.prepareStatement("UPDATE GORAPR.TATN006_SEQUENCE SET CD_VALUE = CD_VALUE + 1 WHERE CD_APLICACION = ?");
			psUpdate.setInt(1, string);

			psUpdate.executeUpdate();
			// Recuperamos el nuevo valor
			psSelect = conn.prepareStatement("SELECT CD_VALUE FROM GORAPR.TATN006_SEQUENCE WHERE CD_APLICACION = ?");
			psSelect.setInt(1, string);

			rs = psSelect.executeQuery();
			if (rs.next())
				retVal = rs.getInt("cd_value");

			conn.commit();
		} catch (Exception exc) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				log.warn("En rollback", e);
			}
			throw new SQLException(exc);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				log.warn("Cerrando ResultSet", e);
			}

			try {
				if (psSelect != null)
					psSelect.close();
			} catch (Exception e) {
				log.warn("Cerrando PreparedStatement", e);
			}

			try {
				if (psUpdate != null)
					psUpdate.close();
			} catch (Exception e) {
				log.warn("Cerrando PreparedStatement", e);
			}

			

			rs = null;
			psSelect = null;
			psUpdate = null;
			conn = null;
		}

		return retVal;
	}

	public int nextValue(Connection conn, String tableName, String columnName, String string) throws SQLException {

		int maxValue = -1;
		
		try {
			synchronized (seqMangr) {
				maxValue = nextValue(conn,string);
				// Si no existe la secuencia, se crea
				if (maxValue == -1) {
//					conn = getConnection()
					
					// Busca el maximo de tableName en columnName
					maxValue = maxValue(conn, tableName, columnName, string);
					// Se crea la nueva secuencia
					insert(conn, string, maxValue);
					// Se hacen permanentes los cambios
					conn.commit();
					// Se recupera el siguiente valor de la secuencia
					maxValue = nextValue(conn,string);
				}
			}
		} catch (Exception exc) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				log.warn("En Rollback", e);
			}
			throw new SQLException(exc);
		} finally {
//			if (conn != null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					log.warn("Cerrando conexion a base de datos", e);
//				}

//			conn = null;
		}

		return maxValue;
	}

	private void insert(Connection conn, String string, int maxValue) {
		// TODO Auto-generated method stub
		
	}

	private int maxValue(Connection conn, String tableName, String columnName, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int nextValue(Connection conn, String string) {
		// TODO Auto-generated method stub
		return 0;
	}
}
