package br.com.xyinc.business.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import br.com.xyinc.database.models.PoiEntity;
import br.com.xyinc.dto.PoiDTO;

class PoiMapperTest {
	private ArrayList<PoiDTO> pois;
	private PoiMapper mapper;
	
	@BeforeEach
	void criarPoiDTO() {
		PoiDTO poiLanchonete = new PoiDTO();
		poiLanchonete.setNome("Lanchonete");
		poiLanchonete.setCoordenadaX(10);
		poiLanchonete.setCoordenadaY(10);
		
		PoiDTO poiAcademia = new PoiDTO();
		poiAcademia.setNome("Academia");
		poiAcademia.setCoordenadaX(20);
		poiAcademia.setCoordenadaY(20);
		
		pois = new ArrayList<>();
		pois.add(poiLanchonete);
		pois.add(poiAcademia);
		
		mapper = new PoiMapper();
	}
	@Test
	void deveConverterSemErro() {
		ArrayList<PoiEntity> poiEnts = new ArrayList<>();
		pois.forEach(poi -> poiEnts.add(mapper.toEntity(poi)));
		
		assertEquals(poiEnts.size(), pois.size());
		assertEquals(2, poiEnts.size());
		
		assertEquals(poiEnts.get(0).getNome(), pois.get(0).getNome());
		assertEquals(poiEnts.get(0).getCoordenadaX(), pois.get(0).getCoordenadaX());
		assertEquals(poiEnts.get(0).getCoordenadaY(), pois.get(0).getCoordenadaY());
		
		
		assertEquals(poiEnts.get(1).getNome(), pois.get(1).getNome());
		assertEquals(poiEnts.get(1).getCoordenadaX(), pois.get(1).getCoordenadaX());
		assertEquals(poiEnts.get(1).getCoordenadaY(), pois.get(1).getCoordenadaY());
		
		ArrayList<PoiDTO> poiDs = new ArrayList<>();
		poiEnts.forEach(poiE -> poiDs.add(mapper.toDTO(poiE))); 
		
		assertEquals(2, poiDs.size());
	}
	
	@Test
	void devePegarMensagemVazia() {
		StringBuilder mensagem = new StringBuilder();
		
		ArrayList<PoiEntity> poiEnts = new ArrayList<>();
		pois.forEach(poi -> poiEnts.add(mapper.toEntity(poi)));
		
		poiEnts.forEach(entity -> mensagem.append(mapper.validarCampos(entity)));
		
		assertTrue(StringUtils.isEmpty(mensagem.toString()));
	}
	
	@Test
	void devePegarMensagem() {
		StringBuilder mensagem = new StringBuilder();
		
		PoiDTO poiRestaurante = new PoiDTO();
		poiRestaurante.setNome("Restaurante");
		poiRestaurante.setCoordenadaX(-30);
		poiRestaurante.setCoordenadaY(-30);
		
		pois.add(poiRestaurante);
		
		ArrayList<PoiEntity> poiEnts = new ArrayList<>();
		pois.forEach(poi -> poiEnts.add(mapper.toEntity(poi)));
		
		poiEnts.forEach(entity -> mensagem.append(mapper.validarCampos(entity)));
		
		assertFalse(StringUtils.isEmpty(mensagem.toString()));
	}
}
