import java.util.Objects;

public class Person {
    private Integer age;
    private String address;
    private final String name;
    private final String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void happyBirthday() {
        age++;
    }

    public boolean hasAge() {
        return age != null;
    }
    public boolean hasAddress() {
        return address != null;
    }

    public PersonBuilder newChildBuilder(){
        return new PersonBuilder().setAddress(address).setAge(0).setSurname(surname);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(name).append(" ")
                .append(surname)
                .toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, surname, age, address);
    }

}
