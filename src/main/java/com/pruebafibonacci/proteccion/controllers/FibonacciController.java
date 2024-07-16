package com.pruebafibonacci.proteccion.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pruebafibonacci.proteccion.entity.ExecutionLog;
import com.pruebafibonacci.proteccion.repository.ExecutionLogRepository;
import com.pruebafibonacci.proteccion.services.EmailService;
import com.pruebafibonacci.proteccion.services.FibonacciService;

import java.util.List;

@RestController
@RequestMapping("/api/fibonacci")
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;

    @Autowired
    private ExecutionLogRepository executionLogRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> getFibonacciSeries(@RequestParam String time) {
        try {
            String[] timeParts = time.split(":");
            int seedX = Character.getNumericValue(timeParts[1].charAt(0));
            int seedY = Character.getNumericValue(timeParts[1].charAt(1));
            int count = Integer.parseInt(timeParts[2]);

            // Validar entradas negativas o cero
            if (seedX <= 0 || seedY <= 0 || count <= 0 ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Semillas y el número de elementos deben ser positivos y mayores que cero.");
            }


            List<Integer> fibonacciSeries = fibonacciService.generateFibonacciSeries(seedX, seedY, count);

            // Guardar en la base de datos
            ExecutionLog log = new ExecutionLog(time, fibonacciSeries.toString());
            executionLogRepository.save(log);

            // Enviar correo
            String subject = "Prueba Técnica - Angel Eduardo Rodriguez Arguello";
            String body = String.format("Hora de generación: %s\nSerie Fibonacci: %s\nEnvio serie fibonacci.", time, fibonacciSeries.toString());
            //emailService.sendEmail("didier.correa@proteccion.com.co", subject, body);
            //emailService.sendEmail("correalondon@gmail.com", subject, body);
            emailService.sendEmail("angel.pro98@gmail.com", subject, body);

            return ResponseEntity.ok(fibonacciSeries);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de hora inválido. Por favor usa el formato HH:MM:SS.");
        }
    }

    @GetMapping
    public ResponseEntity<List<ExecutionLog>> getExecutionLogs() {
        List<ExecutionLog> logs = (List<ExecutionLog>) executionLogRepository.findAll();
        return ResponseEntity.ok(logs);
    }
}
