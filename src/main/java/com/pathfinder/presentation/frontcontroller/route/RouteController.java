package com.pathfinder.presentation.frontcontroller.route;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathfinder.business.model.route.RutaDTO;
import com.pathfinder.business.services.rutas.usecasedetail.IUseCaseDetail;
import com.pathfinder.business.services.rutas.usecasesearch.IUseCaseSearch;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/ruta")
@Api(tags = "Rutas")
@AllArgsConstructor
public class RouteController {

    private final IUseCaseSearch useCaseSearch;
    private final IUseCaseDetail useCaseDetail;
  
    @GetMapping("/search")
    @ApiOperation(value = "Realiza una búsqueda de las rutas disponibles", notes = "Este método realiza una búsqueda con la ubicación proporcionada")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Busqueda existosa", response = RutaDTO.class, responseContainer = "List")
    })
    public List<RutaDTO> search(
	    @ApiParam(value = "Ubicacion para realizar la busqueda", defaultValue = "", example = "Madrid", required = false)
	    @RequestParam(required = false, defaultValue = "") String ubicacion,
	    @RequestParam(required = false, defaultValue = "0") int distanciaTotal
	) {   
	return this.useCaseSearch.search(ubicacion, distanciaTotal);
    }

    @GetMapping("/{id}")
    public RutaDTO getRuta(@PathVariable int id) {
        return this.useCaseDetail.getRuta(id);
    }
    
   
}
