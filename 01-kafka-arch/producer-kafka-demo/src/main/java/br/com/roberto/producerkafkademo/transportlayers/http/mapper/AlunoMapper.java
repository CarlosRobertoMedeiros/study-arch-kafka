package br.com.roberto.producerkafkademo.transportlayers.http.mapper;

import br.com.roberto.producerkafkademo.entities.AlunoEntity;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.AlunoRequest;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.AlunoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlunoMapper {
    static final AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);
    AlunoEntity dtoRequestToEntity(final AlunoRequest alunoRequest);
    AlunoResponse modelToDtoResponse(final AlunoEntity alunoEntity);
}
