package com.bbva.verint.bean;

import java.text.DateFormat;
import java.util.Date;

public class VerintBean {
	
	private String tituloAplicacion;
	private long idGabinete;
	private int idDocumento;
	private int idCarpeta;
	private int idVersion;
	private int numeroPagina;
	private String folioDigitalizacion;
	private String keyIntervener;
	private int documentKey;
	private String contactIdVerint;
	private Date dateTime;
	private int typeOperation;
	private String typeMatrix;
	private String typeDocument;
	private String ext;
	private int size;
	private int product;
	private String cr;
	private String customerId;
	private String funtion;
	private String typeTransact;
	private String sha1n;
	private String descriptionDocument;
	private String service;
	private String signatureAdviser;
	private String contractId;
	private String nameRecord;
	private String idCertificacion;
	private int phaseOperation;
	
	public VerintBean() {
		super();
	}

	public VerintBean(String tituloAplicacion, long idGabinete, int idDocumento, int idCarpeta, int idVersion,
			int numeroPagina, String folioDigitalizacion, String keyIntervener, int documentKey, String contactIdVerint,
			Date dateTime, int typeOperation, String typeMatrix, String typeDocument, String ext, int size, int product,
			String cr, String customerId, String funtion, String typeTransact, String sha1n, String descriptionDocument,
			String service, String signatureAdviser, String contractId, String nameRecord, String idCertificacion,
			int phaseOperation) {
		super();
		this.tituloAplicacion = tituloAplicacion;
		this.idGabinete = idGabinete;
		this.idDocumento = idDocumento;
		this.idCarpeta = idCarpeta;
		this.idVersion = idVersion;
		this.numeroPagina = numeroPagina;
		this.folioDigitalizacion = folioDigitalizacion;
		this.keyIntervener = keyIntervener;
		this.documentKey = documentKey;
		this.contactIdVerint = contactIdVerint;
		this.dateTime = dateTime;
		this.typeOperation = typeOperation;
		this.typeMatrix = typeMatrix;
		this.typeDocument = typeDocument;
		this.ext = ext;
		this.size = size;
		this.product = product;
		this.cr = cr;
		this.customerId = customerId;
		this.funtion = funtion;
		this.typeTransact = typeTransact;
		this.sha1n = sha1n;
		this.descriptionDocument = descriptionDocument;
		this.service = service;
		this.signatureAdviser = signatureAdviser;
		this.contractId = contractId;
		this.nameRecord = nameRecord;
		this.idCertificacion = idCertificacion;
		this.phaseOperation = phaseOperation;
	}

	public String getTituloAplicacion() {
		return tituloAplicacion;
	}

	public void setTituloAplicacion(String tituloAplicacion) {
		this.tituloAplicacion = tituloAplicacion;
	}

	public long getIdGabinete() {
		return idGabinete;
	}

	public void setIdGabinete(long idGabinete) {
		this.idGabinete = idGabinete;
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public int getIdCarpeta() {
		return idCarpeta;
	}

	public void setIdCarpeta(int idCarpeta) {
		this.idCarpeta = idCarpeta;
	}

	public int getIdVersion() {
		return idVersion;
	}

	public void setIdVersion(int idVersion) {
		this.idVersion = idVersion;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public String getFolioDigitalizacion() {
		return folioDigitalizacion;
	}

	public void setFolioDigitalizacion(String folioDigitalizacion) {
		this.folioDigitalizacion = folioDigitalizacion;
	}

	public String getKeyIntervener() {
		return keyIntervener;
	}

	public void setKeyIntervener(String keyIntervener) {
		this.keyIntervener = keyIntervener;
	}

	public int getDocumentKey() {
		return documentKey;
	}

	public void setDocumentKey(int documentKey) {
		this.documentKey = documentKey;
	}

	public String getContactIdVerint() {
		return contactIdVerint;
	}

	public void setContactIdVerint(String contactIdVerint) {
		this.contactIdVerint = contactIdVerint;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(int typeOperation) {
		this.typeOperation = typeOperation;
	}

	public String getTypeMatrix() {
		return typeMatrix;
	}

	public void setTypeMatrix(String typeMatrix) {
		this.typeMatrix = typeMatrix;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	public String getCr() {
		return cr;
	}

	public void setCr(String cr) {
		this.cr = cr;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFuntion() {
		return funtion;
	}

	public void setFuntion(String funtion) {
		this.funtion = funtion;
	}

	public String getTypeTransact() {
		return typeTransact;
	}

	public void setTypeTransact(String typeTransact) {
		this.typeTransact = typeTransact;
	}

	public String getSha1n() {
		return sha1n;
	}

	public void setSha1n(String sha1n) {
		this.sha1n = sha1n;
	}

	public String getDescriptionDocument() {
		return descriptionDocument;
	}

	public void setDescriptionDocument(String descriptionDocument) {
		this.descriptionDocument = descriptionDocument;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSignatureAdviser() {
		return signatureAdviser;
	}

	public void setSignatureAdviser(String signatureAdviser) {
		this.signatureAdviser = signatureAdviser;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getNameRecord() {
		return nameRecord;
	}

	public void setNameRecord(String nameRecord) {
		this.nameRecord = nameRecord;
	}

	public String getIdCertificacion() {
		return idCertificacion;
	}

	public void setIdCertificacion(String idCertificacion) {
		this.idCertificacion = idCertificacion;
	}

	public int getPhaseOperation() {
		return phaseOperation;
	}

	public void setPhaseOperation(int phaseOperation) {
		this.phaseOperation = phaseOperation;
	}

	@Override
	public String toString() {
		return "VerintBean [tituloAplicacion=" + tituloAplicacion + ", idGabinete=" + idGabinete + ", idDocumento="
				+ idDocumento + ", idCarpeta=" + idCarpeta + ", idVersion=" + idVersion + ", numeroPagina="
				+ numeroPagina + ", folioDigitalizacion=" + folioDigitalizacion + ", keyIntervener=" + keyIntervener
				+ ", documentKey=" + documentKey + ", contactIdVerint=" + contactIdVerint + ", dateTime=" + dateTime
				+ ", typeOperation=" + typeOperation + ", typeMatrix=" + typeMatrix + ", typeDocument=" + typeDocument
				+ ", ext=" + ext + ", size=" + size + ", product=" + product + ", cr=" + cr + ", customerId="
				+ customerId + ", funtion=" + funtion + ", typeTransact=" + typeTransact + ", sha1n=" + sha1n
				+ ", descriptionDocument=" + descriptionDocument + ", service=" + service + ", signatureAdviser="
				+ signatureAdviser + ", contractId=" + contractId + ", nameRecord=" + nameRecord + ", idCertificacion="
				+ idCertificacion + ", phaseOperation=" + phaseOperation + "]";
	}

	

}
