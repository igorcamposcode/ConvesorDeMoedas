package br.alura.conversordemoedas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        ConversorMoedas converter = new ConversorMoedas();
        int option = 0;

        String menu = """
                **************************************************
                Bem-vindo ao Conversor de Moedas!
                
                Escolha uma das opções de conversão:
                
                1) Real Brasileiro (BRL) => Peso argentino (ARS)
                2) Dólar Americano (USD) => Real Brasileiro (BRL)
                3) Real Brasileiro (BRL) => Boliviano boliviano (BOB)
                4) Euro (EUR) => Real Brasileiro (BRL)
                5) Real Brasileiro (BRL) => Peso chileno (CLP)
                6) Libra Esterlina (GBP) => Real Brasileiro (BRL)              
                7) Sair
                **************************************************
                """;

        while (option != 7) {
            System.out.println(menu);
            System.out.print("Digite a opção desejada: ");

            option = scanner.nextInt();

            if (option >= 1 && option <= 6) {
                System.out.print("Digite o valor que deseja converter: ");
                double amount = scanner.nextDouble();

                switch (option) {
                    case 1 -> converter.convert(amount, "BRL", "ARS");
                    case 2 -> converter.convert(amount, "USD", "BRL");
                    case 3 -> converter.convert(amount, "BRL", "BOB");
                    case 4 -> converter.convert(amount, "EUR", "BRL");
                    case 5 -> converter.convert(amount, "BRL", "CLP");
                    case 6 -> converter.convert(amount, "GBP", "BRL");
                }
            } else if (option == 7) {
                System.out.println("Obrigado por utilizar o Conversor de Moedas! Até maiiiis...");
            } else {
                System.out.println("Opção inválida. Por favor, tente novamente.");
            }

        }

        scanner.close();
    }
}