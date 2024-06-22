package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Cliente implements Comparable<Cliente> {

//	El sistema debe admitir la carga de clientes, los cuales no deben repetirse y deben estar
//	ordenados siempre por su dni. Además, se solicita el nombre y el apellido por separado de
//	estos.
	
	private Integer dni;
	private String nombre;
	private String apellido;
	
	public Cliente(Integer dni, String nombre, String apellido) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}



	@Override
	public int compareTo(Cliente o) {
		// TODO Auto-generated method stub
		return this.getDni().compareTo(o.getDni());
	}
	
	
	
}
