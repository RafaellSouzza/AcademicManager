package br.com.infnet.AcademicManager.service;

import br.com.infnet.AcademicManager.dto.AlunoRequest;
import br.com.infnet.AcademicManager.exception.ResourceNotFoundException;
import br.com.infnet.AcademicManager.model.Aluno;
import br.com.infnet.AcademicManager.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno criarAluno(AlunoRequest alunoRequest) {
        Aluno aluno = new Aluno();
        aluno.setId(UUID.randomUUID());
        aluno.setNome(alunoRequest.getNome());
        aluno.setCpf(alunoRequest.getCpf());
        aluno.setEmail(alunoRequest.getEmail());
        aluno.setTelefone(alunoRequest.getTelefone());
        aluno.setEndereco(alunoRequest.getEndereco());
        return alunoRepository.save(aluno);
    }

    public Aluno buscarAlunoComNotas(UUID alunoId) {
        return alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
    }
    public Aluno buscarAlunoPorId(UUID id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + id));
    }
    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }
}
