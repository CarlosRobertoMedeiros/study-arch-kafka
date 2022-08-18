package br.com.roberto.producerkafkademo.interactors;

import br.com.roberto.producerkafkademo.repositories.AlunoRepository;
import br.com.roberto.producerkafkademo.repositories.MensagemRepository;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.AlunoRequest;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.MensagemRequest;
import br.com.roberto.producerkafkademo.transportlayers.http.mapper.AlunoMapper;
import br.com.roberto.producerkafkademo.transportlayers.http.mapper.MensagemMapper;
import org.springframework.stereotype.Service;

@Service
public class MensagemUseCase {

    private final MensagemRepository mensagemRepository;
    private final AlunoRepository alunoRepository;

    public MensagemUseCase(MensagemRepository mensagemRepository, AlunoRepository alunoRepository) {
        this.mensagemRepository = mensagemRepository;
        this.alunoRepository = alunoRepository;
    }

    public void enviarMensagem(MensagemRequest mensagemRequest) {
        var mensagem = MensagemMapper.INSTANCE.dtoRequestToEntity(mensagemRequest);
        this.mensagemRepository.enviarMensagem(mensagem);
    }

    public void enviarDadosDoAluno(AlunoRequest alunoRequest) {
        var aluno = AlunoMapper.INSTANCE.dtoRequestToEntity(alunoRequest);
        this.alunoRepository.enviarDadosDoAluno(aluno);
    }
}
