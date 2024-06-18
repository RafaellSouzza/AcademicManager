package br.com.infnet.AcademicManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.infnet.AcademicManager.model.Disciplina;

import java.util.Optional;
import java.util.UUID;

public interface DisciplinaRepository extends JpaRepository<Disciplina, UUID> {
    Optional<Disciplina> findByNome(String nome);
    Optional<Disciplina> findByCodigo(String codigo);

}
