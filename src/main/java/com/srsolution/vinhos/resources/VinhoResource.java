package com.srsolution.vinhos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srsolution.vinhos.model.Vinho;
import com.srsolution.vinhos.repository.Vinhos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "APIRESTfull de Vinhos")
@RestController
@RequestMapping(value="/vinhos/api/v1")
public class VinhoResource {

	@Autowired
	Vinhos vinhos;
	
	@ApiOperation(value="retorna uma lista de vinhos cadastrados.")
	@GetMapping("/vinhos")
	public List<Vinho> todosOsVinhos(){	
		return vinhos.findAll();
	}
	

	
}
