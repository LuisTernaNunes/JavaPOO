package ConsultaCep.model;

import java.util.ArrayList;

public class Cidade {
    private int cep;
    private String nome;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String estado;
    private String regiao;
    private int ibge;
    private int ddd;

    public Cidade(CidadeViaCep cidadeVia) {
        this.cep = Integer.valueOf(cidadeVia.cep().replace("-",""));
        this.nome = cidadeVia.localidade();
        this.estado = cidadeVia.uf();
        this.regiao = cidadeVia.regiao();
        this.ibge = Integer.valueOf(cidadeVia.ibge());
        this.ddd = Integer.valueOf(cidadeVia.ddd());
    }

    @Override
    public String toString() {
        return ("Cidade: "+ this.nome+" "+this.estado);
    }

    public void cadEndereco(ArrayList<String> strings) {
        this.logradouro = strings.get(1);
        this.complemento = strings.get(2);
        this.bairro = strings.get(0);
    }
}
