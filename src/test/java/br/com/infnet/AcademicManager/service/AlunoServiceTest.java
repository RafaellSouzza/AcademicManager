package br.com.infnet.AcademicManager.service;

import br.com.infnet.AcademicManager.dto.AlunoRequest;
import br.com.infnet.AcademicManager.model.Aluno;
import br.com.infnet.AcademicManager.repository.AlunoRepository;
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

public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarAluno() {
        AlunoRequest alunoRequest = new AlunoRequest();
        alunoRequest.setNome("Rafael");
        alunoRequest.setCpf("12345678901");
        alunoRequest.setEmail("rafael@gmail.com");
        alunoRequest.setTelefone("1234567890");
        alunoRequest.setEndereco("Rua Exemplo, 123");

        Aluno aluno = new Aluno();
        aluno.setNome("Rafael");
        aluno.setCpf("12345678901");
        aluno.setEmail("rafael@gmail.com");
        aluno.setTelefone("1234567890");
        aluno.setEndereco("Rua Exemplo, 123");

        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

        Aluno createdAluno = alunoService.criarAluno(alunoRequest);

        assertNotNull(createdAluno);
        assertEquals("Rafael", createdAluno.getNome());
        assertEquals("12345678901", createdAluno.getCpf());
        assertEquals("rafael@gmail.com", createdAluno.getEmail());
        assertEquals("1234567890", createdAluno.getTelefone());
        assertEquals("Rua Exemplo, 123", createdAluno.getEndereco());
    }

    @Test
    void testBuscarAlunoPorId() {
        UUID alunoId = UUID.randomUUID();
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        aluno.setNome("Rafael");

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));

        Aluno foundAluno = alunoService.buscarAlunoPorId(alunoId);

        assertNotNull(foundAluno);
        assertEquals(alunoId, foundAluno.getId());
        assertEquals("Rafael", foundAluno.getNome());
    }

    @Test
    void testListarTodosAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Rafael");
        Aluno aluno2 = new Aluno();
        aluno2.setNome("João");

        alunos.add(aluno1);
        alunos.add(aluno2);

        when(alunoRepository.findAll()).thenReturn(alunos);

        List<Aluno> result = alunoService.listarTodosAlunos();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Rafael", result.get(0).getNome());
        assertEquals("João", result.get(1).getNome());
    }
}
