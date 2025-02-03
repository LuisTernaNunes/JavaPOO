package ConsultaCep;

import ConsultaCep.Servicos.Servicos;
import ConsultaCep.model.Cidade;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        String loop = "S";
        Servicos servicos = new Servicos();
        Scanner scanner = new Scanner(System.in);
        while (!loop.equalsIgnoreCase("N")){
            String cep;
            System.out.println("Digite o cep a ser buscado");
            cep = scanner.nextLine();
            try {
                String json = servicos.httpClient(Integer.valueOf(cep));
                Cidade cidade = new Cidade(servicos.lerJson(json));
                cidade.cadEndereco(servicos.cadEndereco());
                System.out.println(cidade.toString());
                servicos.gravaCidadeJson(cidade);
            }catch (Exception e ){
                System.out.println("Cep inexistente");
            }
            System.out.println("Deseja cadastrar novo enderco ? S/N");
            loop = scanner.nextLine();
        }
    }
}
