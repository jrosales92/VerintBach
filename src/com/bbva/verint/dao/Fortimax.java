package com.bbva.verint.dao;

public class Fortimax {

	private String titApp = new String();
	
	private int idGab   = -1;
	private int idCarp  = -1;
	private int idDoc   = -1;
	private int version = -1;
	
	public Fortimax(String titApp, int idGab, int idCarp, int idDoc, int version) {
		this.titApp  = titApp;
		this.idGab   = idGab;
		this.idCarp  = idCarp;
		this.idDoc   = idDoc;
		this.version = version;
	}
	
	public Fortimax(String nodeId) {

		int maxLen = nodeId.length() - 1;
		int lastPos = maxLen;

		if (hasNotNumbers(nodeId)) {
			titApp = nodeId;
			return;
		}

		try {
			for (int i = maxLen; i >= 0; i--) {
				if ('V' == nodeId.charAt(i)) {
					lastPos = i;
					version = Integer.parseInt(nodeId.substring(i + 1));
				} else if ('D' == nodeId.charAt(i)) {
					if (maxLen == lastPos)
						idDoc = Integer.parseInt(nodeId.substring(i + 1));
					else
						idDoc = Integer.parseInt(nodeId.substring(i + 1, lastPos));
					lastPos = i;
				} else if ('C' == nodeId.charAt(i)) {
					if (maxLen == lastPos)
						idCarp = Integer.parseInt(nodeId.substring(i + 1));
					else
						idCarp = Integer.parseInt(nodeId.substring(i + 1, lastPos));
					lastPos = i;
				} else if ('G' == nodeId.charAt(i)) {
					if (maxLen == lastPos)
						idGab = Integer.parseInt(nodeId.substring(i + 1));
					else
						idGab = Integer.parseInt(nodeId.substring(i + 1, lastPos));
					lastPos = i - 1;
					break;
				}
			}
		} catch (NumberFormatException exc) {
			lastPos = maxLen;
		}

		titApp = nodeId.substring(0, lastPos);
	}
	
	private boolean hasNotNumbers(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)))
				return false;
		return true;
	}
	
	public String getTituloAplicacion() {
		return titApp;
	}

	public int getIdGabinete() {
		return idGab;
	}
	
	public int getIdCarpeta() {
		return idCarp;
	}
	
	public int getIdDocumento() {
		return idDoc;
	}
	
	public boolean isDocumento() {
		return idDoc != -1;
	}
	
	public int getVersion() {
		return version;
	}
}
