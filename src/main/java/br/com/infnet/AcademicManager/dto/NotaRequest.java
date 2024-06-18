package br.com.infnet.AcademicManager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NotaRequest {

    @NotNull
    private UUID alunoId;

    @NotNull
    private UUID disciplinaId;

    @NotNull
    private Double valor;

}
