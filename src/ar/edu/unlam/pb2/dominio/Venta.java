package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venta {
	private Cliente cliente;
	private List<CopaDelMundo> copas;
	
	public Venta(Cliente cliente) {
		this.cliente = cliente;
		this.copas = new ArrayList<>();
	}
	
	

	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public List<CopaDelMundo> getCopas() {
		return copas;
	}



	public void setCopas(List<CopaDelMundo> copas) {
		this.copas = copas;
	}



	@Override
	public int hashCode() {
		return Objects.hash(cliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return Objects.equals(cliente, other.cliente);
	}


	public void agregarCopaDelMundo(CopaDelMundo copaDelMundo) {
		this.copas.add(copaDelMundo);
		
	}



	public Double obtenerTotalVenta() {
		
		Double totalVenta = 0.0;
		
		for (CopaDelMundo copaDelMundo : copas) {
			totalVenta += copaDelMundo.getPrecio();
		}

		return totalVenta;
	}
	
	
	
	
}
