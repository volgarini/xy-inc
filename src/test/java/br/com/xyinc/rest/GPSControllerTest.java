package br.com.xyinc.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.xyinc.dto.PoiDTO;

@SpringBootTest
class GPSControllerTest {
	private final ArrayList<PoiDTO> pois = new ArrayList<>();
	
	@Autowired
	private GPSController controller;
	
	
	@Test
	void deveRetornarBadRequest() {
		PoiDTO poiRestaurante = new PoiDTO();
		poiRestaurante.setNome("Restaurante");
		poiRestaurante.setCoordenadaX(-30);
		poiRestaurante.setCoordenadaY(-30);
		
		pois.add(poiRestaurante);
				
		final ResponseEntity<String> response = controller.cadastrar(pois);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void deveRetornarCreated(){
		PoiDTO poiRestaurante = new PoiDTO();
		poiRestaurante.setNome("Restaurante");
		poiRestaurante.setCoordenadaX(30);
		poiRestaurante.setCoordenadaY(30);
		
		PoiDTO poiCasa = new PoiDTO();
		poiCasa.setNome("Minha Casa");
		poiCasa.setCoordenadaX(15);
		poiCasa.setCoordenadaY(15);
		
		pois.add(poiRestaurante);
		pois.add(poiCasa);
		
		final ResponseEntity<String> response = controller.cadastrar(pois);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void deveRetornarListaDePois() {
		final ResponseEntity<List<PoiDTO>> response = controller.listarTodos();
		
		List<PoiDTO> listaPoi = response.getBody();
	
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, listaPoi.size());
	}
	
	@Test
	void deveRertornarPorProximidade() {
		ResponseEntity<List<PoiDTO>> response = controller.listarProximidade(40, 40, 10);
		
		List<PoiDTO> listaPoi = response.getBody();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, listaPoi.size());
		
		assertEquals("Restaurante", listaPoi.get(0).getNome());
	}
}
