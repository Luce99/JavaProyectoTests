package com.cmc.evaluacion.fase2.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import org.apache.log4j.Logger;

import com.cmc.commons.util.DateUtil;
import com.cmc.commons.util.TipoPrestamo;
import com.cmc.evaluacion.fase2.entidades.Cartera;
import com.cmc.evaluacion.fase2.entidades.Prestamo;
import com.cmc.excepciones.EvaluacionException;

public class AdminPrestamos {
	private static Logger logger = Logger.getLogger(AdminPrestamos.class);
	
	public Prestamo armarPrestamo(String linea){
		String[] partes;
		Prestamo p;
		DateUtil fecha = new DateUtil();
		try {
			partes = linea.split("-");
			p = new Prestamo(partes[1], partes[0]);
			String primeraLetra = partes[1].substring(0, 1);
			
			if (primeraLetra.equals(TipoPrestamo.HIPOTECARIO) ){
				p.setTipo(TipoPrestamo.HIPOTECARIO);
			}
			else if (primeraLetra.equals(TipoPrestamo.QUIROGRAFARIO)){
				p.setTipo(TipoPrestamo.QUIROGRAFARIO);
			}
			else {
				p.setTipo(TipoPrestamo.OTRO);
			}
		 
		 Date fechaConvertida = fecha.convertir(partes[2]);
		 p.setFecha(fechaConvertida);
		 
		 double monto = Double.parseDouble(partes[3]);
		 p.setMonto(monto);
		 return p;
		 
		} catch (Exception e) {
			logger.error("Error al armar prestamo en la linea", e);
			throw new EvaluacionException("Error al armar prestamo en la linea" + linea);
		}
	}
	
	public void colocarPrestamos(String rutaArchivo, Cartera cartera){
		File file = new File(rutaArchivo);
		BufferedReader br = null;
		FileReader fileReader = null;
		String linea = "";
		Prestamo p = null;

		try {
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			while ((linea = br.readLine()) != null) {
				p = armarPrestamo(linea);
				cartera.colocarPrestamo(p);
			}
		} catch (FileNotFoundException e) {
			logger.error("No existe el archivo", e);
			throw new EvaluacionException("No exise el archivo" + rutaArchivo);
		} catch (Exception e) {
			logger.error("Error al leer el archivo", e);
			throw new EvaluacionException("Error al leer el archivo" + e);
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
	}
	
	
	
}
