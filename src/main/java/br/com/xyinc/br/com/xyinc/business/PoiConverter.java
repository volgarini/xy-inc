package br.com.xyinc.br.com.xyinc.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.xyinc.br.com.xyinc.batabase.models.PoiEntity;
import br.com.xyinc.br.com.xyinc.batabase.repository.PoiRepository;
import br.com.xyinc.br.com.xyinc.dto.PoiDTO;

@Service
public class PoiConverter {
	@Autowired
	private PoiRepository poiRepository;

	private String mensagem;

	public PoiConverter() {
		this.mensagem = "";
	}

	private PoiEntity toEntity(PoiDTO poi) {
		PoiEntity poiEntity = new PoiEntity();

		poiEntity.setNome(poi.getNome());
		poiEntity.setCoordenadaX(poi.getCoordenadaX());
		poiEntity.setCoordenadaY(poi.getCoordenadaY());

		return poiEntity;

	}

	private PoiDTO toDTO(PoiEntity poiEntity) {
		PoiDTO poi = new PoiDTO();

		poi.setNome(poiEntity.getNome());
		poi.setCoordenadaX(poiEntity.getCoordenadaX());
		poi.setCoordenadaY(poiEntity.getCoordenadaY());

		return poi;
	}

	private void validarCampos(PoiEntity poiEntity) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<PoiEntity>> validate = validator.validate(poiEntity);

		validate.forEach(violation -> mensagem += violation.getMessage() + "\n");
	}

	public void cadastrarPois(List<PoiDTO> pois) {
		mensagem = "";
		pois.forEach(poi -> validarCampos(toEntity(poi)));

		if (StringUtils.isEmpty(mensagem)) {
			pois.forEach(poi -> poiRepository.save(toEntity(poi)));
		}
	}

	public List<PoiDTO> listarTodos() {
		ArrayList<PoiDTO> pois = new ArrayList<>();

		poiRepository.findAll().forEach(entity -> pois.add(toDTO(entity)));

		return pois;
	}

	public List<PoiDTO> listarPorCoordenadas(Double coordXini, Double coordXfim, Double coordYini, Double coordYfim) {
		ArrayList<PoiDTO> pois = new ArrayList<>();

		poiRepository.findByCoordenadaXBetweenAndCoordenadaYBetween(coordXini, coordXfim, coordYini, coordYfim)
				.forEach(entity -> pois.add(toDTO(entity)));

		return pois;
	}

	public String getMensagem() {
		return mensagem;
	}
}
