package br.com.xyinc.br.com.xyinc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v2/poi")
@Api("/v2/poi")
public class GPSController {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar POI", notes = "Essa operação tem por finalizade realizar cadastros de POIs", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Sucesso ao cadastrar a POI.", response = String.class),
			@ApiResponse(code = 400, message = "Operação inválida."),
			// @ApiResponse(code = 401, message = "Operação não autorizada."),
			@ApiResponse(code = 500, message = "Erro interno ao tentar realizar a operação.") })
	public ResponseEntity<String> cadastrar() {
		try {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
