package com.pathfinder.business.services.rutas.usecasedetail;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pathfinder.business.model.route.Ruta;
import com.pathfinder.business.model.route.RutaDTO;
import com.pathfinder.integration.repository.RutaRepository;

@Service
public class UseCaseDetailImpl implements IUseCaseDetail{

    private final RutaRepository rutaRepository;
    
    public UseCaseDetailImpl(final RutaRepository rutaRepository){
        this.rutaRepository = rutaRepository;
    }

    @Override
    public RutaDTO getRuta(final int id) {
        Optional<Ruta> optRuta = this.rutaRepository.findById(id); 
        RutaDTO rutaDTO = null;
        if (optRuta.isPresent()) 
            rutaDTO = optRuta.get().toTransfer();

        return rutaDTO;
    }
    
}
