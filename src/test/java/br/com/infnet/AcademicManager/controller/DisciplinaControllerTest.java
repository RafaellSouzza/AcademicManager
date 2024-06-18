package br.com.infnet.AcademicManager.controller;

import br.com.infnet.AcademicManager.dto.DisciplinaRequest;
import br.com.infnet.AcademicManager.model.Disciplina;
import br.com.infnet.AcademicManager.service.DisciplinaService;
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

@WebMvcTest(DisciplinaController.class)
public class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinaService disciplinaService;

    @Test
    void testCriarDisciplina() throws Exception {
        DisciplinaRequest request = new DisciplinaRequest();
        request.setNome("Matemática");
        request.setCodigo("MAT101");

        Disciplina disciplina = new Disciplina();
        disciplina.setId(UUID.randomUUID());
        disciplina.setNome("Matemática");
        disciplina.setCodigo("MAT101");

        when(disciplinaService.criarDisciplina(any(DisciplinaRequest.class))).thenReturn(disciplina);

        mockMvc.perform(post("/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Matemática\", \"codigo\": \"MAT101\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Matemática"))
                .andExpect(jsonPath("$.codigo").value("MAT101"));
    }

    @Test
    void testListarDisciplinas() throws Exception {
        mockMvc.perform(get("/disciplinas"))
                .andExpect(status().isOk());
    }
}
