package com.photocontest.controller;


import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/27/16
 * Time: 3:44 AM
 * To change this template use File | Settings | File Templates.
 */

@ContextConfiguration
@WebAppConfiguration("/applicationContext.xml")
public class AdminControllerTest extends AbstractJUnit4SpringContextTests {
    static final Logger logger = Logger.getLogger(AdminControllerTest.class);

    private MockMvc mockMvc;

    @Before public void setUp(){
        logger.info("BEFORE METHOD STARTED --------------------------------------");
        WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        logger.error(ctx.getDisplayName());
    }

    @Test public void adminLogin() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/adminLogin").accept(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/admin"));
    }

    @Configuration
    public static class TestConfiguration {

        @Bean
        public AdminController simpleController() {
            return new AdminController();
        }
    }
}
