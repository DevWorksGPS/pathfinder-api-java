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
		this.rutaRepository.save(new Ruta("ruta1", "Madrid", (float)40.5678, (float)-4.1234, (float)41.1234, (float)-4.5678, (float)156, (float)180));
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
		assertTrue(r3.getDistanciaTotal() == (float)156);
		assertTrue(r3.getDuracionTotal() == (float)180);


	}

}
