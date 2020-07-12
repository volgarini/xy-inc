package br.com.xyinc.business.mapper;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.xyinc.database.models.PoiEntity;
import br.com.xyinc.dto.PoiDTO;

public class PoiMapper {
	public PoiEntity toEntity(PoiDTO poi) {
		PoiEntity poiEntity = new PoiEntity();

		poiEntity.setNome(poi.getNome());
		poiEntity.setCoordenadaX(poi.getCoordenadaX());
		poiEntity.setCoordenadaY(poi.getCoordenadaY());

		return poiEntity;

	}

	public PoiDTO toDTO(PoiEntity poiEntity) {
		PoiDTO poi = new PoiDTO();

		poi.setNome(poiEntity.getNome());
		poi.setCoordenadaX(poiEntity.getCoordenadaX());
		poi.setCoordenadaY(poiEntity.getCoordenadaY());

		return poi;
	}

	public String validarCampos(PoiEntity poiEntity) {
		StringBuilder mensagem = new StringBuilder();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<PoiEntity>> validate = validator.validate(poiEntity);

		validate.forEach(violation -> mensagem.append(violation.getMessage() + "\n"));
		
		return mensagem.toString();
	}
}
