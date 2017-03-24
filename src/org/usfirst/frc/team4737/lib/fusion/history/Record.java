package org.usfirst.frc.team4737.lib.fusion.history;

import org.usfirst.frc.team4737.lib.fusion.Point;

/**
 * @author Brian Semrau
 * @version 3/1/2017
 */
class Record {

    public Point point;
    public double timestamp;

    Record(Point point, double timestamp) {
        this.point = point;
        this.timestamp = timestamp;
    }

}
