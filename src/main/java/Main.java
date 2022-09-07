import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }


        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();
        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

         */

        Map<String, Long> sortedByIdPersonMap = Arrays.stream(RAW_DATA).
                distinct().
                sorted(new PersonComparator()).
                collect(Collectors.groupingBy(Person::getName, Collectors.counting()));

        for (Map.Entry<String, Long> stringLongEntry : sortedByIdPersonMap.entrySet()) {
            System.out.println("Key : " + stringLongEntry.getKey() +
                    "\n" + "Value : " + stringLongEntry.getValue());
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @EqualsAndHashCode
    static class Person {
        final int id;

        final String name;
    }

    static class PersonComparator implements Comparator<Person> {
        public int compare(Person a, Person b) {

            return Math.min(a.id, (b.id));
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
}
