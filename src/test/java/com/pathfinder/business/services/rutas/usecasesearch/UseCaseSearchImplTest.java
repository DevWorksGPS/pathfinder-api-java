package com.pathfinder.business.services.rutas.usecasesearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pathfinder.business.model.route.Ruta;
import com.pathfinder.business.model.route.RutaDTO;
import com.pathfinder.integration.repository.RutaRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UseCaseSearchImplTest {

    @Autowired
    private RutaRepository rutaRepository;
    private IUseCaseSearch useCaseSearch;
    
    @SuppressWarnings("null")
    @BeforeAll
    public void setUp() {
	this.rutaRepository.save(new Ruta("Ruta 1", "Madrid"));
	this.rutaRepository.save(new Ruta("Ruta 2", "Barcelona"));
	this.rutaRepository.save(new Ruta("Ruta 3", "Alicante"));
	byte[] b = { new Byte("1")};
	this.rutaRepository.save(Ruta.builder()
					.name("Ruta 1")
					.image(b)
					.destinoLatitud(0)
					.origenLatitud(0)
					.origenLongitud(0)
					.destinoLongitud(0)
					.duracionTotal(144)
					.version(1)
					.ubicacion("Albacete")
					.distanciaTotal(144)
					.build()
		);
	
	this.useCaseSearch = new UseCaseSearchImpl(rutaRepository);
    }
    
    @Test
    void searchEmptyNameRoute() {
	List<RutaDTO> list = this.useCaseSearch.search("", 0);
	assertTrue(list.size() > 0);
	
	list = this.useCaseSearch.search(null, 0);
	assertTrue(list.size() > 0);
	
	list = this.useCaseSearch.search(null, 1);
	assertTrue(list.size() > 0);
	
	list = this.useCaseSearch.search("     ", 0);	
	assertTrue(list.size() > 0);
	
	list = this.useCaseSearch.search("Albacete", 150);
	assertEquals(list.size(), 1);
    }
    
    @Test 
    void searchEspicifNameRoute() {
	List<RutaDTO> list = this.useCaseSearch.search("Madrid", 0);
	assertTrue(list.size() > 0);
    }
}
