package br.com.will.startup.services;

import br.com.will.startup.exceptions.ResourceNotFoundException;
import br.com.will.startup.model.Person;
import br.com.will.startup.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class);

    private final PersonRepository personRepository;

    public PersonServices(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(Person person) {
        logger.info("Creating one person");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person");
        var entity = findById(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void delete(Long id) {
        var entity = findById(id);
        personRepository.delete(entity);

    }

    public List<Person> findAll() {
        logger.info("Finding all people!");
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one person!");
        return personRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No record found for this ID!")
        );
    }

}
