package com.pathfinder.presentation.frontcontroller.route;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.pathfinder.business.model.route.Ruta;
import com.pathfinder.business.model.route.RutaDTO;
import com.pathfinder.business.services.rutas.usecasedetail.IUseCaseDetail;
import com.pathfinder.business.services.rutas.usecasesearch.IUseCaseSearch;
import com.pathfinder.integration.repository.RutaRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RouteControllerTest {

   @Autowired
   private MockMvc mockMvc;
    
   @Autowired
   private RutaRepository rutaRepository;
   @Autowired
   private IUseCaseDetail useCaseDetail;
   @Autowired
   private IUseCaseSearch useCaseSearch;
   
   
   private RouteController rutaController;
   
   
   @BeforeAll
   public void setUp() {
       this.rutaRepository.deleteAll();
       this.rutaRepository.save(new Ruta("Ruta Uno", "Madrid", (float)40.5678, (float)-4.1234, (float)41.1234, (float)-4.5678));  
       this.rutaRepository.save(new Ruta("Ruta Unoo", "Madrid", (float)41.5678, (float)-5.1234, (float)42.1234, (float)-5.5678));       
       this.rutaRepository.save(new Ruta("Ruta Unooo", "Madrid", (float)39.5678, (float)-3.1234, (float)40.1234, (float)-3.5678));       
       this.rutaRepository.save(new Ruta("Ruta Unoooo", "Madrid", (float)42.5678, (float)-4.5678, (float)43.1234, (float)-4.1234));       
       this.rutaRepository.save(new Ruta("Ruta Dos", "Barcelona", (float)50.5678, (float)-1.1234, (float)51.1234, (float)-1.5678, (float)155));       
       this.rutaRepository.save(new Ruta("Ruta Tres", "Alicante", (float)49.5678, (float)-2.1234, (float)50.1234, (float)-2.5678));       
       this.rutaRepository.save(new Ruta("Ruta Cuatro", "Murcia", (float)51.5678, (float)-6.1234, (float)52.1234, (float)-6.5678, (float)153, (float)195));  
       this.rutaController = new RouteController(useCaseSearch, useCaseDetail);
   }

   @Test
   void searchRouteEndpoint() throws Exception {
       this.mockMvc
       	.perform(
	       get("/ruta/search")
	       .contentType(MediaType.APPLICATION_JSON))
       	.andExpect(status().isOk())
       	.andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
       	.andExpect(jsonPath("$", hasSize(greaterThan(0))));
       
       this.mockMvc
      	.perform(
	       get("/ruta/search?ubicacion=Alicante")
	       .contentType(MediaType.APPLICATION_JSON))
      	.andExpect(status().isOk())
      	.andExpect(content()
               .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      	.andExpect(jsonPath("$", hasSize(1)))
      	.andExpect(jsonPath("$[0].name").value("Ruta Tres"))
      	.andExpect(jsonPath("$[0].id").value(6))
     	.andExpect(jsonPath("$[0].ubicacion").value("Alicante"))
     	.andExpect(jsonPath("$[0].origenLatitud").value(49.5678))
     	.andExpect(jsonPath("$[0].origenLongitud").value(-2.1234))
     	.andExpect(jsonPath("$[0].destinoLatitud").value(50.1234))
     	.andExpect(jsonPath("$[0].destinoLongitud").value(-2.5678));
       
       this.mockMvc
     	.perform(
	       get("/ruta/search?ubicacion=Madrid")
	       .contentType(MediaType.APPLICATION_JSON))
     	.andExpect(status().isOk())
     	.andExpect(content()
              .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
     	.andExpect(jsonPath("$", hasSize(4)))
     	.andExpect(jsonPath("$[0].name").value("Ruta Uno"))
     	.andExpect(jsonPath("$[0].id").value(1))
     	.andExpect(jsonPath("$[0].ubicacion").value("Madrid"))
     	.andExpect(jsonPath("$[0].origenLatitud").value(40.5678))
     	.andExpect(jsonPath("$[0].origenLongitud").value(-4.1234))
     	.andExpect(jsonPath("$[0].destinoLatitud").value(41.1234))
     	.andExpect(jsonPath("$[0].destinoLongitud").value(-4.5678))
     	.andExpect(jsonPath("$[1].name").value("Ruta Unoo"))
     	.andExpect(jsonPath("$[1].id").value(2))
     	.andExpect(jsonPath("$[1].ubicacion").value("Madrid"))
     	.andExpect(jsonPath("$[1].origenLatitud").value(41.5678))
     	.andExpect(jsonPath("$[1].origenLongitud").value(-5.1234))
     	.andExpect(jsonPath("$[1].destinoLatitud").value(42.1234))
     	.andExpect(jsonPath("$[1].destinoLongitud").value(-5.5678));
  
       this.mockMvc
    	.perform(
	       get("/ruta/search?ubicacion=dont found name")
	       .contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(content()
             .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$", hasSize(0)));
 
   
   }
   
   @Test
   void getRutaTest() throws Exception {
       List<RutaDTO> listRuta = this.rutaController.search("Madrid");       
       assertFalse(listRuta.isEmpty());
       String endpoint = "/ruta/" + listRuta.get(0).getId();
       final RutaDTO r = listRuta.get(0);
       this.mockMvc.perform(get(endpoint))
	   .andExpect(status().isOk())
	   .andExpect(content()
			   .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	   .andExpect(jsonPath("$.id").value(r.getId()))
	   .andExpect(jsonPath("$.name").value(r.getName()))
	   .andExpect(jsonPath("$.ubicacion").value(r.getUbicacion()))
	   .andExpect(jsonPath("$.origenLatitud").value(r.getOrigenLatitud()))
	   .andExpect(jsonPath("$.origenLongitud").value(r.getOrigenLongitud()))
	   .andExpect(jsonPath("$.destinoLatitud").value(r.getDestinoLatitud()))
	   .andExpect(jsonPath("$.destinoLongitud").value(r.getDestinoLongitud()));
	   
       List<RutaDTO> listaRuta = this.rutaController.search("Barcelona");       
       assertFalse(listaRuta.isEmpty());
       String endpoint1 = "/ruta/" + listaRuta.get(0).getId();
       final RutaDTO r1 = listaRuta.get(0);
       this.mockMvc.perform(get(endpoint1))
	   .andExpect(status().isOk())
	   .andExpect(content()
			   .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	   .andExpect(jsonPath("$.id").value(r1.getId()))
	   .andExpect(jsonPath("$.name").value(r1.getName()))
	   .andExpect(jsonPath("$.ubicacion").value(r1.getUbicacion()))
	   .andExpect(jsonPath("$.origenLatitud").value(r1.getOrigenLatitud()))
	   .andExpect(jsonPath("$.origenLongitud").value(r1.getOrigenLongitud()))
	   .andExpect(jsonPath("$.destinoLatitud").value(r1.getDestinoLatitud()))
	   .andExpect(jsonPath("$.destinoLongitud").value(r1.getDestinoLongitud()))
	   .andExpect(jsonPath("$.distanciaTotal").value(r1.getDistanciaTotal()));
       
       List<RutaDTO> listaRuta1 = this.rutaController.search("Murcia");       
       assertFalse(listaRuta1.isEmpty());
       String endpoint2 = "/ruta/" + listaRuta1.get(0).getId();
       final RutaDTO r2 = listaRuta1.get(0);
       this.mockMvc.perform(get(endpoint2))
	   .andExpect(status().isOk())
	   .andExpect(content()
			   .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	   .andExpect(jsonPath("$.id").value(r2.getId()))
	   .andExpect(jsonPath("$.name").value(r2.getName()))
	   .andExpect(jsonPath("$.ubicacion").value(r2.getUbicacion()))
	   .andExpect(jsonPath("$.origenLatitud").value(r2.getOrigenLatitud()))
	   .andExpect(jsonPath("$.origenLongitud").value(r2.getOrigenLongitud()))
	   .andExpect(jsonPath("$.destinoLatitud").value(r2.getDestinoLatitud()))
	   .andExpect(jsonPath("$.destinoLongitud").value(r2.getDestinoLongitud()))
	   .andExpect(jsonPath("$.distanciaTotal").value(r2.getDistanciaTotal()))
	   .andExpect(jsonPath("$.duracionTotal").value(r2.getDuracionTotal()));
       
	
   }
    
}
