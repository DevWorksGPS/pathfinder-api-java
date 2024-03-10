package com.pathfinder.business.services.rutas.usecasesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.pathfinder.business.model.route.Ruta;
import com.pathfinder.business.model.route.RutaDTO;
import com.pathfinder.integration.repository.RutaRepository;

@Service
public class UseCaseSearchImpl implements IUseCaseSearch{
    private final RutaRepository rutaRepository;
    public UseCaseSearchImpl(final RutaRepository rutaRepository) {
	this.rutaRepository = rutaRepository;
    }
    
    @Override
    @Transactional
    public List<RutaDTO> search(String ubicacion) {
	List<Ruta> listRuta = new ArrayList<>();
	
	if (StringUtils.isAllBlank(ubicacion))  
	    listRuta = this.rutaRepository.findAll();
	else 
	    listRuta = this.rutaRepository.findByUbicacionContaining(ubicacion);
	
	return listRuta
		.stream()
		.map(Ruta::toTransfer)
		.collect(Collectors.toList());
    }

}
