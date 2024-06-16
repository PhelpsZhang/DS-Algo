package Entity;

public class Person implements Comparable<Person> {
    String name;
    Integer age;
    Integer score;

    public Person() {
        this.name = "";
        age = null;
        score = 0;
    }

    public Person(String name, Integer age, Integer score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        // return "name: " + this.name + " age: " + this.age + " score: " + this.score;
        return name + " (" + score + ")";
    }
}
