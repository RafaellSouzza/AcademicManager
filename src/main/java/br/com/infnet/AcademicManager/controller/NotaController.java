package br.com.infnet.AcademicManager.controller;

import br.com.infnet.AcademicManager.dto.NotaRequest;
import br.com.infnet.AcademicManager.model.Nota;
import br.com.infnet.AcademicManager.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping("/atribuirNota")
    public ResponseEntity<Nota> atribuirNota(@Valid @RequestBody Nota nota) {
        Nota novaNota = notaService.alocarAlunoEmDisciplina(nota.getAluno(), nota.getDisciplina(), nota.getValor());
        return ResponseEntity.ok(novaNota);
    }

    @GetMapping("/aprovados/{disciplinaId}")
    public ResponseEntity<List<Nota>> listarAprovados(@PathVariable UUID disciplinaId) {
        List<Nota> notas = notaService.listarAlunosAprovados(disciplinaId);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/reprovados/{disciplinaId}")
    public ResponseEntity<List<Nota>> listarReprovados(@PathVariable UUID disciplinaId) {
        List<Nota> notas = notaService.listarAlunosReprovados(disciplinaId);
        return ResponseEntity.ok(notas);
    }

    @PostMapping("/atribuirNotaUUID")
    public ResponseEntity<Nota> atribuirNotaUUID(@RequestBody NotaRequest notaRequest) {
        Nota createdNota = notaService.atribuirNotaUUID(notaRequest);
        return ResponseEntity.ok(createdNota);
    }

    @PutMapping("/{notaId}")
    public ResponseEntity<Nota> atualizarNota(@PathVariable UUID notaId, @RequestBody Double valor) {
        Nota nota = notaService.atualizarNota(notaId, valor);
        return ResponseEntity.ok(nota);
    }
}
