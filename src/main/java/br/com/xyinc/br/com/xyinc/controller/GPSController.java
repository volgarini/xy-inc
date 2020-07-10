package br.com.xyinc.br.com.xyinc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.br.com.xyinc.batabase.models.PoiEntity;
import br.com.xyinc.br.com.xyinc.batabase.repository.PoiRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v2/poi")
@Api("/v2/poi")
public class GPSController {
	@Autowired
	private PoiRepository poiRepository;

	@PostMapping(path = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Cadastrar POI", notes = "End-point tem por finalidade realizar cadastros de POIs")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Sucesso ao cadastrar a POI."),
			@ApiResponse(code = 400, message = "Operação inválida."),
			@ApiResponse(code = 401, message = "Operação não autorizada."),
			@ApiResponse(code = 500, message = "Erro interno ao tentar realizar a operação.") })
	public ResponseEntity<String> cadastrar(@Valid @RequestBody List<PoiEntity> pois) {
		try {
			pois.forEach(poi -> poiRepository.save(poi));

			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Listar Todos os POIs", notes = "End-point tem por finalidade listar todos os POIs cadastrados")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso."),
			@ApiResponse(code = 400, message = "Operação inválida."),
			@ApiResponse(code = 401, message = "Operação não autorizada."),
			@ApiResponse(code = 500, message = "Erro interno ao tentar realizar a operação.") })
	public ResponseEntity<List<PoiEntity>> listarTodos() {
		return ResponseEntity.ok(poiRepository.findAll());
	}

	@GetMapping(path = "/proximidade", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Listar os POIs baseado nas coordanadas e distancia enviadas", notes = "End-point tem por finalidade listar todos os POIs por proximidade.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso."),
			@ApiResponse(code = 400, message = "Operação inválida."),
			@ApiResponse(code = 401, message = "Operação não autorizada."),
			@ApiResponse(code = 500, message = "Erro interno ao tentar realizar a operação.") })
	public ResponseEntity<List<PoiEntity>> listarProximidade(@RequestParam Double coordX, @RequestParam Double coordY,
			@RequestParam Double dMAx) {
		return ResponseEntity.ok(poiRepository.findByCoordenadaXBetweenAndCoordenadaYBetween((coordX - dMAx),
				(coordX + dMAx), (coordY - dMAx), (coordY + dMAx)));
	}
}
