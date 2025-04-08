package ru.vsu.cs;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    private final List<Person> people = new ArrayList<>();

    public static final PersonRepository INSTANCE = new PersonRepository();

    static {
        Person p1 = new Person(0, "John Smith", 32);
        Person p2 = new Person(1, "Jane Doe", 28);
        Person p3 = new Person(2, "Max Mustermann", 44);
        Person p4 = new Person(3, "Ivan Ivanov", 18);
        Person[] people = {p1, p2, p3, p4};
        for (Person p : people) INSTANCE.save(p);
    }

    public void save(Person person) {
        people.add(person);
    }

    public List<Person> findAll() {
        return List.copyOf(people);
    }
}
