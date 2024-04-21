package com.pathfinder.integration.especifications;


import org.springframework.data.jpa.domain.Specification;

import com.pathfinder.business.model.route.Ruta;

public class RouteSpecificaction {
    
    private RouteSpecificaction() {}
    
    public static Specification<Ruta> likeUbicacion(final String ubicacion){
	return (root, query, criteriaBuilder) -> {
	    String ubicacionCriteria = ubicacion;
	    if (ubicacionCriteria == null) {
		ubicacionCriteria = "";
	    }
	    return criteriaBuilder.like(root.get("ubicacion") , "%" + ubicacionCriteria + "%");
	};
    }
    
    public static Specification<Ruta> lessThanDistanciaTotal(final int distanciaTotal) {
	return (root, query, criteriaBuilder) -> {
	    if (0 >= distanciaTotal) {
		return null; //No aplicamos criterio
	    }	 
	    return criteriaBuilder.lessThanOrEqualTo(root.get("distanciaTotal"), distanciaTotal);
	};	
    }
    
}
