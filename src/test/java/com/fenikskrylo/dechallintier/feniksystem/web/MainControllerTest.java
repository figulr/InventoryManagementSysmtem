package com.fenikskrylo.dechallintier.feniksystem.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void main가_리턴된다() throws Exception{
        String msg = "메인페이지";

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(msg));
    }
}
