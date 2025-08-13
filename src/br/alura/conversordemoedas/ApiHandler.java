package br.alura.conversordemoedas;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiHandler {
    private static final String API_KEY = "1cdb7d6d9c344b9c19addc45";

    public JsonObject getConversionRates(String baseCurrency) throws IOException, InterruptedException {
        // Constrói a URL para a requisição da API
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
        url = url.trim(); // remove espaços no início e no fim

        // Cria um cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Cria uma requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Envia a requisição e obtém a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verifica se a requisição foi bem-sucedida
        if (response.statusCode() != 200) {
            throw new IOException("Falha na requisição à API: " + response.body());
        }

        // Converte a resposta JSON em um objeto Java usando Gson
        return new Gson().fromJson(response.body(), JsonObject.class);
    }
}
