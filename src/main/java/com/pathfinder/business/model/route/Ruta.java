package com.pathfinder.business.model.route;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private float distanciaTotal;
    private float duracionTotal;
    @Version
    private int version;
    
    public Ruta(String name, String ubicacion) {
	this.name = name;
	this.ubicacion = ubicacion;
    }
    
    public Ruta(String name, String ubicacion, float origenLatitud, float origenLongitud, float destinoLatitud,
	    float destinoLongitud) {
	super();
	this.name = name;
	this.ubicacion = ubicacion;
	this.origenLatitud = origenLatitud;
	this.origenLongitud = origenLongitud;
	this.destinoLatitud = destinoLatitud;
	this.destinoLongitud = destinoLongitud;
    }
    public Ruta(String name, String ubicacion, float origenLatitud, float origenLongitud, float destinoLatitud,
    	    float destinoLongitud,float distanciaTotal) {
    	super();
    	this.name = name;
    	this.ubicacion = ubicacion;
    	this.origenLatitud = origenLatitud;
    	this.origenLongitud = origenLongitud;
    	this.destinoLatitud = destinoLatitud;
    	this.destinoLongitud = destinoLongitud;
    	this.distanciaTotal=distanciaTotal;
     }
    public Ruta(String name, String ubicacion, float origenLatitud, float origenLongitud, float destinoLatitud,
    	    float destinoLongitud,float distanciaTotal,float duracionTotal) {
    	super();
    	this.name = name;
    	this.ubicacion = ubicacion;
    	this.origenLatitud = origenLatitud;
    	this.origenLongitud = origenLongitud;
    	this.destinoLatitud = destinoLatitud;
    	this.destinoLongitud = destinoLongitud;
    	this.distanciaTotal=distanciaTotal;
    	this.duracionTotal=duracionTotal;
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
