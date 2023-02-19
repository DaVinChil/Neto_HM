package firs_task_person_builder;

public class PersonBuilder {
    private String name;
    private Integer age;
    private String surname;
    private String address;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(Integer age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age can not be less zero!");
        }
        this.age = age;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.name = name;
        return this;
    }

    public Person build() {
        if (name == null) {
            if (surname == null) {
                throw new IllegalStateException("Surname and name missing!");
            }
            throw new IllegalStateException("Name missing!");
        } else if (surname == null) {
            throw new IllegalStateException("Surname missing!");
        }

        Person person;
        if (age == null) {
            person = new Person(name, surname);
        } else {
            person = new Person(name, surname, age);
        }

        if (address != null) {
            person.setAddress(address);
        }

        return person;
    }
}
