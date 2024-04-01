package com.pathfinder.business.model.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RutaDTO {
	
    private int id;
    private String name;
    private String ubicacion;
    private float origenLatitud;
    private float origenLongitud;
    private float destinoLatitud;
    private float destinoLongitud;
    private float distanciaTotal;
    private float duracionTotal;

}

