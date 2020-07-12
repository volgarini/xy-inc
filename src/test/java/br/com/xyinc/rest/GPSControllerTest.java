package br.com.xyinc.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.xyinc.dto.PoiDTO;

@SpringBootTest
class GPSControllerTest {
	private final ArrayList<PoiDTO> pois = new ArrayList<>();
	private final GPSController controller = new GPSController();
	
	@Test
	void deveRetornarBadRequest() {
		PoiDTO poiRestaurante = new PoiDTO();
		poiRestaurante.setNome("Restaurante");
		poiRestaurante.setCoordenadaX(-30d);
		poiRestaurante.setCoordenadaY(-30d);
		
		pois.add(poiRestaurante);
		
		final ResponseEntity<String> response = controller.cadastrar(pois);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
