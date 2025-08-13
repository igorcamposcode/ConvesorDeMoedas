package br.alura.conversordemoedas;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Set;

public class ConversorMoedas {

    //Conjunto imutável de códigos de moedas permitidas ao acessar o programa.
    private static final Set<String> ALLOWED = Set.of("ARS","BOB","BRL","CLP","COP","USD");
    private final ApiHandler apiHandler;

    public ConversorMoedas() {
        this.apiHandler = new ApiHandler();
    }

    public void convert(double amount, String fromCurrency, String toCurrency) {
        try {
            // Obtém os dados
            JsonObject ratesData = apiHandler.getConversionRates(fromCurrency);
            JsonObject conversionRates = ratesData.getAsJsonObject("conversion_rates");

            // Verifica se a moeda de destino existe nos dados retornados!
            if (!conversionRates.has(toCurrency)) {
                System.out.println("Moeda de destino '" + toCurrency + "' não encontrada.");
                return;
            }

            // Obtém a taxa de conversão...
            double rate = conversionRates.get(toCurrency).getAsDouble();
            double convertedAmount = amount * rate;

            // Formata os números para exibição...
            DecimalFormat formatter = new DecimalFormat("#,##0.00");
            String formattedAmount = formatter.format(amount);
            String formattedConvertedAmount = formatter.format(convertedAmount);

            // Exibe o resultado...
            System.out.println("\n--- Resultado da Conversão ---");
            System.out.println(formattedAmount + " " + fromCurrency + " equivalem a " + formattedConvertedAmount + " " + toCurrency);
            System.out.println("Taxa de câmbio utilizada: 1 " + fromCurrency + " = " + rate + " " + toCurrency);
            System.out.println("----------------------------\n");

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar dados da API. Verifique sua conexão ou a chave da API.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado durante a conversão.");
            e.printStackTrace();
        }
    }
}
