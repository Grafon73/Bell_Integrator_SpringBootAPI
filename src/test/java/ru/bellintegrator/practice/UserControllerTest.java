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
import ru.bellintegrator.practice.user.view.UserAddView;
import ru.bellintegrator.practice.user.view.UserFilterViewIn;
import ru.bellintegrator.practice.user.view.UserUpdateView;

import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllUsers() {
        this.mockMvc.perform(get("/api/user")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[*].id",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].firstName",
                        Matchers.is("Флора")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].secondName",
                        Matchers.is("Скурлыгина")));
    }

    @Test
    @SneakyThrows
    void getUserById() {
        this.mockMvc.perform(get("/api/user/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id",
                        Matchers.not(empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id",
                        Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName",
                        Matchers.is("Флора")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.secondName",
                        Matchers.is("Скурлыгина")));
    }

    @Test
    @SneakyThrows
    void addUser() {
        UserAddView userAddView = new UserAddView(
                2,
                "Мил",
                "Киселев",
                "Владимирович",
                "директор",
                "+7 (980) 244-79-29",
                15,
                "Разрешение на временное проживание в Российской Федерации",
                "1475826497",
                "2013-07-06",
                840,
                true
        );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userAddView);
        this.mockMvc.perform(post("/api/user/save")
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
    void updateUser() {
        UserUpdateView userUpdateView = new UserUpdateView(
                1,
                2,
                "Мил",
                "Киселев",
                "Владимирович",
                "директор",
                "+7 (980) 244-79-29",
                15,
                "Разрешение на временное проживание в Российской Федерации",
                "1475826497",
                "2013-07-06",
                840,
                true
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userUpdateView);
        this.mockMvc.perform(post("/api/user/update")
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
    void getByFilter() {
        UserFilterViewIn userFilterViewIn = new UserFilterViewIn(
                1,
                "Флора",
                "Скурлыгина",
                "Андреевна",
                "менеджер",
                21,
                112

        );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userFilterViewIn);
        this.mockMvc.perform(post("/api/user/list")
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].firstName",
                        Matchers.is("Флора")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].secondName",
                        Matchers.is("Скурлыгина")));
    }
}
