/**
 * yarin sason
 * Assignment 6
 * 318229143
 */
package forms;


import tools.Constants;

/**
 * class represent a line (segment) with his start, end point .
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * constructs a Line with two point objects.
     *
     * @param start Starting point.
     * @param end   Ending point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Line with 4 coordinates.
     * The constructor made two point objects ,base on the given points
     * and set the new points in the line.
     *
     * @param x1 First point x.
     * @param y1 First point y.
     * @param x2 second point x.
     * @param y2 second point y.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.start = p1;
        this.end = p2;
    }

    /**
     * The function calculate the length of the line.
     *
     * @return The length value
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return The middle point.
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return The start Point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return The end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other Another line to check intersection with,
     * @return false/true depends on if the lines are intersected or not.
     */
    public boolean isIntersecting(Line other) {

        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return true;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return true;
        }
        if ((this.start.getX() == this.end.getX()) && (other.start.getX() == other.end.getX())) {
            if (this.start.getX() == other.start.getX()) {
                double maxMainY = Math.max(this.start.getY(), this.end.getY());
                double minMainY = Math.min(this.start.getY(), this.end.getY());
                double maxOtherY = Math.max(other.start.getY(), other.end.getY());
                double minOtherY = Math.min(other.start.getY(), other.end.getY());
                return ((minOtherY < maxMainY) && (minOtherY > minMainY)
                        || (maxOtherY < maxMainY) && (maxOtherY > minMainY));
            } else {
                return false;
            }
        }

        // if the two lines is vertical or their parallels,they will not intersect.
        Point crossPoint = this.generalIntersectionPoint(other);
        if (crossPoint == null) {
            if ((this.start.getX() != this.end.getX()) || (other.start.getX() != other.end.getX())) {
                if (this.incline() == other.incline() && (this.constantCal() == other.constantCal())) {
                    return (this.inRange(other) || other.inRange(this));
                }
            }
            return false;
        }
        //check if the intersection point is on the two lines.
        return ((this.end.distance(crossPoint) + this.start.distance(crossPoint))
                <= (this.length() + Constants.ALLOWED_ERROR) && (other.end.distance(crossPoint)
                + other.start.distance(crossPoint)) <= (other.length() + Constants.ALLOWED_ERROR));
    }
    /**
     * The function compare between two segments that the same equation
     * and check if the starting point or ending point of one (or both) of one is between the
     * starting or ending point of other ,in this case the segments are merged.
     *
     * @param other Line to compare
     * @return If the line is in the range of the other.
     */
    public boolean inRange(Line other) {
        return ((other.start().distance(this.end) + other.start().distance(this.start) <= this.length())
                || (other.end().distance(this.end) + other.end().distance(this.start) <= this.length()));
    }


    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other the line to check intersection with.
     * @return point to the intersection point if intersect null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            return this.generalIntersectionPoint(other);
        } else {
            return null;
        }
    }

    /**
     * Return true is the lines are equal, false otherwise.
     *
     * @param other Another comparison line
     * @return boolean value if their equals
     */
    public boolean equals(Line other) {
        return (((this.start.equals(other.start)) && (this.end.equals(other.end)))
                || ((this.start.equals(other.end)) && (this.end.equals(other.start))));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect A rectangle object.
     * @return The closest point form the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> interactions = rect.intersectionPoints(this);
        double closestDistance = Constants.SCREEN_HEIGHT;
        Point closestPoint = null;
        for (Point currentPoint : interactions) {
            if (currentPoint != null) {
                if (this.start.distance(currentPoint) < closestDistance) {
                    closestDistance = this.start.distance(currentPoint);
                    closestPoint = currentPoint;
                }
            }
        }
        return closestPoint;
    }

    //help functions
    /**
     * calculate a line incline .
     *
     * @return The incline.
     */
    private double incline() {
        double startX = (this.start).getX();
        double startY = (this.start).getY();
        double endX = (this.end).getX();
        double endY = (this.end).getY();
        double incline;

        if (startX == endX) {
            return -1;
        }
        if (startY == endY) {
            incline = 0;
        } else {
            incline = (startY - endY) / (startX - endX);
        }
        return incline;
    }

    /**
     * calculating the b in the y = mx + b equation.
     *
     * @return the b value.
     */
    private double constantCal() {
        double incline = this.incline();
        double startX = this.start.getX();
        double startY = this.start.getY();
        /*
        y-y1=m1(x-x1) s.t: y = mx + y1-m1*x1
         */
        return startY - incline * startX;

    }

    /**
     * Calculate the intersection point assuming they are real lines
     * and not segments.
     *
     * @param other the general line that we want to find the intersection point with.
     * @return Point to their general intersection point.
     */
    private Point generalIntersectionPoint(Line other) {
        //Taking care in the case that one of the lines is vertical,or both.
        if ((this.start.getX() == this.end.getX()) && (other.start.getX() == other.end.getX())) {
            if (this.start.getX() == other.end.getX()) {
                double maxMainY = Math.max(this.start.getY(), this.end.getY());
                double minMainY = Math.min(this.start.getY(), this.end.getY());
                double maxOtherY = Math.max(other.start.getY(), other.end.getY());
                double minOtherY = Math.min(other.start.getY(), other.end.getY());

                if (minOtherY == maxMainY) {
                    return new Point(this.start.getX(), minOtherY);

                }
                if (maxOtherY == minMainY) {
                    return new Point(this.start.getX(), minMainY);

                } else {
                    return null;
                }
            }
        }
        if (this.start.getX() == this.end.getX()) {
            double incline2 = other.incline();
            double const2 = other.constantCal();
            double yCoordinate = incline2 * this.start.getX() + const2;
            return new Point(this.start.getX(), yCoordinate);
        }
        if (other.start.getX() == other.end.getX()) {
            double incline1 = this.incline();
            double const1 = this.constantCal();
            double yCoordinate = incline1 * other.start.getX() + const1;
            return new Point(other.start.getX(), yCoordinate);
        }
        //checks if the lines are parallel (not intersect in this case)
        if (this.incline() == other.incline() && (this.constantCal() == other.constantCal())) {
            if (this.start.equals(other.start)) {
                if (other.end.distance(this.end) < Math.max(this.length(), other.length())) {
                    return null;
                } else {
                    return this.start;
                }
            }
            if (this.start.equals(other.end)) {
                if (other.start().distance(this.end) < Math.max(this.length(), other.length())) {
                    return null;
                } else {
                    return this.start;
                }
            }
            if (this.end.equals(other.start)) {
                if (other.end().distance(this.start) < Math.max(this.length(), other.length())) {
                    return null;
                } else {
                    return this.end;
                }
            }
            if (this.end.equals(other.end)) {
                if (other.start().distance(this.start) < Math.max(this.length(), other.length())) {
                    return null;
                } else {
                    return this.end;
                }

            } else {
                return null;
            }
        }
        double incline1 = this.incline();
        double incline2 = other.incline();
        double const1 = this.constantCal();
        double const2 = other.constantCal();
        /*
        l1 = m1x +b1
        l2 = m2x +b2
        intersection will be if and only if l1 = l2
        m1x + b1 = m2x +b2 s.t: x = (b1-b2)/(m2-m1)
         */
        double xCoordinate = (const1 - const2) / (incline2 - incline1);
        double yCoordinate = incline1 * xCoordinate + const1;
        return new Point(xCoordinate, yCoordinate);


    }

}

