package br.com.infnet.AcademicManager.controller;

import br.com.infnet.AcademicManager.dto.NotaRequest;
import br.com.infnet.AcademicManager.model.Nota;
import br.com.infnet.AcademicManager.service.NotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotaController.class)
public class NotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotaService notaService;

    @Test
    void testAtribuirNotaUUID() throws Exception {
        NotaRequest request = new NotaRequest();
        request.setAlunoId(UUID.randomUUID());
        request.setDisciplinaId(UUID.randomUUID());
        request.setValor(10.0);

        Nota nota = new Nota();
        nota.setId(UUID.randomUUID());
        nota.setValor(10.0);

        when(notaService.atribuirNotaUUID(any(NotaRequest.class))).thenReturn(nota);

        mockMvc.perform(post("/notas/atribuirNotaUUID")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alunoId\": \"3805f540-64cd-4c15-9f2f-17e4c9ad2f20\", \"disciplinaId\": \"0ba06567-e1bc-4441-b01a-f93ef0cee295\", \"valor\": 10.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor").value(10.0));
    }
}
