package br.com.infnet.AcademicManager.service;

import br.com.infnet.AcademicManager.dto.NotaRequest;
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
import java.util.UUID;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Nota alocarAlunoEmDisciplina(Aluno aluno, Disciplina disciplina, Double valor) {
        Nota nota = new Nota();
        nota.setAluno(aluno);
        nota.setDisciplina(disciplina);
        nota.setValor(valor);
        return notaRepository.save(nota);
    }

    public List<Nota> listarAlunosAprovados(UUID disciplinaId) {
        return notaRepository.findByDisciplinaIdAndValorGreaterThanEqual(disciplinaId, 7.0);
    }

    public List<Nota> listarAlunosReprovados(UUID disciplinaId) {
        return notaRepository.findByDisciplinaIdAndValorLessThan(disciplinaId, 7.0);
    }

    public Nota atribuirNotaUUID(NotaRequest notaRequest) {
        Aluno aluno = alunoRepository.findById(notaRequest.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(notaRequest.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));

        Nota nota = new Nota();
        nota.setAluno(aluno);
        nota.setDisciplina(disciplina);
        nota.setValor(notaRequest.getValor());

        return notaRepository.save(nota);
    }

    public Nota atualizarNota(UUID notaId, Double valor) {
        Nota nota = notaRepository.findById(notaId)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada"));
        nota.setValor(valor);
        return notaRepository.save(nota);
    }
}
