package com.cmc.evaluacion.fase2.test;

import java.util.ArrayList;

import com.cmc.evaluacion.fase2.entidades.Cartera;
import com.cmc.evaluacion.fase2.entidades.Cliente;
import com.cmc.evaluacion.fase2.entidades.Prestamo;
import com.cmc.evaluacion.fase2.servicios.AdminClientes;
import com.cmc.evaluacion.fase2.servicios.AdminPrestamos;

import junit.framework.TestCase;

public class TestColocarPrestamos extends TestCase {
	private static final String ROOT="C:\\Users\\Asus\\Documents\\SELECCIONtrabajo";
	private static final String ARCHIVO_CLIENTES=ROOT+"\\Clientes.txt";
	private static final String ARCHIVO_PRESTAMOS=ROOT+"\\Prestamos.txt";
	
	
	
	public void testColocarClienteA(){
		Cartera cartera=AdminClientes.armarCartera(ARCHIVO_CLIENTES);
		AdminPrestamos adminPrestamos=new AdminPrestamos();
		adminPrestamos.colocarPrestamos(ARCHIVO_PRESTAMOS,cartera);
		
		Cliente cliente=cartera.buscarCliente("1714616123");
		ArrayList<Prestamo> prestamos=cliente.getPrestamos();
		
		assertEquals(2, prestamos.size());
	}
	
	public void testColocarClienteB(){
		Cartera cartera=AdminClientes.armarCartera(ARCHIVO_CLIENTES);
		AdminPrestamos adminPrestamos=new AdminPrestamos();
		adminPrestamos.colocarPrestamos(ARCHIVO_PRESTAMOS,cartera);
		
		Cliente cliente=cartera.buscarCliente("0213982311");
		ArrayList<Prestamo> prestamos=cliente.getPrestamos();
		
		assertEquals(1, prestamos.size());
	}
}