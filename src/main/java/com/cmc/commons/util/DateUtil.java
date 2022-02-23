package com.cmc.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final SimpleDateFormat FormatoFecha = new SimpleDateFormat("yyyy/dd/MM");
	
	public static Date convertir( String fecha) throws ParseException{
		Date fechaF = FormatoFecha.parse(fecha);
		return fechaF;
 }
}
