package ru.bellintegrator.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.bellintegrator.practice.office.view.OfficeSaveView;
import ru.bellintegrator.practice.office.view.OfficeView;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@Transactional
public class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllOffices() {
        this.mockMvc.perform(get("/api/office")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[*].id",
                        Matchers.not(empty())))
                .andExpect(content().string(containsString("Филиал №1")));
    }

    @Test
    @SneakyThrows
    void getOfficeById(){
        this.mockMvc.perform(get("/api/office/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id",
                        Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name",
                        Matchers.is("Филиал №1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address",
                        Matchers.is("г. Сковородино, ул. Михайловский пер, дом 124")));
    }

    @Test
    @SneakyThrows
    void addOffice(){
        OfficeSaveView officeView =
                new OfficeSaveView(1,
                        "Филиал №1",
                        null,
                        "+7 (928) 595-87-56",
                        true );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(officeView);
        this.mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result",
                        Matchers.is("success")));
    }

    @Test
    @SneakyThrows
    void updateOffice(){
        OfficeView officeView =
                new OfficeView(1,
                        "Филиал №1",
                        "г. Уфа пер, дом 114",
                        "+7 (928) 595-87-56",
                        true );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(officeView);
        this.mockMvc.perform(post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result",
                        Matchers.is("success")));

    }

    @Test
    @SneakyThrows
    void getByFilter() throws JsonProcessingException {
        OfficeSaveView officeFilterView =
                new OfficeSaveView(1,
                        "Филиал №1",
                        null,
                        "+7 (928) 595-87-56",
                        true );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(officeFilterView);
        this.mockMvc.perform(post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].id",
                        Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].name",
                        Matchers.is("Филиал №1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].isActive",
                        Matchers.is(true)));
    }
}
