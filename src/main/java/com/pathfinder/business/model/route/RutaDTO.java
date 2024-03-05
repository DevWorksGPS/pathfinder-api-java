package com.pathfinder.business.model.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RutaDTO {
	
	private int id;
    private String name;
    private String ubicacion;

    public RutaDTO(int id, String name, String ubicacion) {
		// TODO Auto-generated constructor stub
	}

}

