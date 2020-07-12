package br.com.xyinc.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.xyinc.database.models.PoiEntity;

@Repository
public interface PoiRepository extends JpaRepository<PoiEntity, Integer> {
	public List<PoiEntity> findByCoordenadaXBetweenAndCoordenadaYBetween(Double x1, Double x2, Double y1, Double y2);
}
