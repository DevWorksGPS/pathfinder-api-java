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
import com.pathfinder.integration.repository.RutaRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
   
   @BeforeAll
   public void setUp() {
       this.rutaRepository.deleteAll();
       this.rutaRepository.save(new Ruta("Ruta Uno", "Madrid"));  
       this.rutaRepository.save(new Ruta("Ruta Unoo", "Madrid"));       
       this.rutaRepository.save(new Ruta("Ruta Unooo", "Madrid"));       
       this.rutaRepository.save(new Ruta("Ruta Unoooo", "Madrid"));       
       this.rutaRepository.save(new Ruta("Ruta Dos", "Barcelona"));       
       this.rutaRepository.save(new Ruta("Ruta Tres", "Alicante"));       
       this.rutaRepository.save(new Ruta("Ruta Cuatro", "Murcia"));     
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
     	.andExpect(jsonPath("$[0].ubicacion").value("Alicante"));
       
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
     	.andExpect(jsonPath("$[1].name").value("Ruta Unoo"))
     	.andExpect(jsonPath("$[1].id").value(2))
     	.andExpect(jsonPath("$[1].ubicacion").value("Madrid"));
  
       this.mockMvc
    	.perform(
	       get("/ruta/search?ubicacion=dont found name")
	       .contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(content()
             .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(jsonPath("$", hasSize(0)));
 
   
   }
    
}
