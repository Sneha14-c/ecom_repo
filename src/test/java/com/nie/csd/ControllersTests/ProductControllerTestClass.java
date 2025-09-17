package com.nie.csd.ControllersTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.nie.csd.controllers.ProductController;

//@SpringBootTest
@WebMvcTest(ProductController.class)


public class ProductControllerTestClass {
    @Autowired
    ProductController controller;

    @Autowired
    MockMvc mockMvc;

    
    
    @Test
    // void contextLoads() {
    //      assert(controller!=null);   it is needed when you use test case
    //}
    public void testSayHello() throws Exception {

        // String result=controller.sayHello();
        // String expected ="HELLO";
        // assert(result.equals(expected));
        mockMvc.perform(get("/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string("HELLO"));

}
}


