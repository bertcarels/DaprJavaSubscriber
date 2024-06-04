package com.kaboutersoft.group.gtsciais.demo;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTest {

    @Autowired
    private MockMvc mockMvc;

   
   
   // @Test
   // public void appHasAGreeting() {
   //     DemoApplication classUnderTest = new DemoApplication();
   //     assertNotNull("app should have a greeting", classUnderTest.getGreeting());
   //  }

  // @Test 
  // public void appHasAGreeting2() {
  //      DemoApplication classUnderTest = new DemoApplication();
  //      assertNotNull("app should have a greeting", null);
  //   }


   @Autowired
   private ApplicationContext applicationContext;

   // Test method to verify ApplicationContext
   @Test
   public void testApplicationContext() {
       assertNotNull(applicationContext);
       // Perform additional checks on the ApplicationContext if needed
   }


    


}
