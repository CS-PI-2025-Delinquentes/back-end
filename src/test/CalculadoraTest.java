package com.pagil.teruel_express.teste;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {
    @Test
    void deveSomarDoisNumerosPositivos() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.somar(5, 3);
        assertEquals(8, resultado, "A soma de 5 e 3 deve ser 8");
    }

    @Test
    void deveSomarComZero(){
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.somar(10, 0);
        assertEquals(10, resultado, "A soma de 10 e 0 deve ser 10");
    }
}
