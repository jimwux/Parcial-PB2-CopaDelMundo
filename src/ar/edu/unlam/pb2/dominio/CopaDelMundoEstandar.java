package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.*;

public class CopaDelMundoEstandar extends CopaDelMundo {

	private final static Double MANO_DE_OBRA = 0.2;
	private Integer stock;

	public CopaDelMundoEstandar(Long id, Materiales material, Double precio, Integer stock) {
		super(id, material, precio);
		this.stock = stock;
	}
	
	

	public Integer getStock() {
		return stock;
	}



	public void actualizarStock(Integer stock) {
		this.stock += stock;
	}
	
	public void restarStock(Integer cantidadVendida) {
		this.stock -= cantidadVendida;
	}



	@Override
	protected Double obtenerPrecioFinal() {
		Double precioFinal = (this.getPrecio() + this.getPrecio() * CopaDelMundoEstandar.MANO_DE_OBRA);
		return precioFinal;
	}


	
}
