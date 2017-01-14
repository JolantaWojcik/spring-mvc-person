package pl.kurs.spring.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kurs.spring.mvc.model.Person;
import pl.kurs.spring.mvc.service.PersonSortByStrategy;

@Controller
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonSortByStrategy personSortByStrategy;
	
	private List<Person> database;

	@PostConstruct
	public void init() {
		database = new ArrayList<>();
		database.add(new Person("Jan", "Kowalski", 30));
		database.add(new Person("Anna", "Nowak", 20));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getPeople(@RequestParam(value = "sortByCriteria", defaultValue = "id") String sortByCriteria,
			ModelMap model) {
		database.sort(personSortByStrategy.sortBy(sortByCriteria));
		model.addAttribute("persons", database);
		return "personView";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute Person person, ModelMap model) {
		database.add(person);
		return getPeople("id", model);
	}

	// do dzialania modelattribute potrzeby jest konstruktor bezparametrowy w
	// klasie ktora on ma stworzyc(Person)

	// @RequestMapping(value = "/", method = RequestMethod.POST)
	// public String addPerson(@RequestParam("name") String name,
	// @RequestParam("surname") String surname,
	// @RequestParam("age") int age, ModelMap model) {
	// database.add(new Person(name, surname, age));
	// return getPersons(model);
	// }

}
