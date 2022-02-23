package com.cmc.evaluacion.fase2.entidades;

import java.util.ArrayList;
import java.util.HashMap;

public class Cartera {

	private ArrayList<Cliente> clientes;
	private HashMap<String, ArrayList<Pago>> pagos;

	public Cartera() {
		clientes = new ArrayList<Cliente>();
		pagos = new HashMap <String, ArrayList<Pago>>();
	}

	public HashMap<String, ArrayList<Pago>> getPagos() {
		return pagos;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	@Override
	public String toString() {
		return "Cartera [clientes=" + clientes + "]";
	}

	public Cliente buscarCliente(String cedula) {
		if (clientes.size() != 0) {
			for (Cliente c : clientes) {
				if (c.getCedula().equals(cedula)) {
					return c;
				}
			}
		}
		return null;

	}

	public boolean agregarCliente(Cliente cliente) {
		Cliente res = buscarCliente(cliente.getCedula());
		if (res != null) {
			return false;
		} else {
			clientes.add(cliente);
			return true;
		}
	}

	public void colocarPrestamo(Prestamo prestamo) {
		Cliente c = buscarCliente(prestamo.getCedulaCliente());
		c.agregarPrestamo(prestamo);
	}

	public void agregarPago(Pago pago) {
			if (pagos.get(pago.getNumeroPrestamo()) == null) {
				ArrayList<Pago> p = new ArrayList<Pago>();
				p.add(pago);
				pagos.put(pago.getNumeroPrestamo(), p);
			} else {
				ArrayList<Pago> p2 = pagos.get(pago.getNumeroPrestamo());
				p2.add(pago);
			}
	}

	public ArrayList<Pago> consultarPagos(String numeroPrestamo) {
		ArrayList<Pago> p = pagos.get(numeroPrestamo);
		if (p == null) {
			ArrayList<Pago> pa = new ArrayList<Pago>();
			return pa;
		} else {
			return p;
		}
	}
	
	public ArrayList<Balance> calcularBalances(){
		ArrayList<Balance> bal = new ArrayList<Balance>();
		for (Cliente c: clientes){
			Balance balance = new Balance();
			bal.add(balance);
			ArrayList <Prestamo> deuda = c.getPrestamos();
			for (Prestamo prestamo: deuda){
				balance.setValorPrestamo(balance.getValorPrestamo()+prestamo.getMonto());
				ArrayList<Pago> pagosRealizados = consultarPagos(prestamo.getNumero());
				for (Pago pay: pagosRealizados){
				balance.setValorPagado(balance.getValorPagado()+pay.getMonto());
				}
				}
			balance.setSaldo(balance.getValorPrestamo()-balance.getValorPagado());
		}
		return bal;
	}
	

}
