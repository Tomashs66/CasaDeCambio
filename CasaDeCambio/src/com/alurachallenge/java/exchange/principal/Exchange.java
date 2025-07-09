package com.alurachallenge.java.exchange.principal;

import com.alurachallenge.java.exchange.calculos.Conversion;
import java.util.Scanner;

public class Exchange {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== CONVERSOR DE DIVISAS =====");

        System.out.print("Moneda base (ej: USD): ");
        String monedaBase = scanner.nextLine().toUpperCase();

        System.out.print("Moneda destino (ej: COP): ");
        String monedaDestino = scanner.nextLine().toUpperCase();

        System.out.print("Cantidad a convertir: ");
        double cantidad = scanner.nextDouble();

        // 1. Obtener tasa de la API
        ApiConnection api = new ApiConnection();
        double tasa = api.obtenerTasaCambio(monedaBase, monedaDestino);

        // Validar si se obtuvo una tasa válida
        if (tasa == 0.0) {
            System.out.println("No se pudo obtener la tasa de cambio.");
            return;
        }

        // 2. Calcular la conversión
        Conversion conversion = new Conversion();
        double resultado = conversion.convertir(cantidad, tasa);

        // 3. Mostrar resultado
        System.out.printf("Resultado: %.2f %s = %.2f %s\n",
                cantidad, monedaBase, resultado, monedaDestino);

        System.out.println("================================");
    }
}

