package br.com.xyinc.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import br.com.xyinc.dto.PoiDTO;

public class PoiConverterTest {
	private PoiConverter converter;

	@BeforeEach
	void criarConverter() {
		converter = new PoiConverter();
	}

	@Test
	void deveCalcularDistanciaPositiva() {
		Integer coordSum = converter.coordSum(10, 10);

		assertTrue(coordSum > 0);
		assertEquals(20, coordSum);
	}

	@Test
	void deveCalcularDistanciaNegativa() {
		Integer coordDiff = converter.coordDiff(10, 50);

		assertTrue(coordDiff < 0);
		assertEquals(-40, coordDiff);
	}

	@Test
	void deveResultarZero() {
		Integer coordDiff = converter.coordDiff(10, 10);

		assertEquals(0, coordDiff);
	}

	@Test
	void deveRetornarMensagemVazia() {
		PoiDTO poiLanchonete = new PoiDTO();
		poiLanchonete.setNome("Lanchonete");
		poiLanchonete.setCoordenadaX(10);
		poiLanchonete.setCoordenadaY(10);
		
		converter.realizarValidacao(Arrays.asList(poiLanchonete));

		assertTrue(StringUtils.isEmpty(converter.getMensagem()));
	}

	@Test
	void deveRetornarErroNome() {
		PoiDTO poiLanchonete = new PoiDTO();
		poiLanchonete.setNome(null);
		poiLanchonete.setCoordenadaX(10);
		poiLanchonete.setCoordenadaY(10);

		converter.realizarValidacao(Arrays.asList(poiLanchonete));

		assertFalse(StringUtils.isEmpty(converter.getMensagem()));
		assertTrue(converter.getMensagem().contains("Nome do POI deve ser preenchido"));
	}
	
	@Test
	void deveValidarCoordenadasPositivas() {
		PoiDTO poiLanchonete = new PoiDTO();
		poiLanchonete.setNome("Lanchonete");
		poiLanchonete.setCoordenadaX(-10);
		poiLanchonete.setCoordenadaY(-10);

		converter.realizarValidacao(Arrays.asList(poiLanchonete));
		
		assertFalse(StringUtils.isEmpty(converter.getMensagem()));
		assertTrue(converter.getMensagem().contains("Coordenada X deve ser maior que 0"));
		assertTrue(converter.getMensagem().contains("Coordenada Y deve ser maior que 0"));
	}
}
