package br.com.xyinc.br.com.xyinc.batabase.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@Entity
@Table(name = "poi", schema = "xyinc")
public class PoiEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_POI")
	@SequenceGenerator(name = "SEQ_POI", sequenceName = "SEQ_POI", allocationSize = 1)
	@JsonIgnore
	private Integer id;

	@Size(min = 1, max = 100)
	@NotNull(message = "Nome do POI deve ser preenchido")
	@ApiModelProperty(value = "Nome do POI", example = "Minha Casa", required = true)
	@Column(name = "nome")
	private String nome;

	@NotNull(message = "Coordenada X deve ser preenchida")
	@Positive
	@ApiModelProperty(value = "Coordenada X - Valor Positivo", example = "99.99", required = true)
	@Column(name = "coordenada_x")
	private Double coordenadaX;

	@NotNull(message = "Coordenada Y deve ser preenchida")
	@Positive
	@ApiModelProperty(value = "Coordenada Y - Valor Positivo", example = "99.99", required = true)
	@Column(name = "coordenada_y")
	private Double coordenadaY;

	@Override
	public String toString() {
		return "PoiEntity [id=" + id + ", nome=" + nome + ", coordenadaX=" + coordenadaX + ", coordenaday="
				+ coordenadaY + "]";
	}

}
