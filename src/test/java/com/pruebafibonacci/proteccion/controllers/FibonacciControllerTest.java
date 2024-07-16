package com.pruebafibonacci.proteccion.controllers;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pruebafibonacci.proteccion.entity.ExecutionLog;
import com.pruebafibonacci.proteccion.repository.ExecutionLogRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class FibonacciControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    private ExecutionLogRepository executionLogRepository;

    @Test
    public void testGetFibonacciSeries() throws Exception {
        mockMvc.perform(post("/api/fibonacci")
                .param("time", "12:23:04")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[21,13,8,5,3,2]"));
    }

    @Test
    public void testGetExecutionLogs() throws Exception {
        executionLogRepository.save(new ExecutionLog("12:23:04", "[21,13,8,5,3,2]"));

        mockMvc.perform(get("/api/fibonacci")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'executionTime':'12:23:04','fibonacciSeries':'[21,13,8,5,3,2]'}]"));
    }
}
