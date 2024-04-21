package com.pathfinder.business.services.rutas.usecasesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pathfinder.business.model.route.Ruta;
import com.pathfinder.business.model.route.RutaDTO;
import com.pathfinder.integration.especifications.RouteSpecificaction;
import com.pathfinder.integration.repository.RutaRepository;

@Service
public class UseCaseSearchImpl implements IUseCaseSearch{
    private final RutaRepository rutaRepository;
    public UseCaseSearchImpl(final RutaRepository rutaRepository) {
	this.rutaRepository = rutaRepository;
    }
    
    @Override
    @Transactional
    public List<RutaDTO> search(String ubicacion, int distanciaTotal) {
	List<Ruta> listRuta = new ArrayList<>();
	
	if (StringUtils.isAllBlank(ubicacion) && distanciaTotal == 0)  
	    listRuta = this.rutaRepository.findAll();
	else 
	    listRuta = this.rutaRepository.findAll(
		    	Specification.where(
		    			RouteSpecificaction.likeUbicacion(ubicacion)
		    				.and
		    			(RouteSpecificaction.lessThanDistanciaTotal(distanciaTotal)))
		    	);    
	    
	return listRuta
		.stream()
		.map(Ruta::toTransfer)
		.collect(Collectors.toList());
    }

}
