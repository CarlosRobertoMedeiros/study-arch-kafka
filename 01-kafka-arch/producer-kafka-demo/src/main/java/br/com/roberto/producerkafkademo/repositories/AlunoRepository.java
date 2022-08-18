package br.com.roberto.producerkafkademo.repositories;

import br.com.roberto.producerkafkademo.entities.AlunoEntity;

public interface AlunoRepository {
    void enviarDadosDoAluno(AlunoEntity aluno);
}
