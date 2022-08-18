package br.com.roberto.producerkafkaavrodemo.transportlayers.http.mapper;

import br.com.roberto.producerkafkaavrodemo.entities.StockHistoryEntity;
import br.com.roberto.producerkafkaavrodemo.transportlayers.http.dto.StockHistoryRequest;
import br.com.roberto.producerkafkaavrodemo.transportlayers.http.dto.StockHistoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockHistoryMapper {
    static final StockHistoryMapper INSTANCE = Mappers.getMapper(StockHistoryMapper.class);
    StockHistoryEntity dtoRequestToEntity(final StockHistoryRequest stockHistoryRequest);
    StockHistoryResponse modelToDtoResponse(final StockHistoryEntity stockHistoryEntity);
}
