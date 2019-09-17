package com.bbva.verint.archiving;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bbva.verint.bean.CfdiBean;
import com.bbva.verint.bean.VerintBean;

public class MapArchiving {
	
	public Map<String, Object>  generaMetadata (VerintBean verint){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String date = sdf.format(new Date()); 
		 
	
		Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("V", "VR");                  //Titulo aplicacion
		metadata.put("getKeyIntervener", verint.getKeyIntervener());
		metadata.put("DocumentKey", verint.getDocumentKey());		//clave del Documento
		metadata.put("ContactIdVerint", verint.getContactIdVerint());     //id GAbinete
//		metadata.put("fd", date);            //fecha
		metadata.put("TypeOperation", verint.getTypeOperation());      //id Carpeta
		metadata.put("TypeMatrix", verint.getTypeMatrix());    //idDocumentos
		metadata.put("TypeDocument", verint.getTypeDocument());      //id version
		metadata.put("Ext", verint.getExt());      //id pagina
		metadata.put("Size", verint.getSize());      //folio Digitalizacion  
		metadata.put("Product",(verint.getProduct()));	//clave del Documento
		metadata.put("cR", verint.getCr());			//CR
		metadata.put("CustomerId", verint.getCustomerId());                  //usuario
		metadata.put("Funtion", verint.getFuntion());                  //extension
		metadata.put("TypeTransact", verint.getTypeTransact()); //tamaño archivo
		metadata.put("sha1N", verint.getSha1n());   //Digestion SHA1 del Archivo
		metadata.put("DescriptionDocument", verint.getDescriptionDocument());
		metadata.put("Service", verint.getService());
		metadata.put("SignatureAdviser", verint.getSignatureAdviser());
		metadata.put("ContractId", verint.getContractId());
		metadata.put("NameRecord", verint.getNameRecord());
		metadata.put("getIdCertificacion", verint.getIdCertificacion());
		metadata.put("PhaseOperation", verint.getPhaseOperation());

	
		

		return metadata;
		
	}
	
	 public static String quitaSignos(String cadena) {
		 String response = null;
		 if (cadena != null)
		 response = cadena.replaceAll("[^\\dA-Za-z. ]", "");
		 return response;
		 }

}
