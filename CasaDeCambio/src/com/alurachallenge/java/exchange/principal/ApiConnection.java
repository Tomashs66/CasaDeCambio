package com.alurachallenge.java.exchange.principal;

import com.alurachallenge.java.exchange.modelos.RespuestaApi;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {

    private static final String API_KEY = "TU_API_KEY_AQUÍ"; // ← Cambia esto por la tuya

    public double obtenerTasaCambio(String monedaBase, String monedaDestino) {
        double tasaCambio = 0.0;

        try {
            // Construir la URL
            String endpoint = "https://v6.exchangerate-api.com/v6/" + "cadde571efb1ba4a486afacc"+ "/pair/" + monedaBase + "/" + monedaDestino;
            URL url = new URL(endpoint);

            // Abrir conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }

            reader.close();

            // 🟡 Aquí entra Gson
            Gson gson = new Gson();
            RespuestaApi datos = gson.fromJson(respuesta.toString(), RespuestaApi.class);

            if ("success".equals(datos.result)) {
                tasaCambio = datos.conversion_rate;
            } else {
                System.out.println("Error en la respuesta de la API.");
            }

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }

        return tasaCambio;
    }
}

