package com.example.figures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** <strong>Square</strong> class is a child of Rectangle class
 */
public class Square extends Rectangle implements Figure {

    private static Logger LOGGER = LoggerFactory.getLogger(Square.class);
    private final String type = "Square";

    public Square(double width, int x, int y) {
        super(width, width, x, y);
    }

    @Override
    public String getType() {
        return this.type;
    }

     /**
     * {@inheritdoc}
     */
    @Override
    public void show() {
        String position = "(" + super.getX() + ", " + super.getY() + ")";
        double area = this.getArea();
        //System.out.println("Object type: " + this.type + ". Object coordinates: " + position + ". Object area: " + area + ".");
        LOGGER.info("Object type: {} . Object coordinates: {}. Object area: {}.", this.type, position, area);
    }
}
