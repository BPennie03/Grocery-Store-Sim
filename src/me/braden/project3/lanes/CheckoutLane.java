package me.braden.project3.lanes;

import me.braden.project3.Customer;

import java.util.LinkedList;
import java.util.Queue;

public class CheckoutLane implements Comparable<CheckoutLane> {

    // CheckoutLane class includes getters and setters for information such as the time and the ability to add or
    // remove customers from a specific checkoutLane

    // The sub-classes, RegularLane and ExpressLane are very similar, with their only difference being the time they add
    // or subtract based on customers being added or removed

    private final Queue<Customer> customers = new LinkedList<>();
    private double time;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void add(Customer customer) {
        customers.offer(customer);
    }

    public void remove(Customer customer) {
        customers.remove(customer);
    }

    public int getQueueSize() {
        return customers.size();
    }

    @Override
    public int compareTo(CheckoutLane other) {
        return Integer.compare(this.getQueueSize(), other.getQueueSize());
    }
}
