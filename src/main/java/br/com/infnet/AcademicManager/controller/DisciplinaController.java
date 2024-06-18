package br.com.infnet.AcademicManager.controller;

import br.com.infnet.AcademicManager.dto.DisciplinaRequest;
import br.com.infnet.AcademicManager.model.Aluno;
import br.com.infnet.AcademicManager.model.Disciplina;
import br.com.infnet.AcademicManager.repository.AlunoRepository;
import br.com.infnet.AcademicManager.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaService.listarTodas();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/nome")
    public ResponseEntity<Disciplina> getDisciplinaByNome(@RequestParam String nome) {
        Optional<Disciplina> disciplina = disciplinaService.findByNome(nome);
        if (disciplina.isPresent()) {
            return ResponseEntity.ok(disciplina.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Disciplina> criarDisciplina(@Valid @RequestBody DisciplinaRequest disciplinaRequest) {
        Disciplina novaDisciplina = disciplinaService.criarDisciplina(disciplinaRequest);
        return ResponseEntity.ok(novaDisciplina);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Disciplina> getDisciplinaByCodigo(@PathVariable String codigo) {
        Optional<Disciplina> disciplina = disciplinaService.getDisciplinaByCodigo(codigo);
        return ResponseEntity.ok(disciplina.orElse(null));
    }

    @PostMapping("/{disciplinaId}/alocarAluno/{alunoId}")
    public ResponseEntity<Void> alocarAluno(@PathVariable UUID disciplinaId, @PathVariable UUID alunoId) {
        disciplinaService.alocarAluno(disciplinaId, alunoId);
        return ResponseEntity.ok().build();
    }
}
