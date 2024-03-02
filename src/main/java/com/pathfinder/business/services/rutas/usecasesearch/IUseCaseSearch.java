package com.pathfinder.business.services.rutas.usecasesearch;

import java.util.List;

import com.pathfinder.business.model.route.RutaDTO;

public interface IUseCaseSearch {

    List<RutaDTO> search(final String nameRoute);
    
}
