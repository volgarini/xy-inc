package br.com.xyinc.br.com.xyinc.batabase.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "poi", schema = "xyinc")
public class PoiEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_poi")
	@SequenceGenerator(name = "seq_poi", sequenceName = "seq_poi", allocationSize = 1)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "coordenada_x")
	private Double coordenadaX;

	@Column(name = "coordenada_y")
	private Double coordenaday;

	@Override
	public String toString() {
		return "PoiEntity [id=" + id + ", nome=" + nome + ", coordenadaX=" + coordenadaX + ", coordenaday="
				+ coordenaday + "]";
	}

}
