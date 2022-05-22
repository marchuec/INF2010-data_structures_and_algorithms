package Shape;

import Point.Point2d;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape implements Cloneable {
    private final Collection<Point2d> coords;

    /** TODO (DONE)
     * Create a BaseShape with empty coordinades
     */
    public BaseShape() {
        coords = (Collection<Point2d>) new ArrayList<Point2d>();
    }

    /** TODO (DONE)
     * Create a BaseShape from a collection of 2D points
     * @param coords The collection of 2D points
     */
    public BaseShape(Collection<Point2d> coords) {
        this.coords = coords;
    }

    /** TODO (DONE)
     * Add a deep copy of the 2D point to the Shape
     * @param coord 2D point to add
     * @return Updated BaseShape
     */
    public BaseShape add(Point2d coord) {
        this.coords.add(coord.clone());
        return this;
    }

    /** TODO (DONE)
     * Add a deep copy of the 2D points of the shape to the current shape
     * @param shape Shape to add to the current shape
     * @return Updated BaseShape
     */
    public BaseShape add(BaseShape shape) {
        for (Point2d coordToAdd:shape.coords) {
            this.add(coordToAdd.clone());
        }
        return this;
    }

    /** TODO (DONE)
     * Add a deep copy of the points in the collection to the shape
     * @param coords Collections of point to add
     * @return Updated BaseShape
     */
    public BaseShape addAll(Collection<Point2d> coords) {
        for (Point2d coordToAdd:coords) {
            this.add(coordToAdd.clone());
        }
        return this;
    }

    /** TODO (DONE)
     * Remove the 2D point from the shape
     * @param coord Point to remove
     * @return Updated BaseShape
     */
    public BaseShape remove(Point2d coord) {
        this.coords.remove(coord);
        return this;
    }

    /** TODO (DONE)
     * Remove the 2D points in the shape from the current shape
     * @param shape Shape containing the points to remove
     * @return Updated BaseShape
     */
    public BaseShape remove(BaseShape shape) {
        for (Point2d coordToRemove:shape.coords) {
            this.coords.remove(coordToRemove);
        }
        return this;
    }

    /** TODO (DONE)
     * Remove the 2D points in the collection from the current shape
     * @param coords Collection of 2D points to remove
     * @return Updated BaseShape
     */
    public BaseShape removeAll(Collection<Point2d> coords) {
        for (Point2d coordToRemove:coords) {
            this.coords.remove(coordToRemove);
        }
        return null;
    }

    /** TODO (DONE)
     * Return a shallow copy of the coordinates of the shape
     * @return Shallow copy of all coordinates contained by this BaseShape
     */
    public Collection<Point2d> getCoords() {
        Collection<Point2d> coordsCopy = new ArrayList<>();
        for (Point2d coord:coords) {
            coordsCopy.add(coord);
        }
        return coordsCopy;
    }

    /** TODO (DONE)
     * Create and return a deep copy of the coordinates of the shape
     * @return Deep copy of all coordinates contained by this BaseShape
     */
    public Collection<Point2d> cloneCoords() {
        Collection<Point2d> coordsClone = (Collection<Point2d>) new ArrayList<Point2d>();
        for (Point2d coord:coords) {
            coordsClone.add(coord.clone());
        }
        return coordsClone;
    }

    /** TODO (DONE)
     * Translate all points in a shape by a given vector
     * @param translateVector Vector by which to translate
     * @return Translated shape
     */
    public BaseShape translate(Point2d translateVector) {
        for (Point2d point:this.coords) {
            point.translate(translateVector);
        }
        return null;
    }

    /** TODO (DONE)
     * Rotate all points in a shape by a given angle
     * @param angle Angle by which to rotate
     * @return Rotated shape
     */
    public BaseShape rotate(Double angle) {
        for (Point2d point:coords) {
            point.rotate(angle);
        }
        return this;
    }

    /** TODO (DONE)
     * @return Maximum X coordinate of the shape
     */
    public Double getMaxX() {
        Iterator<Point2d> itr = coords.iterator();
        double maxX = (itr.hasNext()) ? itr.next().X() : -Double.MAX_VALUE;
        while (itr.hasNext()) {
            double Xcoord = itr.next().X();
            if (Xcoord > maxX)
                maxX = Xcoord;
        }
        return maxX;
    }

    /** TODO (DONE)
     * @return Maximum Y coordinate of the shape
     */
    public Double getMaxY() {
        Iterator<Point2d> itr = coords.iterator();
        double maxY = (itr.hasNext()) ? itr.next().Y() : -Double.MAX_VALUE;
        while (itr.hasNext()) {
            double Ycoord = itr.next().Y();
            if (Ycoord > maxY)
                maxY = Ycoord;
        }
        return maxY;
    }

    /** TODO (DONE)
     * @return 2D Point containing the maximum X and Y coordinates of the shape
     */
    public Point2d getMaxCoord() {
        return new Point2d(getMaxX(), getMaxY());
    }

    /** TODO (DONE)
     * @return Minimum X coordinate of the shape
     */
    public Double getMinX() {
        Iterator<Point2d> itr = coords.iterator();
        double minX = (itr.hasNext()) ? itr.next().X() : Double.MAX_VALUE;
        while (itr.hasNext()) {
            double Xcoord = itr.next().X();
            if (Xcoord < minX)
                minX = Xcoord;
        }
        return minX;
    }

    /** TODO (DONE)
     * @return Minimum Y coordinate of the shape
     */
    public Double getMinY() {
        Iterator<Point2d> itr = coords.iterator();
        double minY = (itr.hasNext()) ? itr.next().Y() : Double.MAX_VALUE;
        while (itr.hasNext()) {
            double Ycoord = itr.next().Y();
            if (Ycoord < minY)
                minY = Ycoord;
        }
        return minY;
    }

    /** TODO (DONE)
     * @return 2D point containing the minimum X and Y coordinate of the shape
     */
    public Point2d getMinCoord() {
        return new Point2d(getMinX(), getMinY());
    }

    /** TODO (DONE)
     * @return Deep copy of the current shape
     */
    public BaseShape clone() {
        return new BaseShape(cloneCoords());
    }
}
