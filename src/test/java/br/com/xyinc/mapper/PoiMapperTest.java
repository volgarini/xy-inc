package br.com.xyinc.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import br.com.xyinc.br.com.xyinc.batabase.models.PoiEntity;
import br.com.xyinc.br.com.xyinc.dto.PoiDTO;
import br.com.xyinc.br.com.xyinc.mapper.PoiMapper;

public class PoiMapperTest {
	private ArrayList<PoiDTO> pois;
	private PoiMapper mapper;
	
	@Before
	public void criarPoiDTO() {
		PoiDTO poiLanchonete = new PoiDTO();
		poiLanchonete.setNome("Lanchonete");
		poiLanchonete.setCoordenadaX(10d);
		poiLanchonete.setCoordenadaY(10d);
		
		PoiDTO poiAcademia = new PoiDTO();
		poiAcademia.setNome("Academia");
		poiAcademia.setCoordenadaX(20d);
		poiAcademia.setCoordenadaY(20d);
		
		pois = new ArrayList<>();
		pois.add(poiLanchonete);
		pois.add(poiAcademia);
		
		mapper = new PoiMapper();
	}
	@Test
	public void deveConverterSemErro() {
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
	}
	
	@Test
	public void devePegarMensagemVazia() {
		StringBuilder mensagem = new StringBuilder();
		
		ArrayList<PoiEntity> poiEnts = new ArrayList<>();
		pois.forEach(poi -> poiEnts.add(mapper.toEntity(poi)));
		
		poiEnts.forEach(entity -> mensagem.append(mapper.validarCampos(entity)));
		
		assertTrue(StringUtils.isEmpty(mensagem.toString()));
	}
	
	@Test
	public void devePegarMensagem() {
		StringBuilder mensagem = new StringBuilder();
		
		PoiDTO poiRestaurante = new PoiDTO();
		poiRestaurante.setNome("Restaurante");
		poiRestaurante.setCoordenadaX(-30d);
		poiRestaurante.setCoordenadaY(-30d);
		
		pois.add(poiRestaurante);
		
		ArrayList<PoiEntity> poiEnts = new ArrayList<>();
		pois.forEach(poi -> poiEnts.add(mapper.toEntity(poi)));
		
		poiEnts.forEach(entity -> mensagem.append(mapper.validarCampos(entity)));
		
		assertFalse(StringUtils.isEmpty(mensagem.toString()));
	}
}
