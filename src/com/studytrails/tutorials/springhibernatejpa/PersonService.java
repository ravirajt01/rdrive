package com.studytrails.tutorials.springhibernatejpa;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class PersonService {

	@Inject
	private PersonDao personDao;

	/*public PersonDao getPersonDao() {
return personDao;
}
@Autowired
public void setPersonDao(PersonDao personDao) {
this.personDao = personDao;
}
	 */
	/*public void addPerson(Person person) {
getPersonDao().insert(person);
}

public List<Person> fetchAllPersons() {
return getPersonDao().selectAll();
}
	 */
	public void addPerson(Person person) {
		personDao.insert(person);
	}

	public List<Person> fetchAllPersons() {
		return personDao.selectAll();
	}



}