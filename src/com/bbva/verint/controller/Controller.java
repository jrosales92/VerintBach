package com.bbva.verint.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import com.bbva.verint.principal.Test;

public class Controller {
	Test jsonObj = new Test();
	public static File CreaArchivoControl(String File) {
		String ruta = "C:/Users/GESFOR-676/Documents/Verint Archivos/";
		String json = "C://Users//GESFOR-676//Documents//ejemploJson4sept (1).txt";
		File file = new File("C:/Users/GESFOR-676/Documents/Verint Archivos/Verint.control");
		// Si el archivo no existe es creado
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = null;
			bw = new BufferedWriter(fw);
			for (int i = 1; i <= 50; i++) {
				bw.write(json + i);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	public File CreaArchivoStart(String nameFile) {
		String ruta = "C:/Users/GESFOR-676/Documents/Verint Archivos/";
		String contenido = "";

		JSONArray json = new JSONArray();

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("keyIntervener", "");
		map.put("documentKey", "");
		map.put("nombredoc", "");
		map.put("dateTime","");
		map.put("typeOperation","");
		map.put("typeMatrix","");
		map.put("tipeDocument","");
		map.put("ext","");
		map.put("size","");
		map.put("product","");
		map.put("cr","");
		map.put("customerId","");
		map.put("funtion","");
		map.put("typeTransact","");
		map.put("sha1n","");
		map.put("descriptionDocument","");
		map.put("service","");
		map.put("signatureAdviser","");
		map.put("contractId","");
		map.put("nameRecord","");
		map.put("idCertification","");
		map.put("phaseOperation","");
		

		json.put(map);

		File file = new File("C:/Users/GESFOR-676/Documents/Verint Archivos/Verint.start");
		// Si el archivo no existe es creado
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = null;
			bw = new BufferedWriter(fw);
			for (int i = 1; i <= 200; i++) {
				bw.write(json.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}
}
