package be.dewolf;

import be.dewolf.rest.PersonTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DbappApplication.class)
@Transactional
@TestPropertySource(locations="classpath:test.properties")
public class ApplicationIT {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifiesCreatePersonCreatesPerson() throws Exception {

        assertAantalPersonen(0);

        createPerson();

        assertAantalPersonen(1);
    }

    private MvcResult createPerson() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/person/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    private void assertAantalPersonen(int expectedAmount) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/person/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<PersonTO> persons = mapper.readValue(contentAsString, TypeFactory.defaultInstance().constructCollectionType(List.class, PersonTO.class));
        Assert.assertEquals(expectedAmount, persons.size());
    }

    @Test
    public void verifiesCreatePersonReturnsPersistedPerson() throws Exception {
        assertAantalPersonen(0);

        MvcResult mvcResult = createPerson();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        PersonTO personTO = mapper.readValue(contentAsString, PersonTO.class);
        Assert.assertNotNull(personTO);
        System.out.println(personTO);
        Assert.assertNotEquals(0, personTO.getId());
    }

    @Test
    public void verifiesGreetingpageLoads() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void verifiesHomepageLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());


    }

}