package com.pathfinder.integration.specification;

import org.springframework.data.jpa.domain.Specification;

import com.pathfinder.business.model.route.Ruta;


public class RutaSpecificationFilter {
     
    public static Specification<Ruta> filterByName(String ubicacion){
	return (root, query, cb) -> {
	    return cb.like(root.get("ubicacion"), "%" + ubicacion + "%");
	};
    }
    
    public static Specification<Ruta> filterByDuration(int duration){
   	return (root, query, cb) -> {
   	    return cb.greaterThan(root.get("duracion"), duration);
   	};
    }
}
    

