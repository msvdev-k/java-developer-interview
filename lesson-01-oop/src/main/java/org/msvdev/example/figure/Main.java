package org.msvdev.example.figure;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Figure> figures = List.of(new Circle(), new Square(), new Triangle());
        figures.forEach(Figure::paint);
    }
}
