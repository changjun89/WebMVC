package me.anpan.anpanspringwebmvc.web.controller;

import me.anpan.anpanspringwebmvc.web.dto.Person;
import me.anpan.anpanspringwebmvc.web.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository repository;

    @Test
    public void hello() throws Exception {

        Person person = new Person();
        person.setName("changjun");

        Person person1 = repository.save(person);

        this.mockMvc.perform(get("/hello").param("id", person1.getId()+""))
                .andDo(print())
                .andExpect(content().string("hello changjun"));
    }

}