package com.pathfinder.integration.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.pathfinder.business.model.route.Ruta;
import com.pathfinder.business.model.route.RutaDTO;

import static org.assertj.core.api.Assertions.assertThat;

 class RutaTest {

    @Test
    void testConstructorWithBasicInfo() {
        Ruta ruta = new Ruta("Ruta A", "Ubicación A");
        assertThat(ruta.getName()).isEqualTo("Ruta A");
        assertThat(ruta.getUbicacion()).isEqualTo("Ubicación A");
        assertEquals(0, ruta.getVersion());
        assertEquals(0, ruta.getId());
        assertNotNull( Ruta.builder().id(0).toString());
        assertNotNull(ruta.toString());
    }

    @Test
    void testConstructorWithCoordinates() {
        Ruta ruta = new Ruta("Ruta B", "Ubicación B", 10.0f, 20.0f, 30.0f, 40.0f);
        assertThat(ruta.getName()).isEqualTo("Ruta B");
        assertThat(ruta.getUbicacion()).isEqualTo("Ubicación B");
        assertThat(ruta.getOrigenLatitud()).isEqualTo(10.0f);
        assertThat(ruta.getOrigenLongitud()).isEqualTo(20.0f);
        assertThat(ruta.getDestinoLatitud()).isEqualTo(30.0f);
        assertThat(ruta.getDestinoLongitud()).isEqualTo(40.0f);
    }

    @Test
    void testConstructorWithDistance() {
        Ruta ruta = new Ruta("Ruta C", "Ubicación C", 10.0f, 20.0f, 30.0f, 40.0f, 50.0f);
        assertThat(ruta.getDistanciaTotal()).isEqualTo(50.0f);
    }

    @Test
    void testConstructorWithDistanceAndDuration() {
        Ruta ruta = new Ruta("Ruta D", "Ubicación D", 10.0f, 20.0f, 30.0f, 40.0f, 50.0f, 60.0f);
        assertThat(ruta.getDistanciaTotal()).isEqualTo(50.0f);
        assertThat(ruta.getDuracionTotal()).isEqualTo(60.0f);
    }

    @Test
    void testToTransferMethod() {
        Ruta ruta = new Ruta("Ruta E", "Ubicación E", 10.0f, 20.0f, 30.0f, 40.0f, 50.0f, 60.0f);
        RutaDTO rutaDTO = ruta.toTransfer();

        assertThat(rutaDTO.getName()).isEqualTo(ruta.getName());
        assertThat(rutaDTO.getUbicacion()).isEqualTo(ruta.getUbicacion());
        assertThat(rutaDTO.getOrigenLatitud()).isEqualTo(ruta.getOrigenLatitud());
        assertThat(rutaDTO.getOrigenLongitud()).isEqualTo(ruta.getOrigenLongitud());
        assertThat(rutaDTO.getDestinoLatitud()).isEqualTo(ruta.getDestinoLatitud());
        assertThat(rutaDTO.getDestinoLongitud()).isEqualTo(ruta.getDestinoLongitud());
        assertThat(rutaDTO.getImage()).isEqualTo(ruta.getImage());
    }
}
