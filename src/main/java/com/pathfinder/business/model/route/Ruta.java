package com.pathfinder.business.model.route;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String ubicacion;
    private float origenLatitud;
    private float origenLongitud;
    private float destinoLatitud;
    private float destinoLongitud;
    @Lob
    private byte[] image;
    @Version
    private int version;
    
    public Ruta(String name, String ubicacion) {
	this.name = name;
	this.ubicacion = ubicacion;
    }
    
    public RutaDTO toTransfer() {
        return RutaDTO.builder()
                        .id(this.id)
                        .name(this.name)
                        .ubicacion(this.ubicacion)
                        .origenLatitud(this.origenLatitud)
                        .origenLongitud(this.origenLongitud)
                        .destinoLatitud(this.destinoLatitud)
                        .destinoLongitud(this.destinoLongitud)
                        .image(this.image)
                        .build();
    }
    
}
