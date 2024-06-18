package br.com.infnet.AcademicManager.controller;

import br.com.infnet.AcademicManager.dto.AlunoRequest;
import br.com.infnet.AcademicManager.model.Aluno;
import br.com.infnet.AcademicManager.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoService.listarTodos();
        return ResponseEntity.ok(alunos);
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody AlunoRequest alunoRequest) {
        Aluno novoAluno = alunoService.criarAluno(alunoRequest);
        return ResponseEntity.ok(novoAluno);
    }


    @GetMapping("/{alunoId}")
    public ResponseEntity<Aluno> buscarAlunoComNotas(@PathVariable UUID alunoId) {
        Aluno aluno = alunoService.buscarAlunoComNotas(alunoId);
        return ResponseEntity.ok(aluno);
    }
}