package ar.edu.unlam.pb2.dominio;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.enums.Colores;
import ar.edu.unlam.pb2.enums.Materiales;

public class FabricaCopasDelMundoTest {
	
	private FabricaDeCopasDelMundo fabrica;
	
	@Before
	public void init () {
		this.fabrica = new FabricaDeCopasDelMundo("Tecnocopas");
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoEstandar() throws CopaDelMundoNoEncontradaException {
		
		Long id = (long) 1234;
		CopaDelMundo copaEstandar = new CopaDelMundoEstandar(id, Materiales.PLASTICO, 500.5, 10);
		assertTrue(this.fabrica.agregarCopaDelMundo(copaEstandar));
		
		CopaDelMundo copaObtenida = this.fabrica.obtenerCopaDelMundoPorId(id);
		assertEquals(copaEstandar, copaObtenida);
		assertTrue(copaObtenida instanceof CopaDelMundoEstandar);
		
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoPersonalizada() throws CopaDelMundoNoEncontradaException {
		
		Long id = (long) 4321;
		CopaDelMundo copaPersonalizada = new CopaDelMundoPersonalizada(id, Materiales.PLASTICO, 500.5, Colores.CEDRO);
		assertTrue(this.fabrica.agregarCopaDelMundo(copaPersonalizada));
		
		CopaDelMundo copaObtenida = this.fabrica.obtenerCopaDelMundoPorId(id);
		assertEquals(copaPersonalizada, copaObtenida);
		assertTrue(copaObtenida instanceof CopaDelMundoPersonalizada);
		
	}

	@Test (expected = ClienteDuplicadoException.class)
	public void dadoQueExisteUnaFabricaDeCopasDelMundoAlAgregarUnClienteExistenteSeLanzaUnaClienteDuplicadoException() throws ClienteDuplicadoException {
		
		Cliente cliente1 = new Cliente (45070730, "Jimena", "Gomez");
		this.fabrica.agregarCliente(cliente1);
		
		Cliente cliente2 = new Cliente (45070730, "Jimena", "Gomez");
		this.fabrica.agregarCliente(cliente2);
		
	}

	@Test
	public void dadoQueExisteUnaFabricaQuePoseeCopasDelMundoSePuedenObtenerLasCopasDelMundoEstandar() {
		
		CopaDelMundo copa1 = new CopaDelMundoEstandar((long) 1234, Materiales.PLASTICO, 500.5, 10);
		this.fabrica.agregarCopaDelMundo(copa1);

		CopaDelMundo copa2 = new CopaDelMundoPersonalizada((long) 4321, Materiales.RECINA, 360.5, Colores.CEDRO);
		this.fabrica.agregarCopaDelMundo(copa2);
		
		CopaDelMundo copa3 = new CopaDelMundoEstandar((long) 7894, Materiales.RECINA, 450.0, 8);
		this.fabrica.agregarCopaDelMundo(copa3);

		CopaDelMundo copa4 = new CopaDelMundoPersonalizada((long) 4987, Materiales.PLASTICO, 780.5, Colores.ROBLE_OSCURO);
		this.fabrica.agregarCopaDelMundo(copa4);
		
		List<CopaDelMundo> copasDelMundoEstandar = this.fabrica.obtenerCopasDelMundoEstandar();
		
		Integer i = 0;
		for (CopaDelMundo copaDelMundo : copasDelMundoEstandar) {
			switch (i) {
			case 0:
				assertEquals(copa1, copaDelMundo);
			break;
			case 1:
				assertEquals(copa3, copaDelMundo);
			break;
			} i++;
		}
		
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPuedoObtenerUnaCopaDelMundoPorSuId() throws CopaDelMundoNoEncontradaException {
		
		Long id = (long) 1234;
		CopaDelMundo copaEstandar = new CopaDelMundoEstandar(id, Materiales.PLASTICO, 500.5, 10);
		this.fabrica.agregarCopaDelMundo(copaEstandar);
		
		CopaDelMundo copaObtenida = this.fabrica.obtenerCopaDelMundoPorId(id);
		assertEquals(copaEstandar, copaObtenida);
		assertEquals(copaEstandar.getPrecio(), copaObtenida.getPrecio());
		
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarCincoCopasDelMundoAUnaVentaDeCopasDelMundoEstandarParaUnClienteSeDescuentanCincoUnidadesDelStockDeCopasDelMundoEstandar() throws ClienteDuplicadoException, CopaDelMundoNoEncontradaException {
		Long idCopa = (long) 1234;
		CopaDelMundo copaEstandar = new CopaDelMundoEstandar(idCopa, Materiales.PLASTICO, 500.5, 18);
		this.fabrica.agregarCopaDelMundo(copaEstandar);

		Cliente cliente = new Cliente (45070730, "Jimena", "Gomez");
		this.fabrica.agregarCliente(cliente);
		
		Integer cantidadAVender = 5;
		this.fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente, idCopa, cantidadAVender);
		
		Integer stockEsperado = 18 - cantidadAVender;
		
		CopaDelMundo copaConNuevoStock = this.fabrica.obtenerCopaDelMundoPorId(idCopa);
		Integer stockObtenido = this.fabrica.obtenerStockDeUnaCopaEstandar(copaConNuevoStock);
		
		assertEquals(stockEsperado, stockObtenido);
		
	}

	@Test (expected = CopaDelMundoNoEncontradaException.class)
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarUnaVentaDeCopasDelMundoPersonalizadaParaUnClienteSeRemueveLaCopaDelMundoPersonalizadaDeLaFabrica() throws ClienteDuplicadoException, CopaDelMundoNoEncontradaException {
		Long idCopa = (long) 1234;
		CopaDelMundo copaPersonalizada = new CopaDelMundoPersonalizada(idCopa, Materiales.PLASTICO, 500.5, Colores.CAOBA);
		this.fabrica.agregarCopaDelMundo(copaPersonalizada);

		Cliente cliente = new Cliente (45070730, "Jimena", "Gomez");
		this.fabrica.agregarCliente(cliente);
		
		this.fabrica.agregarCopaDelMundoPersonalizadaAVentaDeCliente(cliente, idCopa);
		this.fabrica.obtenerCopaDelMundoPorId(idCopa);
	
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPersonalizadasSePuedeObtenerElPrecioDeUnaCopaDelMundoPersonalizada() throws CopaDelMundoNoEncontradaException, ClienteDuplicadoException {
	
		Long idCopa = (long) 1234;
		CopaDelMundo copaPersonalizada = new CopaDelMundoPersonalizada(idCopa, Materiales.PLASTICO, 500.5, Colores.CAOBA);
		this.fabrica.agregarCopaDelMundo(copaPersonalizada);

		Double precioFinalEsperado = 500.5 + 500.5 * (0.15 + 0.05);
		Double precioFinalObtenido = this.fabrica.obtenerPrecioDeCopaDelMundoPersonalizada(idCopa);
		assertEquals(precioFinalEsperado, precioFinalObtenido);
	
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConVentasDeCopasDelMundoEstandarYPersonalizadasVendidasAClientesSePuedeObtenerUnMapaConClaveClienteYTotalDeVentasDeCopasEstandarOrdenadoPorCliente() throws ClienteDuplicadoException {
		
		CopaDelMundo copa1 = new CopaDelMundoEstandar((long) 1234, Materiales.PLASTICO, 300.5, 10);
		this.fabrica.agregarCopaDelMundo(copa1);

		CopaDelMundo copa2 = new CopaDelMundoPersonalizada((long) 4321, Materiales.PLASTICO, 500.5, Colores.CAOBA);
		this.fabrica.agregarCopaDelMundo(copa2);

		Double precioFinalEsperado1 = (300.5 + (300.5 * 0.20)) + (500.5 + 500.5 * (0.15 + 0.05));
		
		Cliente cliente1 = new Cliente (45070731, "Jimena", "Gomez");
		this.fabrica.agregarCliente(cliente1);
		
		
		CopaDelMundo copa3 = new CopaDelMundoEstandar((long) 1234, Materiales.PLASTICO, 200.5, 10);
		this.fabrica.agregarCopaDelMundo(copa3);

		Double precioFinalEsperado2 = (200.5 + (300.5 * 0.20));
		
		Cliente cliente2 = new Cliente (45070730, "Sergio", "Gomez");
		this.fabrica.agregarCliente(cliente2);
		
		Map<Cliente, Double> clientesVenta = this.fabrica.obtenerTotalDePrecioDeCopasDelMundoEstandarVendidasAClientesOrdenadasPorCliente();
		
		Integer i = 0;
		for (Map.Entry<Cliente, Double> entry : clientesVenta.entrySet()) {
			switch (i) {
			case 0:
				assertEquals(cliente2, entry.getKey());
				assertEquals(precioFinalEsperado2, entry.getValue());
			break;
			case 1:
				assertEquals(cliente1, entry.getKey());
				assertEquals(precioFinalEsperado1, entry.getValue());
			break;
			} i++;
			
		}
		
	
	}
}
