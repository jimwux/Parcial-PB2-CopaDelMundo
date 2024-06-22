package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Materiales;
import ar.edu.unlam.pb2.enums.Colores;

public class CopaDelMundoPersonalizada extends CopaDelMundo {
	
	private final Double MANO_DE_OBRA = 0.15;
	private Colores colorAtril;

	public CopaDelMundoPersonalizada(Long id, Materiales material, Double precio, Colores colorAtril) {
		super(id, material, precio);
		this.colorAtril = colorAtril;
		// TODO Auto-generated constructor stub
	}
	
	

	public Colores getColorAtril() {
		return colorAtril;
	}



	public void setColorAtril(Colores colorAtril) {
		this.colorAtril = colorAtril;
	}



	@Override
	protected Double obtenerPrecioFinal() {
		
		Double porcentajeColor = 0.0;
		
		switch (this.getColorAtril()) {
		case CAOBA:
			porcentajeColor = 0.05;
			break;
		case CEDRO:
			porcentajeColor = 0.10;			
			break;
		case ROBLE_OSCURO:
			porcentajeColor = 0.15;
			break;
		}
		
		Double precioFinal = this.getPrecio() + (this.getPrecio() * (MANO_DE_OBRA + porcentajeColor));

		return precioFinal;
	}

}
