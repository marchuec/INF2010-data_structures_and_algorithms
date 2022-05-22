package Letter;

import Point.Point2d;
import Shape.*;

public final class LetterFactory {
    final static Double maxHeight = 200.0;
    final static Double maxWidth = maxHeight / 2.5;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double stripeThickness = maxHeight / 8;
    final static Double halfStripeThickness = stripeThickness / 2;

    // TODO (DONE)
    /**
     * Create the letter E graphically
     * @return BaseShape containing the letter E
     */
    public static BaseShape create_E() {
        BaseShape mainEllipse = new Ellipse(halfMaxWidth, halfMaxHeight);
        BaseShape ellipseToRemove = new Ellipse(halfMaxWidth - stripeThickness, halfMaxHeight-stripeThickness);
        mainEllipse.remove(ellipseToRemove);

        BaseShape mainStripe = new Rectangle(maxWidth, stripeThickness);
        mainEllipse.add(mainStripe);

        BaseShape stripeToRemove = new Rectangle(halfMaxWidth, stripeThickness);
        stripeToRemove = stripeToRemove.translate(new Point2d(halfMaxWidth/2, stripeThickness + 1));
        mainEllipse.remove(stripeToRemove);

        return mainEllipse;
    }

    /** TODO (DONE)
     * Create the letter C graphically
     * @return BaseShape containing the letter C
     */
    public static BaseShape create_C() {
        BaseShape mainEllipse = new Ellipse(maxWidth, maxHeight);
        Ellipse middleTempEllipse = new Ellipse(maxWidth - stripeThickness, maxHeight - stripeThickness);
        BaseShape rightTemp = new Rectangle(halfMaxWidth, maxHeight/1).translate(new Point2d(halfMaxWidth/1.5, 0.0));

        mainEllipse.remove(middleTempEllipse);
        mainEllipse.remove(rightTemp);

        return mainEllipse;
    }

    /** TODO (DONE)
     * Create the letter L graphically
     * @return BaseShape containing the letter L
     */
    public static BaseShape create_L() {
        BaseShape horizontalStripe = new Rectangle(stripeThickness, maxHeight);
        return horizontalStripe;
    }

    /** TODO (DONE)
     * Create the letter I graphically
     * @return BaseShape containing the letter I
     */
    public static BaseShape create_I() {
        BaseShape horizontalStripe= new Rectangle(halfStripeThickness,maxHeight);
        Rectangle partToRemove = new Rectangle(halfStripeThickness,1.5*stripeThickness).translate (new Point2d(0.0,-(halfMaxHeight-stripeThickness)));

        Ellipse thePoint = new Circle(stripeThickness).translate(new Point2d(0.0,-(halfMaxHeight-halfStripeThickness)));

        horizontalStripe.remove(partToRemove);
        horizontalStripe.add(thePoint);

        return horizontalStripe;
    }

    /** TODO (DONE)
     * Create the letter A graphically
     * @return BaseShape containing the letter A
     */
    public static BaseShape create_A() {
        Double degrees180 = Math.toRadians(180.0);
        BaseShape letterA = create_V();

        Rectangle horizontalStripe = new Rectangle(1.5*halfStripeThickness,halfStripeThickness);
        letterA.rotate(degrees180).translate(new Point2d(0.0,stripeThickness));
        letterA.add(horizontalStripe);

        return letterA;
    }

    /** TODO (DONE)
     * Create the letter V graphically
     * @return BaseShape containing the letter V
     */
    public static BaseShape create_V() {
        Double degrees8 = Math.toRadians(8);
        BaseShape leftStripe = new Rectangle(halfStripeThickness,maxHeight).rotate(-degrees8).translate(new Point2d(-halfMaxWidth/2,0.0));
        BaseShape rightStripe = new Rectangle(halfStripeThickness,maxHeight).rotate(degrees8).translate(new Point2d(halfMaxWidth/2,0.0));
        leftStripe.add(rightStripe);
        return leftStripe;
    }

