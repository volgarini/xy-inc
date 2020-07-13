package br.com.xyinc.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.xyinc.business.mapper.PoiMapper;
import br.com.xyinc.database.repository.PoiRepository;
import br.com.xyinc.dto.PoiDTO;

@Service
public class PoiConverter {
	@Autowired
	private PoiRepository poiRepository;

	private String mensagem;
	private PoiMapper mapper;
	
	public PoiConverter() {
		this.mensagem = "";
		this.mapper = new PoiMapper();
	}

	

	public void cadastrarPois(List<PoiDTO> pois) {
		mensagem = "";
		pois.forEach(poi -> mensagem += mapper.validarCampos(mapper.toEntity(poi)));

		if (StringUtils.isEmpty(mensagem)) {
			pois.forEach(poi -> poiRepository.save(mapper.toEntity(poi)));
		}
	}

	public List<PoiDTO> listarTodos() {
		ArrayList<PoiDTO> pois = new ArrayList<>();

		poiRepository.findAll().forEach(entity -> pois.add(mapper.toDTO(entity)));

		return pois;
	}

	public List<PoiDTO> listarPorCoordenadas(Integer coordXini, Integer coordXfim, Integer coordYini, Integer coordYfim) {
		ArrayList<PoiDTO> pois = new ArrayList<>();

		poiRepository.findByCoordenadaXBetweenAndCoordenadaYBetween(coordXini, coordXfim, coordYini, coordYfim)
				.forEach(entity -> pois.add(mapper.toDTO(entity)));

		return pois;
	}

	public String getMensagem() {
		return mensagem;
	}
}
