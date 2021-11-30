package com.example.figures;
/**Interface <strong>Figure</strong> describes common methods for figures such as,
 * <b>show()</b>, <b>getType()</b>, <b>getArea()</b> etc..
 * @author - Marina Panchenko
 * @version - 1.0
 */
public interface Figure {

    /** Method displays object's information - type, area and coordinates
     */
    void show();

    String getType();

    /** Method returns object area
     * @return object area
     */
    double getArea();

    /** Method moves object to the new coordinates
     * @param x new X coordinate
     * @param y new Y coordinate
     */

    void move(int x, int y);

    /** Method zoom/scale object
     * @param percent value in %
     */

    void zoomPercentage(int percent);
}
