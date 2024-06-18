package br.com.infnet.AcademicManager.service;

import br.com.infnet.AcademicManager.dto.NotaRequest;
import br.com.infnet.AcademicManager.exception.ResourceNotFoundException;
import br.com.infnet.AcademicManager.model.Aluno;
import br.com.infnet.AcademicManager.model.Disciplina;
import br.com.infnet.AcademicManager.model.Nota;
import br.com.infnet.AcademicManager.repository.AlunoRepository;
import br.com.infnet.AcademicManager.repository.DisciplinaRepository;
import br.com.infnet.AcademicManager.repository.NotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NotaServiceTest {

    @InjectMocks
    private NotaService notaService;

    @Mock
    private NotaRepository notaRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAtribuirNotaUUID() {
        UUID alunoId = UUID.randomUUID();
        UUID disciplinaId = UUID.randomUUID();

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);

        NotaRequest notaRequest = new NotaRequest();
        notaRequest.setAlunoId(alunoId);
        notaRequest.setDisciplinaId(disciplinaId);
        notaRequest.setValor(8.0);

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina));
        when(notaRepository.save(any(Nota.class))).thenReturn(new Nota(aluno, disciplina, 8.0));

        Nota nota = notaService.atribuirNotaUUID(notaRequest);

        assertNotNull(nota);
        assertEquals(8.0, nota.getValor());
    }

    @Test
    void testListarAlunosAprovados() {
        UUID disciplinaId = UUID.randomUUID();
        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);

        List<Nota> notas = new ArrayList<>();
        notas.add(new Nota(new Aluno(), disciplina, 8.0));
        notas.add(new Nota(new Aluno(), disciplina, 9.0));

        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina));
        when(notaRepository.findByDisciplinaIdAndValorGreaterThanEqual(disciplinaId, 7.0)).thenReturn(notas);

        List<Nota> result = notaService.listarAlunosAprovados(disciplinaId);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testListarAlunosReprovados() {
        UUID disciplinaId = UUID.randomUUID();
        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);

        List<Nota> notas = new ArrayList<>();
        notas.add(new Nota(new Aluno(), disciplina, 5.0));
        notas.add(new Nota(new Aluno(), disciplina, 6.0));

        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina));
        when(notaRepository.findByDisciplinaIdAndValorLessThan(disciplinaId, 7.0)).thenReturn(notas);

        List<Nota> result = notaService.listarAlunosReprovados(disciplinaId);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testAtribuirNotaUUIDAlunoNotFound() {
        UUID alunoId = UUID.randomUUID();
        UUID disciplinaId = UUID.randomUUID();

        NotaRequest notaRequest = new NotaRequest();
        notaRequest.setAlunoId(alunoId);
        notaRequest.setDisciplinaId(disciplinaId);
        notaRequest.setValor(8.0);

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> notaService.atribuirNotaUUID(notaRequest));
    }

    @Test
    void testAtribuirNotaUUIDDisciplinaNotFound() {
        UUID alunoId = UUID.randomUUID();
        UUID disciplinaId = UUID.randomUUID();

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);

        NotaRequest notaRequest = new NotaRequest();
        notaRequest.setAlunoId(alunoId);
        notaRequest.setDisciplinaId(disciplinaId);
        notaRequest.setValor(8.0);

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> notaService.atribuirNotaUUID(notaRequest));
    }
}
