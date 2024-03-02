package com.pathfinder.business.services.rutas.usecasesearch;

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
    
    @BeforeAll
    public void setUp() {
	this.rutaRepository.save(new Ruta("Ruta 1", "Madrid"));
	this.rutaRepository.save(new Ruta("Ruta 2", "Barcelona"));
	this.rutaRepository.save(new Ruta("Ruta 3", "Alicante"));
	this.useCaseSearch = new UseCaseSearchImpl(rutaRepository);
    }
    
    @Test
    void searchEmptyNameRoute() {
	List<RutaDTO> list = this.useCaseSearch.search("");
	assertTrue(list.size() > 0);
	
	list = this.useCaseSearch.search(null);
	assertTrue(list.size() > 0);
	
	list = this.useCaseSearch.search("     ");	
	assertTrue(list.size() > 0);
    }
    
    @Test 
    void searchEspicifNameRoute() {
	List<RutaDTO> list = this.useCaseSearch.search("Ruta 1");
	assertTrue(list.size() > 0);
    }
}
