package com.pathfinder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

@ExtendWith(MockitoExtension.class)
class ServletInitializerTest {

    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    @Test
    void testIt() {
      ServletInitializer servletInitializer = new ServletInitializer();
      when(springApplicationBuilder.sources(PathFinderApplication.class)).thenReturn(springApplicationBuilder);
    
      SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);
    
      verify(springApplicationBuilder).sources(PathFinderApplication.class);
      assertEquals(springApplicationBuilder,result);
    }

}