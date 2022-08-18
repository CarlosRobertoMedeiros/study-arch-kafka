package br.com.roberto.producerkafkademo.repositories;


import br.com.roberto.producerkafkademo.entities.MensagemEntity;

public interface MensagemRepository {
    void enviarMensagem(MensagemEntity mensagem);

}
