package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

import ar.edu.unlam.pb2.enums.Materiales;

public abstract class CopaDelMundo {

	private Long id;
	private Materiales material;
	private Double precio;
	
	public CopaDelMundo(Long id, Materiales material, Double precio) {
		this.id = id;
		this.material = material;
		this.precio = precio;
		
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Materiales getMaterial() {
		return material;
	}



	public void setMaterial(Materiales material) {
		this.material = material;
	}



	public Double getPrecio() {
		return precio;
	}



	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CopaDelMundo other = (CopaDelMundo) obj;
		return Objects.equals(id, other.id);
	}



	protected abstract Double obtenerPrecioFinal();
	
}
