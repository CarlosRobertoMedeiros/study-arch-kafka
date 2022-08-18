package br.com.roberto.producerkafkademo.entities;

import lombok.Data;


public class MensagemEntity {
    private String mensagem;

    public MensagemEntity(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
