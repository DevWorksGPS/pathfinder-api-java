package com.pathfinder;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class PathFinderApplicationTests {
    @Autowired
    private ApplicationContext context;
	@Test
	void contextLoads() {
	    PathFinderApplication.main(new String[]{
	                "--spring.main.web-environment=false",
	                "--spring.autoconfigure.exclude=blahblahblah",
	                // Override any other environment properties according to your needs
	        });
	}

}
