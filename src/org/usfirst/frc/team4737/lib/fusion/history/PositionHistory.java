package org.usfirst.frc.team4737.lib.fusion.history;

import org.usfirst.frc.team4737.lib.fusion.Point;
import org.usfirst.frc.team4737.robot.RobotMap;

import java.util.ArrayList;

/**
 * @author Brian Semrau
 * @version 3/1/2017
 */
public class PositionHistory {

    private ArrayList<Record> history;
    private int size;

    public PositionHistory() {
        history = new ArrayList<>(size = RobotMap.RIO_UPDATE_RATE_HZ);
    }

    public void record(Point pt, double timestamp) {
        history.add(0, new Record(pt, timestamp));

        // Trim history size
        while (history.size() > size)
            history.remove(size);
    }

    public Point getNearest(double time) {



        return null;
    }

    public Point getNewest() {
        return history.get(0).point;
    }


}
