package br.com.infnet.AcademicManager.repository;

import br.com.infnet.AcademicManager.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotaRepository extends JpaRepository<Nota, UUID> {
    List<Nota> findByDisciplinaIdAndValorGreaterThanEqual(UUID disciplinaId, Double valor);

    List<Nota> findByDisciplinaIdAndValorLessThan(UUID disciplinaId, Double valor);
}
