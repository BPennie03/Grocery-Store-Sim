package me.braden.project3.events;

import me.braden.project3.Customer;

public class ArrivalEvent extends Event {

    // Arrival event class that is a sub-class of the event class, which creates an arrival event which takes a
    // specific time and customer

    public ArrivalEvent(double arrivalTime, Customer currentCustomer) {
        super.setTime(arrivalTime);
        super.setCustomer(currentCustomer);
    }
}
