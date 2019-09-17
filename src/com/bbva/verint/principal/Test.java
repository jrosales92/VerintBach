package com.bbva.verint.principal;

import java.io.File;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
	public static void main(String args[]) throws Exception{
		System.out.println(NuevoDocto());
//		System.out.println(ConsultaDocto());
	}

	public static JSONObject NuevoDocto(){
		JSONObject respuesta = null;
	    try {
            Scanner input = new Scanner(new File("C://Users//GESFOR-676//Documents//ejemploJson4sept (1).txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
//        		System.out.println(line);
        		JSONArray jsonarray = new JSONArray(line);
        		JSONObject jsonObj = null;
        		for (int i = 0; i < jsonarray.length(); i++) {
					jsonObj = jsonarray.getJSONObject(i);
					System.out.println(jsonObj);
					respuesta=  SendDocument.almacenaInformacion(jsonObj);
				}
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		//IMPLEMTAR LECTURA DE TU ARCHIVO DE ENTRADA E ITERAR CADA RENGLON == CADENA

		return respuesta;
	}
}