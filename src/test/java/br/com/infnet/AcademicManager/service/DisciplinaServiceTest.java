package br.com.infnet.AcademicManager.service;

import br.com.infnet.AcademicManager.dto.DisciplinaRequest;
import br.com.infnet.AcademicManager.exception.ResourceNotFoundException;
import br.com.infnet.AcademicManager.model.Aluno;
import br.com.infnet.AcademicManager.model.Disciplina;
import br.com.infnet.AcademicManager.repository.AlunoRepository;
import br.com.infnet.AcademicManager.repository.DisciplinaRepository;
import br.com.infnet.AcademicManager.repository.NotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DisciplinaServiceTest {

    @InjectMocks
    private DisciplinaService disciplinaService;

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private NotaRepository notaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarDisciplina() {
        DisciplinaRequest request = new DisciplinaRequest();
        request.setNome("Matemática");
        request.setCodigo("MAT101");

        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Matemática");
        disciplina.setCodigo("MAT101");

        when(disciplinaRepository.save(any(Disciplina.class))).thenReturn(disciplina);

        Disciplina result = disciplinaService.criarDisciplina(request);

        assertEquals("Matemática", result.getNome());
        assertEquals("MAT101", result.getCodigo());
    }

    @Test
    void testAlocarAluno() {
        UUID disciplinaId = UUID.randomUUID();
        UUID alunoId = UUID.randomUUID();

        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);

        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina));
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));

        assertDoesNotThrow(() -> disciplinaService.alocarAluno(disciplinaId, alunoId));

        verify(disciplinaRepository, times(1)).save(disciplina);
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    void testAlocarAlunoDisciplinaNotFound() {
        UUID disciplinaId = UUID.randomUUID();
        UUID alunoId = UUID.randomUUID();

        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> disciplinaService.alocarAluno(disciplinaId, alunoId));
    }

    @Test
    void testAlocarAlunoAlunoNotFound() {
        UUID disciplinaId = UUID.randomUUID();
        UUID alunoId = UUID.randomUUID();

        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);

        when(disciplinaRepository.findById(disciplinaId)).thenReturn(Optional.of(disciplina));
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> disciplinaService.alocarAluno(disciplinaId, alunoId));
    }
}
