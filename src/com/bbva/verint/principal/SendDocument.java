package com.bbva.verint.principal;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bbva.verint.bean.VerintBean;
import com.bbva.verint.dao.AlmacenaDocto;
import com.bbva.verint.dao.ExpedienteDao;

public class SendDocument {

	private static Logger log = Logger.getLogger(SendDocument.class);

	private enum atributosFB {
		titulo_aplicacion, keyIntervener, documentKey, contactIdVerint, dateTime, typeOperation, typeMatrix, typeDocument, ext, size, product, customerId, funtion, typeTransact, sha1n, descriptionDocument, service, signatureAdviser, contractId, nameRecord, idCertification, phaseOperation;
	};

	public static JSONObject almacenaInformacion(JSONObject informacion) {

		String titulo_aplicacion, keyIntervener, documentKey, contactIdVerint, dateTime, typeOperation, typeMatrix,
				typeDocument, ext, size, product, cr, customerId, funtion, typeTransact, sha1n, descriptionDocument,
				service, signatureAdviser, contractId, nameRecord, idCertification, phaseOperation;

		JSONObject respuesta = new JSONObject();
		JSONObject atributo = new JSONObject();
		JSONArray atributos = new JSONArray();
		JSONObject codigoError = new JSONObject();
		JSONObject codigoExito = new JSONObject();

		// log.info("RECIBIENDO ARCHIVO-METADATA VERINT");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			titulo_aplicacion = "VR";
			keyIntervener = (String) informacion.get("keyIntervener");
			documentKey = (String) informacion.get("documentKey");
			contactIdVerint = (String) informacion.get("contactIdVerint");
			dateTime = (String) informacion.get("dateTime");
			typeOperation = (String) informacion.get("typeOperation");
			typeMatrix = (String) informacion.get("typeMatrix");
			typeDocument = (String) informacion.get("tipeDocument");
			ext = (String) informacion.get("ext");
			size = (String) informacion.get("size");
			product = (String) informacion.get("product");
			cr = (String) informacion.get("cR");
			customerId = (String) informacion.get("customerId");
			funtion = (String) informacion.get("funtion");
			typeTransact = (String) informacion.get("typeTransact");
			sha1n = (String) informacion.get("sha1n");
			descriptionDocument = (String) informacion.get("descriptionDocument");
			service = (String) informacion.get("service");
			signatureAdviser = (String) informacion.get("signatureAdviser");
			contractId = (String) informacion.get("contractId");
			nameRecord = (String) informacion.get("nameRecord");
			idCertification = (String) informacion.get("idCertification");
			phaseOperation = (String) informacion.get("phaseOperation");

			ExpedienteDao exp = new ExpedienteDao();
			VerintBean verint = new VerintBean();

			verint.setTituloAplicacion("VERINT");
			for (int i = 0; i < atributos.length(); i++) {
				atributo = atributos.getJSONObject(i);
				log.info("Insertando al map (CAMPO): " + atributo.getString("campo").toString() + ", (VALOR): "
						+ atributo.getString("valor").toString());

				map.put(atributo.getString("campo").toString(), atributo.getString("valor").toString());
			}

			log.info("LLENANDO BEAN.VERINT");

			verint.setKeyIntervener(keyIntervener);
			verint.setDocumentKey(Integer.parseInt(documentKey));
			verint.setContactIdVerint(contactIdVerint);
			// verint.setDateTime((new SimpleDateFormat("dd/MM/yyyyz/hh:mm:ss").parse(dateTime)));
			// verint.setDateTime(Date.valueOf(dateTime));
			verint.setTypeOperation(Integer.parseInt(typeOperation));
			verint.setTypeMatrix(typeMatrix);
			verint.setTypeDocument(typeDocument);
			verint.setExt(ext);
			verint.setSize(Integer.parseInt(size));
			verint.setProduct(Integer.parseInt(product));
			verint.setCr(cr);
			verint.setCustomerId(customerId);
			verint.setFuntion(funtion);
			verint.setTypeTransact(typeTransact);
			verint.setSha1n(sha1n);
			verint.setDescriptionDocument(descriptionDocument);
			verint.setService(service);
			verint.setSignatureAdviser(signatureAdviser);
			verint.setContractId(contractId);
			verint.setNameRecord(nameRecord);
			verint.setIdCertificacion(idCertification);
			verint.setPhaseOperation(Integer.parseInt(phaseOperation));

			respuesta = new AlmacenaDocto().addFile(verint);
		} catch (NullPointerException e) {
			log.info("Error al procesar la informacion: " + e.getCause());
			codigoError.put("codigo", "0");
			codigoError.put("mensaje", "Parametros nulos");
			codigoError.put("causa", "Parametros nulos");

			respuesta.put("exito", codigoExito);
			respuesta.put("error", codigoError);
		} catch (JSONException e) {
			log.info("Error al procesar la informacion: " + e.getMessage());
			codigoError.put("codigo", "0");
			codigoError.put("mensaje", e.getMessage());
			codigoError.put("causa", "Error en la estructura del JSON");

			respuesta.put("exito", codigoExito);
			respuesta.put("error", codigoError);
		} catch (Exception e) {
			e.printStackTrace();
			codigoError.put("codigo", "0");
			codigoError.put("mensaje", "Error");
			codigoError.put("causa", e.getMessage());

			respuesta.put("exito", codigoExito);
			respuesta.put("error", codigoError);
		}

		return respuesta;

	}

}
