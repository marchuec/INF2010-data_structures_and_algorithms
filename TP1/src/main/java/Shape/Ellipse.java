package Shape;

import Point.Point2d;

import java.util.Collection;

public class Ellipse extends BaseShape {
    /** TODO (done)
     * Create a filled Ellipse that is centered on (0, 0)
     * @param widthDiameter Width of the Ellipse
     * @param heightDiameter Height of the Ellipse
     */
    public Ellipse(Double widthDiameter, Double heightDiameter) {
        super();
        for (Double x = -widthDiameter/2.0; x <= widthDiameter/2.0; x += 0.5){
            for (Double y = -heightDiameter/2.0; y <= heightDiameter/2.0; y += 0.5) {
                if ((x*x)/(widthDiameter*widthDiameter/4.0) + (y*y)/(heightDiameter*heightDiameter/4.0) <= 1) {
                    add(new Point2d(x,y));
                }
            }
        }
    }

    /** TODO (Done)
     * Create a filled Ellipse that is centered on (0,0)
     * @param dimensions 2D point containing the width and height of the Ellipse
     */
    public Ellipse(Point2d dimensions) {
        this(dimensions.X(), dimensions.Y());
    }

    /** TODO (Done)
     * Translate the Ellipse by the given 2D point
     * @param translatePoint The 2D point by which to translate
     * @return The translated Ellipse
     */
    @Override
    public Ellipse translate(Point2d translatePoint) {
        super.translate(translatePoint);
        return this;
    }

    /** TODO (done)
     * Rotate the Ellipse by the given angle (In Radians)
     * @param angle The angle by which to rotate
     * @return The rotated Ellipse
     */
    @Override
    public Ellipse rotate(Double angle) {
        super.rotate(angle);
        return this;
    }

    /** TODO (done)
     * @return Deep Copy of the Ellipse
     */
    @Override
    public Ellipse clone() { return (Ellipse)super.clone(); }
}
