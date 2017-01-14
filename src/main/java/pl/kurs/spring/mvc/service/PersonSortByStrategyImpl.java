package pl.kurs.spring.mvc.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import pl.kurs.spring.mvc.model.Person;

@Service
public class PersonSortByStrategyImpl implements PersonSortByStrategy {

	private Map<String, Comparator<Person>> sortStrategyMap;

	@PostConstruct
	public void init() {
		sortStrategyMap = new HashMap<>();
		sortStrategyMap.put("id", (a, b) -> a.getId() - b.getId());
		sortStrategyMap.put("name", (a, b) -> a.getName().compareTo(b.getName()));
		sortStrategyMap.put("surname", (a, b) -> a.getSurname().compareTo(b.getSurname()));
		sortStrategyMap.put("age", (a, b) -> a.getAge() - b.getAge());
	}

	@Override
	public Comparator<Person> sortBy(String criteria) {
		return sortStrategyMap.get(criteria);
	}

}
