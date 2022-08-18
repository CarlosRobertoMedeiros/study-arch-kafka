package br.com.roberto.producerkafkademo.transportlayers.http;

import br.com.roberto.producerkafkademo.interactors.MensagemUseCase;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.AlunoRequest;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.AlunoResponse;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.MensagemRequest;
import br.com.roberto.producerkafkademo.transportlayers.http.dto.MensagemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class MensagemRestImpl {

    private final MensagemUseCase mensagemUseCase;

    public MensagemRestImpl(MensagemUseCase mensagemUseCase) {
        this.mensagemUseCase = mensagemUseCase;
    }

    @PostMapping("/enviar-mensagem-postando-string-simples")
    public ResponseEntity<MensagemResponse> enviarMensagem(@RequestBody MensagemRequest mensagemRequest) {
        //var mensagem = mensagemUseCase.enviarMensagem(mensagemRequest);
        this.mensagemUseCase.enviarMensagem(mensagemRequest);
        //var response = MensagemMapper.INSTANCE.modelToDtoResponse(mensagem);
        //return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return null;
    }

    @PostMapping("/aluno/enviar-dados")
    public ResponseEntity<AlunoResponse> enviarDadosDoAluno(@RequestBody AlunoRequest alunoRequest) {
        //var mensagem = mensagemUseCase.enviarMensagem(mensagemRequest);
        this.mensagemUseCase.enviarDadosDoAluno(alunoRequest);
        //var response = MensagemMapper.INSTANCE.modelToDtoResponse(mensagem);
        //return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return null;
    }

}
