package ru.bellintegrator.practice;

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
import ru.bellintegrator.practice.organization.view.OrgFilterIn;
import ru.bellintegrator.practice.organization.view.OrgSaveView;
import ru.bellintegrator.practice.organization.view.OrgUpdateView;

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
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllOrganizations(){
        this.mockMvc.perform(get("/api/organization")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[*].id",
                        Matchers.not(empty())))
                .andExpect(content().string(containsString("ПАО Драгоценность и Ъ")));
    }

    @Test
    @SneakyThrows
    void addNewOrganization(){
        OrgSaveView orgSaveView = new OrgSaveView(
                "Булки",
                "ООО Хлеб",
                "98416549674",
                "49844654",
                "г. Кумены, ул. Краснопрудный пер, дом 114",
                "+7 (910) 659-16-56",
                 true
        );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(orgSaveView);
        this.mockMvc.perform(post("/api/organization")
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
    void getOrganizationById(){
        this.mockMvc.perform(get("/api/organization/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id",
                        Matchers.not(empty())))
                .andExpect(content().string(containsString("ПАО Драгоценность и Ъ")));
    }

    @Test
    @SneakyThrows
    void getOrganizationByFilter(){
        OrgFilterIn orgFilterIn = new OrgFilterIn(
                 "ПАО Драгоценность и Ъ",
                 "3169961387",
                true
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(orgFilterIn);
        this.mockMvc.perform(post("/api/organization/list")
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
                        Matchers.is("ПАО Драгоценность и Ъ")));
    }

    @Test
    @SneakyThrows
    void updateOrganization(){
        OrgUpdateView orgUpdateView = new OrgUpdateView(
                1,
                "Булки",
                "ООО Хлеб",
                "98416549674",
                "49844654",
                "г. Кумены, ул. Краснопрудный пер, дом 114",
                "+7 (910) 659-16-56",
                true
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(orgUpdateView);
        this.mockMvc.perform(post("/api/organization/update")
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
}
