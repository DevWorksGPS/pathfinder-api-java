package com.pathfinder.presentation.frontcontroller.route;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pathfinder.business.model.route.RutaDTO;
import com.pathfinder.business.services.rutas.usecasesearch.IUseCaseSearch;



@RestController
@RequestMapping("/ruta")
public class RouteController {

    private final IUseCaseSearch useCaseSearch;

    
    public RouteController(final IUseCaseSearch useCaseSearch) {
	this.useCaseSearch = useCaseSearch;
    }
    
    @GetMapping("/search")
    @ResponseBody
    public List<RutaDTO> search(@RequestParam(required = false, defaultValue = "") String ubicacion) {
	    return this.useCaseSearch.search(ubicacion);
    }
   
}
