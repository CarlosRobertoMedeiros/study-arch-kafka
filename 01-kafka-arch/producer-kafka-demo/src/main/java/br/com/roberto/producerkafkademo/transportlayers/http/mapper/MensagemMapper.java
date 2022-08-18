package br.com.roberto.producerkafkademo.transportlayers.http.mapper;

import br.com.roberto.producerkafkademo.entities.MensagemEntity;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.MensagemRequest;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.MensagemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MensagemMapper {
    static final MensagemMapper INSTANCE = Mappers.getMapper(MensagemMapper.class);
    MensagemEntity dtoRequestToEntity(final MensagemRequest mensagemRequest);
    MensagemResponse modelToDtoResponse(final MensagemEntity mensagemEntity);
}
