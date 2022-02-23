package com.cmc.evaluacion.fase2.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.cmc.evaluacion.fase2.entidades.Cartera;
import com.cmc.evaluacion.fase2.entidades.Cliente;
import com.cmc.excepciones.CheckedException;
import com.cmc.excepciones.EvaluacionException;

public class AdminClientes {

	private static Logger logger = Logger.getLogger(AdminClientes.class);

	private static void armarCliente(String linea, Cartera cartera) {

		String[] partes;
		Cliente c;
		try {
			partes = linea.split(",");
			c = new Cliente(partes[0], partes[1], partes[2]);
			cartera.agregarCliente(c);
		} catch (Exception e) {
			logger.error("Error al armar cliente en la linea", e);
			try {
				throw new CheckedException("Error al armar cliente en la linea" + linea);
			} catch (CheckedException e1) {
				logger.error("Error al armar cliente en la linea", e1);
			}
			
		}
	}

	public static Cartera armarCartera(String rutaArchivo) {

		File file = new File(rutaArchivo);
		BufferedReader br = null;
		FileReader fileReader = null;
		Cartera cartera = new Cartera();
		String linea = "";

		try {
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			while ((linea = br.readLine()) != null) {
				armarCliente(linea, cartera);
			}

		} catch (FileNotFoundException e) {
			logger.error("No existe el archivo", e);
			throw new EvaluacionException("No exise el archivo" + rutaArchivo);
		} catch (Exception e) {
			logger.error("Error al leer el archivo", e);
			throw new EvaluacionException("Error al leer el archivo" + rutaArchivo);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				logger.error("Error al cerrar el bufferedReader br", e);
			}
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (Exception e) {
				logger.error("Error al cerrar el fileReader br", e);
			}

		}
		return cartera;
	}
}
