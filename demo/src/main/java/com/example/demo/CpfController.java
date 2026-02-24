package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verificador")
public class CpfController {

    @GetMapping("/{cpf}")
    public ResponseEntity<String> verificar(@PathVariable String cpf) {


        cpf = cpf.replaceAll("[^0-9]", "");


        if (cpf.length() != 11) {
            return ResponseEntity.badRequest().body("CPF inválido");
        }


        if (cpf.matches("(\\d)\\1{10}")) {
            return ResponseEntity.badRequest().body("CPF inválido");
        }

        try {

            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }

            int digito1 = 11 - (soma % 11);
            if (digito1 >= 10) digito1 = 0;


            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }

            int digito2 = 11 - (soma % 11);
            if (digito2 >= 10) digito2 = 0;

            // Verifica se os dígitos batem
            if (digito1 == (cpf.charAt(9) - '0') &&
                    digito2 == (cpf.charAt(10) - '0')) {

                return ResponseEntity.ok("CPF válido");
            } else {
                return ResponseEntity.badRequest().body("CPF inválido");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao validar CPF");
        }
    }
}