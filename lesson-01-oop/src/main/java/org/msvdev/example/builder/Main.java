package org.msvdev.example.builder;

public class Main {
    public static void main(String[] args) {
        Person person = Person.builder()
                .firstName("Иван")
                .lastName("Иванов")
                .middleName("Иванович")
                .country("Россия")
                .address("Москва")
                .phone("123-45-67")
                .age(28)
                .gender("М")
                .build();

        System.out.println(person);
    }
}
