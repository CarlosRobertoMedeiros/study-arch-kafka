package br.com.roberto.producerkafkademo.transportlayers.http.dto;

public class AlunoResponse {
    private Long id;
    private String nome;
    private String endereco;

    public AlunoResponse() {
    }

    public AlunoResponse(Long id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


}
