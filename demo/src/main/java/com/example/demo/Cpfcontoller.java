package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class Cpfcontoller {


    @GetMapping("/api/verificador/{cpf}")
    public ResponseEntity<String> validarCpfPath(@PathVariable String cpf) {

        // Remove pontos e traços do CPF
        String cpfLimpo = cpf.replaceAll("\\.", "").replaceAll("-", "");

        // Verifica se tem 11 dígitos
        if (cpfLimpo.length() == 11) {

            int peso = 10;   // Peso começa em 10
            int soma = 0;    // Variável para somar os resultados

            // 🔹 Calcula o primeiro dígito verificador
            for (int i = 0; i < 9; i++) {
                int numero = cpfLimpo.charAt(i) - '0'; // Converte char para número
                soma += numero * peso; // Multiplica pelo peso
                peso--; // Diminui o peso
            }

            // Verifica se o primeiro dígito está correto
            if ((soma * 10) % 11 == cpfLimpo.charAt(9) - '0') {

                peso = 11; // Reinicia peso para o segundo cálculo
                soma = 0;  // Zera soma

                // Calula o segundo dígito verificador
                for (int i = 0; i < 10; i++) {
                    int numero = cpfLimpo.charAt(i) - '0';
                    soma += numero * peso;
                    peso--;
                }

                // Verifica se o segundo dígito está correto
                if ((soma * 10) % 11 == cpfLimpo.charAt(10) - '0') {
                    return ResponseEntity.ok("cpf valido");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("cpf invalido");
    }


    @GetMapping("/api/verificador")
    public ResponseEntity<String> validarCpfParametro(@RequestParam String cpf) {

        String cpfLimpo = cpf.replaceAll("\\.", "").replaceAll("-", "");

        if (cpfLimpo.length() == 11) {

            int peso = 10;
            int soma = 0;

            for (int i = 0; i < 9; i++) {
                int numero = cpfLimpo.charAt(i) - '0';
                soma += numero * peso;
                peso--;
            }

            if ((soma * 10) % 11 == cpfLimpo.charAt(9) - '0') {

                peso = 11;
                soma = 0;

                for (int i = 0; i < 10; i++) {
                    int numero = cpfLimpo.charAt(i) - '0';
                    soma += numero * peso;
                    peso--;
                }

                if ((soma * 10) % 11 == cpfLimpo.charAt(10) - '0') {
                    return ResponseEntity.ok("cpf valido");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("cpf invalido");
    }

    @PostMapping("/api/verificador")
    public ResponseEntity<String> validarCpfPost(@RequestParam String cpf) {

        String cpfLimpo = cpf.replaceAll("\\.", "").replaceAll("-", "");

        if (cpfLimpo.length() == 11) {

            int peso = 10;
            int soma = 0;

            for (int i = 0; i < 9; i++) {
                int numero = cpfLimpo.charAt(i) - '0';
                soma += numero * peso;
                peso--;
            }

            if ((soma * 10) % 11 == cpfLimpo.charAt(9) - '0') {

                peso = 11;
                soma = 0;

                for (int i = 0; i < 10; i++) {
                    int numero = cpfLimpo.charAt(i) - '0';
                    soma += numero * peso;
                    peso--;
                }

                if ((soma * 10) % 11 == cpfLimpo.charAt(10) - '0') {
                    return ResponseEntity.ok("cpf valido");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("cpf invalido");
    }

    // Método principal que inicia o Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(Cpfcontoller.class, args);
    }
}