package br.com.roberto.producerkafkaavrodemo.transportlayers.http;

import br.com.roberto.producerkafkaavrodemo.interactors.MensagemUseCase;
import br.com.roberto.producerkafkaavrodemo.transportlayers.http.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class MensagemRestImpl {

    private final MensagemUseCase mensagemUseCase;

    public MensagemRestImpl(MensagemUseCase mensagemUseCase) {
        this.mensagemUseCase = mensagemUseCase;
    }

    @PostMapping("/estoque/enviar-dados-historico")
    public ResponseEntity<StockHistoryResponse> enviarDadosDeHistoricoDoEstoque(@RequestBody StockHistoryRequest stockHistoryRequest) {
        //var mensagem = mensagemUseCase.enviarMensagem(mensagemRequest);
        this.mensagemUseCase.enviarDadosDeHistoricoDoEstoque(stockHistoryRequest);
        //var response = MensagemMapper.INSTANCE.modelToDtoResponse(mensagem);
        //return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return null;
    }

}
