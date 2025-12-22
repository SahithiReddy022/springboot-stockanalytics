package io.endeavour.stocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.endeavour.stocks.entity.crud.PersonEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class CrudControllerTest {

    ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();


    @Autowired
    MockMvc mockMvc;

    Integer personId;

    @BeforeEach
    public void setup() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/crud/person/")
                        .content(getJson("test-data/create-person.json"))
                        .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        PersonEntity person = objectMapper.readValue(contentAsString, PersonEntity.class);

        assertNotNull(person);

        personId = person.getPersonId();

    }

    @AfterEach
    public void teardown() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/crud/person/" + personId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertNotNull(contentAsString);
    }

    @Test
    public void whenGetPersonIsSuccessful() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/crud/person/"+personId))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        String jsonString = mvcResult.getResponse().getContentAsString();
        assertNotNull(jsonString);

        PersonEntity person = objectMapper.readValue(jsonString, PersonEntity.class);

        assertNotNull(person);

        assertEquals("John", person.getFirstName());
        assertEquals("Smith", person.getLastName());


    }

    static String getJson(String filePath) throws Exception {
        Resource resource = new ClassPathResource(filePath);
        return Files.readString(resource.getFile().toPath());
    }
}

