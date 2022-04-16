package me.braden.project3;

import me.braden.project3.lanes.CheckoutLane;
import me.braden.project3.lanes.ExpressLane;
import me.braden.project3.lanes.RegularLane;

import java.util.ArrayList;
import java.util.Random;

public class Customer {

    private final int numItems;
    private final double avgPickupTime;
    private double timeInLane;
    private int ID;

    public Customer(int numItems, double avgPickupTime, int ID) {
        this.numItems = numItems;
        this.avgPickupTime = avgPickupTime;
        this.ID = ID;
    }

    public int getNumItems() {
        return numItems;
    }

    public double getTimeInLane() {
        return timeInLane;
    }

    public void setTimeInLane(double timeInLane) {
        this.timeInLane = timeInLane;
    }

    public double getAvgPickupTime() {
        return avgPickupTime;
    }

    /***
     *
     * @param lanes
     * @return the randomCheckoutLane the customer will be placed into
     */
    public CheckoutLane chooseRandomLane(ArrayList<CheckoutLane> lanes){
        ArrayList<CheckoutLane> usableLanes = new ArrayList<>();
        for (CheckoutLane checkoutLane : lanes) {
            CheckoutLane lane = numItems <= 12 ? checkoutLane : checkoutLane instanceof RegularLane ? checkoutLane : null;
            if (lane != null) {
                usableLanes.add(lane);
            }
        }

        int minLength = usableLanes.get(0).getQueueSize();

        for (CheckoutLane usableLane : usableLanes) {
            if (usableLane.getQueueSize() < minLength) {
                minLength = usableLane.getQueueSize();
            }
        }

        ArrayList<ExpressLane> expressLanes = new ArrayList<>();
        for(int i=0; i<usableLanes.size(); i++){
            CheckoutLane lane = usableLanes.get(i);
            if(lane.getQueueSize() != minLength){
                usableLanes.remove(lane);
            } else if (lane instanceof ExpressLane){
                expressLanes.add((ExpressLane) lane);
            }
        }

        // If there are no express lanes, pick a random usable lane based off of customer's items, else, pick a random
        // lane from the list of expressLanes
        return expressLanes.isEmpty()
                ? usableLanes.get(new Random().nextInt(usableLanes.size()))
                : expressLanes.get(new Random().nextInt(expressLanes.size()));
    }

    @Override
    public String toString() {
        return "Customer{" +
                "numItems=" + numItems +
                ", avgPickupTime=" + avgPickupTime +
                ", timeInLane=" + timeInLane +
                ", ID=" + ID +
                '}';
    }
}
