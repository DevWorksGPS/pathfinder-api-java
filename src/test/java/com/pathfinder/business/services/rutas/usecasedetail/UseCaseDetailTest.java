package com.pathfinder.business.services.rutas.usecasedetail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
class UseCaseDetailTest {
	
	@Autowired
	private RutaRepository rutaRepository;
	private IUseCaseDetail useCaseDetail;
	
	@BeforeAll
	public void setUp() {
		this.rutaRepository.deleteAll();
		this.useCaseDetail = new UseCaseDetailImpl(rutaRepository);
	}
	
	@Test
	void getRuta() {
		this.rutaRepository.save(new Ruta("ruta1", "Madrid", (float)40.5678, (float)-4.1234, (float)41.1234, (float)-4.5678));
		RutaDTO r = this.useCaseDetail.getRuta(0);
		assertNull(r);
		RutaDTO t = this.useCaseDetail.getRuta(-1);
		assertNull(t);
		List<Ruta> list = this.rutaRepository.findByUbicacionContaining("Madrid");
		assertFalse(list.isEmpty());
		RutaDTO r3 = this.useCaseDetail.getRuta(list.get(0).getId());
		assertNotNull(r3);
		assertTrue(r3.getId() > 0);
		assertEquals(r3.getName(), "ruta1");
		assertEquals(r3.getUbicacion(), "Madrid");
		assertTrue(r3.getOrigenLatitud() == (float)40.5678);
		assertTrue(r3.getOrigenLongitud() == (float)-4.1234);
		assertTrue(r3.getDestinoLatitud() == (float)41.1234);
		assertTrue(r3.getDestinoLongitud() == (float)-4.5678);
		
		this.rutaRepository.save(new Ruta("ruta2", "Valencia", (float)45.5678, (float)-3.1234, (float)46.1234, (float)-3.5678, (float)156));
		List<Ruta> list1 = this.rutaRepository.findByUbicacionContaining("Valencia");
		assertFalse(list1.isEmpty());
		RutaDTO r1 = this.useCaseDetail.getRuta(list1.get(0).getId());
		assertNotNull(r1);
		assertTrue(r1.getId() > 0);
		assertEquals(r1.getName(), "ruta2");
		assertEquals(r1.getUbicacion(), "Valencia");
		assertTrue(r1.getOrigenLatitud() == (float)45.5678);
		assertTrue(r1.getOrigenLongitud() == (float)-3.1234);
		assertTrue(r1.getDestinoLatitud() == (float)46.1234);
		assertTrue(r1.getDestinoLongitud() == (float)-3.5678);
		assertTrue(r1.getDistanciaTotal() == (float)156);
		
		this.rutaRepository.save(new Ruta("ruta3", "Barcelona", (float)50.5678, (float)-5.1234, (float)51.1234, (float)-5.5678, (float)175, (float)180));
		List<Ruta> list2 = this.rutaRepository.findByUbicacionContaining("Barcelona");
		assertFalse(list2.isEmpty());
		RutaDTO r2 = this.useCaseDetail.getRuta(list2.get(0).getId());
		assertNotNull(r2);
		assertTrue(r2.getId() > 0);
		assertEquals(r2.getName(), "ruta3");
		assertEquals(r2.getUbicacion(), "Barcelona");
		assertTrue(r2.getOrigenLatitud() == (float)50.5678);
		assertTrue(r2.getOrigenLongitud() == (float)-5.1234);
		assertTrue(r2.getDestinoLatitud() == (float)51.1234);
		assertTrue(r2.getDestinoLongitud() == (float)-5.5678);
		assertTrue(r2.getDistanciaTotal() == (float)175);
		assertTrue(r2.getDuracionTotal() == (float)180);


	}

}
