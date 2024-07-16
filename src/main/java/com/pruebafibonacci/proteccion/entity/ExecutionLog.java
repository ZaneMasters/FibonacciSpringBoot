package com.pruebafibonacci.proteccion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String executionTime;
    private String fibonacciSeries;

    // Constructores, getters y setters

    public ExecutionLog() {
    }

    public ExecutionLog(String executionTime, String fibonacciSeries) {
        this.executionTime = executionTime;
        this.fibonacciSeries = fibonacciSeries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getFibonacciSeries() {
        return fibonacciSeries;
    }

    public void setFibonacciSeries(String fibonacciSeries) {
        this.fibonacciSeries = fibonacciSeries;
    }
}
