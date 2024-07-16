package com.pruebafibonacci.proteccion.repository;

import org.springframework.data.repository.CrudRepository;

import com.pruebafibonacci.proteccion.entity.ExecutionLog;

public interface ExecutionLogRepository extends CrudRepository<ExecutionLog, Long> {
}
