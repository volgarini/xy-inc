package br.com.xyinc.database.models;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@Entity
@Table(name = "poi", schema = "public")
public class PoiEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_POI")
	@SequenceGenerator(name = "SEQ_POI", sequenceName = "SEQ_POI", allocationSize = 1)
	private Integer id;

	@Size(min = 1, max = 100)
	@NotNull(message = "Nome do POI deve ser preenchido")
	@Column(name = "nome")
	private String nome;

	@NotNull(message = "Coordenada X deve ser preenchida")
	@Positive(message = "Coordenada X deve ser maior que 0")
	private Double coordenadaX;

	@NotNull(message = "Coordenada Y deve ser preenchida")
	@Positive(message = "Coordenada Y deve ser maior que 0")
	@Column(name = "coordenada_y")
	private Double coordenadaY;

	@Override
	public String toString() {
		return "PoiEntity [id=" + id + ", nome=" + nome + ", coordenadaX=" + coordenadaX + ", coordenaday="
				+ coordenadaY + "]";
	}

}