    /** TODO (DONE)
     * Create the letter N graphically
     * @return BaseShape containing the letter N
     */
    public static BaseShape create_N() {
        BaseShape leftVerticalStripe = new Rectangle(halfStripeThickness,maxHeight);
        BaseShape rightVerticalStripe = new Rectangle(halfStripeThickness,maxHeight).translate(new Point2d(50.0,0.0));
        BaseShape circle = new Circle(maxHeight/2).translate(new Point2d(maxWidth/2 ,-halfMaxWidth));

        BaseShape circleToRemove = new Circle(maxHeight/2-stripeThickness).translate(new Point2d(maxWidth/2 ,-halfMaxWidth));
        BaseShape square = new Square(maxHeight/2).translate(new Point2d(maxHeight/2,0.0));
        BaseShape squareToRemove = new Square(maxHeight/2);
        BaseShape squareSecToRemove = new Square(halfMaxHeight).translate(new Point2d(maxWidth,-halfMaxHeight));
        Rectangle verticalStripeToRemove = new Rectangle(halfStripeThickness,maxHeight).translate(new Point2d(-halfStripeThickness ,0.0));

        circle.remove(square);
        circle.remove(squareToRemove);
        circle.remove(verticalStripeToRemove);
        circle.remove(squareSecToRemove);
        rightVerticalStripe.remove(squareSecToRemove);

        leftVerticalStripe.add(rightVerticalStripe);
        leftVerticalStripe.add(circle);

        return leftVerticalStripe;
    }

    /** TODO (DONE)
     * Create the letter R graphically
     * @return BaseShape containing the letter R
     */
    public static BaseShape create_R() {
        BaseShape mainCircle = new Circle(maxWidth/2);
        BaseShape mainCircleToRemove = new Circle(maxWidth/2 - stripeThickness);
        mainCircle.remove(mainCircleToRemove);

        BaseShape semiCircleToRemove = new Square(maxWidth);
        semiCircleToRemove = semiCircleToRemove.translate(new Point2d(0.0, maxWidth/2));
        mainCircle.remove(semiCircleToRemove);

        BaseShape mainStripe = new Rectangle(stripeThickness, maxHeight);
        mainStripe = mainStripe.translate(new Point2d(-maxWidth/2 + stripeThickness/2, maxHeight/4 - stripeThickness/2));
        mainCircle.add(mainStripe);

        mainCircle = mainCircle.translate(new Point2d(0.0, -maxHeight/4 + stripeThickness/2));

        return mainCircle;

    }

    /** TODO (DONE)
     * Create the letter B graphically
     * @return BaseShape containing the letter B
     */
    public static BaseShape create_B() {
        BaseShape verticalStripe = new Rectangle(halfStripeThickness,maxHeight).translate(new Point2d(1.5*halfStripeThickness,0.0));
        BaseShape upperCircle = new Circle(maxHeight/2).translate(new Point2d(halfMaxWidth/2,halfMaxHeight/2));
        BaseShape lowerCircle = new Circle(maxHeight/2).translate(new Point2d(halfMaxWidth/2,-halfMaxHeight/2));
        BaseShape removeUpperCircle = new Circle(maxHeight/2 - stripeThickness).translate(new Point2d(halfMaxWidth/2,halfMaxHeight/2));
        BaseShape removeLowerCircle = new Circle(maxHeight/2 - stripeThickness).translate(new Point2d(halfMaxWidth/2,-halfMaxHeight/2));

        Rectangle removeRightStripe = new Rectangle(4*stripeThickness,maxHeight).translate(new Point2d(-1.5*stripeThickness,0.0));
        upperCircle.remove(removeUpperCircle);
        lowerCircle.remove(removeLowerCircle);

        verticalStripe.add(lowerCircle);
        verticalStripe.add(upperCircle);
        verticalStripe.remove(removeRightStripe);
        return verticalStripe;
    }
}

