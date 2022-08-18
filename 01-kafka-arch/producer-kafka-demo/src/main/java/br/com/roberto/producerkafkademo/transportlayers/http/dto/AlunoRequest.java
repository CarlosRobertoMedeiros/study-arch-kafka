package br.com.roberto.producerkafkademo.transportlayers.http.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class AlunoRequest {
    private Long id;
    private String nome;
    private String endereco;

    public AlunoRequest(){};

    public AlunoRequest(Long id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
