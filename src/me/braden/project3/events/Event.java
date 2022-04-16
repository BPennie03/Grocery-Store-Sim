package me.braden.project3.events;

import me.braden.project3.Customer;

public class Event implements Comparable<Event> {

    private Customer customer;
    private double time;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public int compareTo(Event o) {
        return Double.compare(o.time, this.time);
    }

}
