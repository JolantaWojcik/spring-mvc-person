package pl.kurs.spring.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import pl.kurs.spring.mvc.controller.PersonController;
import pl.kurs.spring.mvc.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersonControllerTest.PersonControllerConfigurationTest.class)
public class PersonControllerTest {

	@Autowired
	PersonController pc;

	@Mock
	private ModelMap model;

	private MockMvc mockMvc;

	@Test
	public void shouldProperlyGetPeople() throws Exception{
		Person p1 = new Person("Jan", "Kowalski", 30);
		Person p2 = new Person("Arkadiusz", "Kowalski", 23);
		String criteria = "sortByCriteria";

		List<Person> people = Arrays.asList(p1, p2);
//		people.add(p1);
//		people.add(p2);

		//zle -> jak zrobic zeby byla lista - da sie nie zmieniajac typu metody
		when(pc.getPeople(criteria, model)).thenReturn(people.toString());

		this.mockMvc
		.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("persons",people))
		.andExpect(view().name("personView"));
	}

	@Test
	public void shouldAddPerson() throws Exception{
		Person p1 = new Person("Jan", "Kowalski", 30);
		List<Person> people = new ArrayList();
		people.add(p1);
		//jak sprawdzic czy metoday zdała poprzedni test?
		Mockito.verify(pc).getPeople("id", model);
		when(pc.addPerson(p1, model)).thenReturn(pc.getPeople("id", model));
		Mockito.verify(people).add(p1);
	}

	@Configuration
	public static class PersonControllerConfigurationTest{
		@Bean
		public PersonController pc(){
			return Mockito.mock(PersonController.class);
		}
	}
}


