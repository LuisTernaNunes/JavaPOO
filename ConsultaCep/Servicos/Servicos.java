package ConsultaCep.Servicos;

import ConsultaCep.model.Cidade;
import ConsultaCep.model.CidadeViaCep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Servicos {


    public String httpClient(int cep) throws IOException, InterruptedException {
        String json = "";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/"+cep+"/json/"))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        json = response.body();
        return json;
    }

    public CidadeViaCep lerJson(String json){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.fromJson(json, CidadeViaCep.class);
    }
    public ArrayList<String> cadEndereco(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual o seu bairo ?");
        String bairroT = scanner.nextLine();
        System.out.println("Qual o seu logradouro");
        String logradouroT = scanner.nextLine();
        System.out.println("Informe um complemento");
        String complementoT = scanner.nextLine();
        ArrayList<String> cad = new ArrayList<>();
        cad.add(bairroT);
        cad.add(logradouroT);
        cad.add(complementoT);
        return cad;
    }

    public void gravaCidadeJson(Cidade cidade) throws IOException {
        FileWriter file = new FileWriter("enderecos.json", true);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        file.write(gson.toJson(cidade));
        file.close();
    }
}
