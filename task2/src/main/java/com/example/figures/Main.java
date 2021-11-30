package com.example.figures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    @SuppressWarnings("checkstyle:MemberName")
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

//        System.out.println("----- New figures creation: -------");
        LOGGER.info("----- New figures creation started: -------");
        Figure[] f = new Figure[3];
        f[0] = new Triangle(3,4,5,0,0);
        f[1] = new Rectangle(2, 6,5,5);
        f[2] = new Square(5, 20,20);

        LOGGER.info("----- Figures manipulations started: -------");
        for (Figure figure : f) {
            figure.getType();
            figure.getArea();
            figure.move(1, 1);
            figure.zoomPercentage(125);
            figure.show();
        }
    }
}
