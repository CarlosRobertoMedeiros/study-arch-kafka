package br.com.roberto.producerkafkaavrodemo.repositories;


import br.com.roberto.producerkafkaavrodemo.entities.StockHistoryEntity;

public interface StockHistoryRepository {
    public void enviarDadosDeHistoricoDoEstoque(StockHistoryEntity stockHistoryEntity);
}
