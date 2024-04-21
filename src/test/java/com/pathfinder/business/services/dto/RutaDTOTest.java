package com.pathfinder.business.services.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.pathfinder.business.model.route.RutaDTO;

class RutaDTOTest {

    @Test
     void testBuilderAndGetters() {
        byte[] imageData = new byte[]{1, 2, 3}; // Ejemplo de datos para la imagen
        
        // Crear una instancia usando el builder
        RutaDTO ruta = RutaDTO.builder()
                              .id(1)
                              .name("Ruta Principal")
                              .ubicacion("Ciudad Central")
                              .origenLatitud(40.712776f)
                              .origenLongitud(-74.005974f)
                              .destinoLatitud(34.052235f)
                              .destinoLongitud(-118.243683f)
                              .image(imageData)
                              .distanciaTotal(120.5f)
                              .duracionTotal(90.0f)
                              .build();

        assertThat(ruta.getId()).isEqualTo(1);
        assertThat(ruta.getName()).isEqualTo("Ruta Principal");
        assertThat(ruta.getUbicacion()).isEqualTo("Ciudad Central");
        assertThat(ruta.getOrigenLatitud()).isEqualTo(40.712776f);
        assertThat(ruta.getOrigenLongitud()).isEqualTo(-74.005974f);
        assertThat(ruta.getDestinoLatitud()).isEqualTo(34.052235f);
        assertThat(ruta.getDestinoLongitud()).isEqualTo(-118.243683f);
        assertThat(ruta.getImage()).isEqualTo(imageData);
        assertThat(ruta.getDistanciaTotal()).isEqualTo(120.5f);
        assertThat(ruta.getDuracionTotal()).isEqualTo(90.0f);
        assertNotNull(RutaDTO.builder().toString());
        assertNotNull(ruta.toString());

    }
}
