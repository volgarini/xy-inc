package br.com.xyinc.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import br.com.xyinc.br.com.xyinc.batabase.models.PoiEntity;
import br.com.xyinc.br.com.xyinc.dto.PoiDTO;
import br.com.xyinc.br.com.xyinc.mapper.PoiMapper;

public class PoiMapperTest {
	private List<PoiDTO> pois;
	private PoiMapper mapper;
	
	@Before
	public void criarPoiDTO() {
		PoiDTO poiLanchonete = new PoiDTO();
		poiLanchonete.setNome("Lanchonete");
		poiLanchonete.setCoordenadaX(10d);
		poiLanchonete.setCoordenadaY(10d);
		
		PoiDTO poiAcademia = new PoiDTO();
		poiLanchonete.setNome("Academia");
		poiLanchonete.setCoordenadaX(-10d);
		poiLanchonete.setCoordenadaY(-10d);
		
		pois = Arrays.asList(poiLanchonete, poiAcademia);
		
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
	public void devePegarMensagem() {
		StringBuilder mensagem = new StringBuilder();
		
		ArrayList<PoiEntity> poiEnts = new ArrayList<>();
		pois.forEach(poi -> poiEnts.add(mapper.toEntity(poi)));
		
		poiEnts.forEach(entity -> mensagem.append(mapper.validarCampos(entity)));
		
		assertFalse(StringUtils.isEmpty(mensagem.toString()));
	}
}
