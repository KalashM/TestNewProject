package com.example.figures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** <strong>Square</strong> class is a child of Rectangle class
 */
public class Square extends Rectangle implements Figure {

    private static Logger LOGGER = LoggerFactory.getLogger(Square.class);
    private double side;
    private final String type = "Square";

    public Square(double side, int x, int y) {
        super(x, y);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String getType() {
        return this.type;
    }
    /**
     * {@inheritdoc}
     */

    @Override
    public double getArea() {
        return side * side;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void zoomPercentage(int percent) {
        this.side = side * percent / 100;
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
