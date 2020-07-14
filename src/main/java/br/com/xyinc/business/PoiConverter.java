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

	public void realizarValidacao(List<PoiDTO> pois) {
		mensagem = "";
		pois.forEach(poi -> mensagem += mapper.validarCampos(mapper.toEntity(poi)));
	}
	public void cadastrarPois(List<PoiDTO> pois) {
		realizarValidacao(pois);
		
		if (StringUtils.isEmpty(mensagem)) {
			pois.forEach(poi -> poiRepository.save(mapper.toEntity(poi)));
		}
	}

	public List<PoiDTO> listarTodos() {
		ArrayList<PoiDTO> pois = new ArrayList<>();

		poiRepository.findAll().forEach(entity -> pois.add(mapper.toDTO(entity)));

		return pois;
	}

	public Integer coordDiff(Integer coordIni, Integer coordMax) {
		return coordIni - coordMax;
	}

	public Integer coordSum(Integer coordIni, Integer coordMax) {
		return coordIni + coordMax;
	}

	public List<PoiDTO> listarPorCoordenadas(Integer coordX, Integer coordY, Integer dMax) {
		ArrayList<PoiDTO> pois = new ArrayList<>();

		poiRepository
				.findByCoordenadaXBetweenAndCoordenadaYBetween(coordDiff(coordX, dMax), coordSum(coordX, dMax),
						coordDiff(coordY, dMax), coordSum(coordY, dMax))
				.forEach(entity -> pois.add(mapper.toDTO(entity)));

		return pois;
	}

	public String getMensagem() {
		return mensagem;
	}
}
