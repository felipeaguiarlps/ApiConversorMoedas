import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean executando = true;

        HistoricoConversao historico = new HistoricoConversao();
        TaxaConversao taxaConversao = new TaxaConversao();

        while (executando){
            System.out.println("*************************");
            System.out.println("\n📌 Menu de Opções:");
            System.out.println("1 - Realizar nova conversão");
            System.out.println("2 - Ver histórico de conversões");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = sc.nextLine();

            switch (opcao){
                case "1" ->{
                    System.out.println("Possiveis moedas a serem convertidas");
                    System.out.println("""
                    Código moeda    Nome moeda
                    "ARS"	        Argentine Peso
                    "BRL"	        Brazilian Real
                    "CAD"	        Canadian Dollar
                    "EUR"	        Euro
                    "GBP"	        Pound Sterling
                    "USD"	        United States Dollar
                    "CNY"	        Chinese Renminbi
                    "INR"	        Indian Rupee
                    "JPY"	        Japanese Yen
                    "RUB"	        Russian Ruble
                    """);

                    System.out.println("Digite o codigo da moeda parametro: ");
                    String pair1 = sc.nextLine();


                    System.out.println("Digite o codigo da moeda a ser convertido: ");
                    String pair2 = sc.nextLine();

                    Set<String> moedasValidas = Set.of("ARS", "BRL", "CAD", "EUR", "GBP", "USD", "CNY", "INR", "JPY", "RUB");

                    if(!moedasValidas.contains(pair1.toUpperCase()) || !moedasValidas.contains(pair2.toUpperCase()) ){
                        System.out.println("Código inválido, digite apenas códigos apresentados no Menu.");
                        sc.nextLine();
                        continue;
                    }

                    double amount =0.0;
                    try {
                        System.out.println(String.format("Digite o valor a ser convertido de %s para %s", pair1, pair2));
                        amount = sc.nextDouble();
                        sc.nextLine();
                    } catch (InputMismatchException e){
                        System.out.println("Valor inválido. Digite apenas números, ex: 50.78");
                        sc.nextLine();
                        continue;
                    }


                    String s = String.valueOf(taxaConversao.searchApi(pair1, pair2, amount));

                    JsonStructure resultado = taxaConversao.searchApi(pair1, pair2, amount);

                    double resultadoConvertido = amount * resultado.conversionRate();

                    String registro = String.format("Valor convertido: %.2f %s = %.2f %s\n",
                            amount, resultado.baseCode(),
                            resultadoConvertido, resultado.targetCode());
                    System.out.println(registro);

                    historico.adicionar(registro);

                }

                case "2" -> historico.mostrar();

                case "0" -> {
                    executando = false;
                    System.out.println("👋 Encerrando o programa. Até mais!");
                }
                default -> System.out.println("❌ Opção inválida. Tente novamente.");

            }
        }

    }
}
