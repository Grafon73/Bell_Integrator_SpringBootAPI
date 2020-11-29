package ru.bellintegrator.practice;

import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@Transactional
public class InfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllDocs(){
        this.mockMvc.perform(get("/api/docs")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[*].code",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[*].name",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].code",
                        Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].name",
                        Matchers.is("Свитедельство о рождении")));
    }

    @Test
    @SneakyThrows
    void getAllCountries(){
        this.mockMvc.perform(get("/api/countries")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[*].code",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[*].name",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].code",
                        Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].name",
                        Matchers.is("Афганистан")));
    }
}
