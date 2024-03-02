package com.pathfinder.business.model.route;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Version
    private int version;
    
    public Ruta(String name, String ubicacion) {
	this.name = name;
	this.ubicacion = ubicacion;
    }
    
    public RutaDTO toTransfer() {
	return new RutaDTO(id, name, ubicacion);
    }
    
}
