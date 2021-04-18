package com.programmers.programmers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmers.programmers.apis.dto.RunProgramInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProgrammersApplicationTests {


    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void 테스트조회() throws Exception {
        ResultActions result = this.mockMvc.perform(get("/api/tests").accept(MediaType.APPLICATION_JSON));
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()",is(3)));
    }

    @Test
    @WithMockUser(username="tinyhhj",password="admin",roles={"ADMIN"})
    void 히스토리조회() throws Exception {
        ResultActions result = this.mockMvc.perform(get("/api/histories?testId={}".replace("{}","1"))
                .accept(MediaType.APPLICATION_JSON));
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()",is(2)));
    }

    @Test
    public void 로그인() throws Exception {
        ResultActions result = this.mockMvc.perform(post("/login")
                .content("username=tinyhhj&password=admin")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON));
        result.andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));
    }
    @Test
    public void 유저조회() throws Exception {
        ResultActions result = this.mockMvc.perform(get("/api/histories?id={}".replace("{}","1"))
                .accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
    }

    @Test
    @WithMockUser(username="tinyhhj", password="admin")
    public void 코드실행() throws Exception {
        RunProgramInput input = new RunProgramInput();
        input.setCode("function solve(a){return a;}");
        ResultActions result = this.mockMvc.perform(post("/api/run?testId={}"
                .replaceFirst("\\{}","1")
        )
                .content(new ObjectMapper().writeValueAsBytes(input))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
    }

}
