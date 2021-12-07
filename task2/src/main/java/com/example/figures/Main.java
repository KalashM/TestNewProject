package com.example.figures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Main {
    @SuppressWarnings("checkstyle:MemberName")
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

//        System.out.println("----- New figures creation: -------");
        LOGGER.info("----- New figures creation started: -------");
        ArrayList<Figure> figures = new ArrayList<>();
        try {
            figures.add(new Triangle(1, 1, 5, 0, 0));
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
        figures.add(new Rectangle(2, 6,5,5));
        figures.add(new Square(5,20,20));

        LOGGER.info("----- Figures manipulations started: -------");
        for (Figure figure : figures) {
            figure.getType();
            figure.getArea();
            figure.move(1, 1);
            figure.moveBy(2, 3);
            figure.zoomPercentage(125);
            figure.show();
        }
    }
}
