package me.anpan.anpanspringwebmvc.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.anpan.anpanspringwebmvc.web.dto.Person;
import me.anpan.anpanspringwebmvc.web.repository.PersonRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Jaxb2Marshaller marshaller;

    @Test
    public void hello() throws Exception {

        Person person = new Person();
        person.setName("changjun");

        Person person1 = repository.save(person);

        this.mockMvc.perform(get("/hello").param("id", person1.getId() + ""))
                .andDo(print())
                .andExpect(content().string("hello changjun"));
    }

    @Test
    public void indexTest() throws Exception {
        this.mockMvc.perform(get("/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("hello html")));
    }

    @Test
    public void mobileTest() throws Exception {
        this.mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("mobile html")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL));
    }

    @Test
    public void stringMessage() throws Exception {
        this.mockMvc.perform(get("/message").content("hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }


    @Test
    public void xmlMessage() throws Exception {
        Person person = new Person();
        person.setId(2019l);
        person.setName("changjun");

        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        marshaller.marshal(person, streamResult);

        String xmlString = stringWriter.toString();

        this.mockMvc.perform(get("/xmlMessage")
                .content(xmlString)
                .contentType(MediaType.APPLICATION_ATOM_XML)
                .accept(MediaType.APPLICATION_ATOM_XML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("person/name").string("changjun"))
                .andExpect(xpath("person/id").string("2019"));
    }

    @Test
    public void jsonMessage() throws Exception {
        Person person = new Person();
        person.setId(2019l);
        person.setName("changjun");

        String value = objectMapper.writeValueAsString(person);

        this.mockMvc.perform(get("/jsonMessage")
                .content(value)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2019"))
                .andExpect(jsonPath("$.name").value("changjun"));
    }
}