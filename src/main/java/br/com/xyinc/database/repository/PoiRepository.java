package br.com.xyinc.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.xyinc.database.models.PoiEntity;

@Repository
public interface PoiRepository extends JpaRepository<PoiEntity, Integer> {
	public List<PoiEntity> findByCoordenadaXBetweenAndCoordenadaYBetween(Integer x1, Integer x2, Integer y1, Integer y2);
}
