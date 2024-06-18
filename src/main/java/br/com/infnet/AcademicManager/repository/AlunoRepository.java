package br.com.infnet.AcademicManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.infnet.AcademicManager.model.Aluno;

import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
}
