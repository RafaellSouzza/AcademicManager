package br.com.infnet.AcademicManager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
public class Nota {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    @JsonIgnoreProperties("notas")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    @JsonIgnoreProperties("notas")
    private Disciplina disciplina;

    @NotNull
    private Double valor;

    public Nota() {}

    public Nota(Aluno aluno, Disciplina disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.valor = 0.0;
    }
    public Nota(Aluno aluno, Disciplina disciplina, double valor) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.valor = valor;
    }
}
