package pl.kurs.spring.mvc.service;

import java.util.Comparator;

import pl.kurs.spring.mvc.model.Person;

public interface PersonSortByStrategy {
	Comparator<Person> sortBy(String criteria);
}
