package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class FabricaDeCopasDelMundo {

//	La fábrica debe contener entonces clientes, copas del mundo para vender y las ventas
//	realizadas

	private String nombre;
	private TreeSet<Cliente> clientes;
	private List<CopaDelMundo> copasParaVender;
	private Set<Venta> ventas;

	public FabricaDeCopasDelMundo(String nombre) {
		this.nombre = nombre;
		this.clientes = new TreeSet<>();
		this.copasParaVender = new ArrayList<>();
		this.ventas = new HashSet<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean agregarCopaDelMundo(CopaDelMundo copaDelMundo) {
		return this.copasParaVender.add(copaDelMundo);
	}

	public Boolean agregarCliente(Cliente cliente) throws ClienteDuplicadoException {

		if (this.obtenerCliente(cliente) != null) {
			throw new ClienteDuplicadoException("El cliente que intenta ingresar ya existe en la fábrica");
		}

		return this.clientes.add(cliente);
	}

	private Cliente obtenerCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		for (Cliente clienteActual : clientes) {
			if (clienteActual.equals(cliente)) {
				return clienteActual;
			}
		}
		return null;
	}

	public List<CopaDelMundo> obtenerCopasDelMundoEstandar() {

		List<CopaDelMundo> listaAuxiliar = new ArrayList<>();

		for (CopaDelMundo copaDelMundo : this.copasParaVender) {
			if (copaDelMundo instanceof CopaDelMundoEstandar) {
				listaAuxiliar.add(copaDelMundo);
			}
		}
		return listaAuxiliar;
	}

	public CopaDelMundo obtenerCopaDelMundoPorId(Long id) throws CopaDelMundoNoEncontradaException {

		for (CopaDelMundo copaDelMundo : this.copasParaVender) {
			if (copaDelMundo.getId().equals(id)) {
				return copaDelMundo;
			}
		}
		throw new CopaDelMundoNoEncontradaException("Dicha copa no se encuentra en la fábrica");

	}

	public Integer obtenerStockDeUnaCopaEstandar(CopaDelMundo copaEstandar) throws CopaDelMundoNoEncontradaException {

		if (copaEstandar instanceof CopaDelMundoEstandar) {
			CopaDelMundoEstandar copa = (CopaDelMundoEstandar) copaEstandar;
			return copa.getStock();
		}
		return null;
	}

	public void agregarCopaDelMundoEstandarAVentaDeCliente(Cliente clienteDeVenta, Long idCopaDelMundo,
			Integer cantidadAVender) throws CopaDelMundoNoEncontradaException {
//		Venta de copa del mundo estándar: además de generar la venta si no existe, se
//		debe descontar la cantidad solicitada de dicha copa del stock disponible, agregar a
//		la colección de la venta la cantidad solicitada y luego agregar la venta a la colección
//		de ventas.

		Venta venta = this.obtenerVentaPorCliente(clienteDeVenta);
		
		if (venta == null) {;
			venta = new Venta (clienteDeVenta);
		}
		
		if (this.obtenerCopaDelMundoPorId(idCopaDelMundo) instanceof CopaDelMundoEstandar) {
		CopaDelMundoEstandar copa = (CopaDelMundoEstandar) this.obtenerCopaDelMundoPorId(idCopaDelMundo);

		if (this.verificarSiHayStockDisponible(copa, cantidadAVender)) {
			for (int i = 0; i < cantidadAVender; i++) {
				venta.agregarCopaDelMundo(copa);
			}
			this.restarStockDeUnaCopaEstandar(copa, cantidadAVender);
		}

		}
		

	}

	private void restarStockDeUnaCopaEstandar(CopaDelMundoEstandar copaEstandar, Integer cantidadAVender) {
		for (CopaDelMundo copaDelMundo : this.copasParaVender) {
			if (copaDelMundo.equals(copaEstandar)) {
				CopaDelMundoEstandar copa = (CopaDelMundoEstandar) copaDelMundo;
				copa.restarStock(cantidadAVender);
			}
		}
	}

	private Boolean verificarSiHayStockDisponible(CopaDelMundoEstandar copaDelMundoEstandar, Integer cantidadAVender) {
		if (copaDelMundoEstandar.getStock() >= cantidadAVender) {
			return true;
		}
		return false;
	}

	public void agregarCopaDelMundoPersonalizadaAVentaDeCliente(Cliente clienteDeVenta, Long idCopaDelMundo) throws CopaDelMundoNoEncontradaException {
		
		Venta venta = this.obtenerVentaPorCliente(clienteDeVenta);
		
		if (venta == null) {;
			venta = new Venta (clienteDeVenta);
		}
		
		CopaDelMundo copa = this.obtenerCopaDelMundoPorId(idCopaDelMundo);

		if (copa instanceof CopaDelMundoPersonalizada) {
			venta.agregarCopaDelMundo (copa);
			this.eliminarCopaDeLaFabrica(copa);
		}
		
	}

	private Boolean eliminarCopaDeLaFabrica(CopaDelMundo copa) {
		return this.copasParaVender.remove(copa);
	}

	public Double obtenerPrecioDeCopaDelMundoPersonalizada(Long id) throws CopaDelMundoNoEncontradaException {

		CopaDelMundo copaPersonalizada = this.obtenerCopaDelMundoPorId(id);
		return copaPersonalizada.obtenerPrecioFinal();
	}

	public Map<Cliente, Double> obtenerTotalDePrecioDeCopasDelMundoEstandarVendidasAClientesOrdenadasPorCliente() {

		Map<Cliente, Double> mapaClientesVenta = new TreeMap<>();
		
		for (Venta venta : this.ventas) {
			mapaClientesVenta.put(venta.getCliente(), venta.obtenerTotalVenta());
		}
		
		return mapaClientesVenta;
		
	}

	private Venta obtenerVentaPorCliente(Cliente cliente) {
		for (Venta venta : this.ventas) {
			if (venta.getCliente().equals(cliente)) {
				return venta;
			}
		}
		return null;
	}

}
