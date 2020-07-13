package br.com.xyinc.dto;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class PoiDTO {
	@NotNull
	@ApiModelProperty(value = "Nome do POI", example = "Minha Casa", required = true)
	private String nome;
	
	@ApiModelProperty(value = "Coordenada X - Valor Positivo", example = "99", required = true)
	private Integer coordenadaX;
	
	@ApiModelProperty(value = "Coordenada Y - Valor Positivo", example = "50", required = true)
	private Integer coordenadaY;

	@Override
	public String toString() {
		return "PoiDTO [nome=" + nome + ", coordenadaX=" + coordenadaX + ", coordenadaY=" + coordenadaY + "]";
	}
	
	
}
