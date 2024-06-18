package br.com.infnet.AcademicManager.service;

import br.com.infnet.AcademicManager.dto.DisciplinaRequest;
import br.com.infnet.AcademicManager.exception.ResourceNotFoundException;
import br.com.infnet.AcademicManager.model.Aluno;
import br.com.infnet.AcademicManager.model.Disciplina;
import br.com.infnet.AcademicManager.model.Nota;
import br.com.infnet.AcademicManager.repository.AlunoRepository;
import br.com.infnet.AcademicManager.repository.DisciplinaRepository;
import br.com.infnet.AcademicManager.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private NotaRepository notaRepository;

    public Disciplina criarDisciplina(DisciplinaRequest disciplinaRequest) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaRequest.getNome());
        disciplina.setCodigo(disciplinaRequest.getCodigo());
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> findByNome(String nome) {
        return disciplinaRepository.findByNome(nome);
    }

    public Optional<Disciplina> getDisciplinaByCodigo(String codigo) {
        return disciplinaRepository.findByCodigo(codigo);
    }

    public void alocarAluno(UUID disciplinaId, UUID alunoId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        if (!disciplina.getAlunos().contains(aluno)) {
            disciplina.getAlunos().add(aluno);
            aluno.getDisciplinas().add(disciplina);
            alunoRepository.save(aluno);
            disciplinaRepository.save(disciplina);
        }
    }
}
