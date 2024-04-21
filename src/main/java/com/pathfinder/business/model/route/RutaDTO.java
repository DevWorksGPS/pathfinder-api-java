package com.pathfinder.business.model.route;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RutaDTO {
	
    private int id;
    private String name;
    private String ubicacion;
    private float origenLatitud;
    private float origenLongitud;
    private float destinoLatitud;
    private float destinoLongitud;
    private byte[] image;
    @JsonIgnore
    private float distanciaTotal;
    @JsonIgnore
    private float duracionTotal;

}

