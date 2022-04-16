package me.braden.project3.events;

import me.braden.project3.lanes.CheckoutLane;
import me.braden.project3.Customer;

public class FinishedCheckoutEvent extends Event {

    // FinishedCheckoutEvent is a sub-class to the event class and creates a finishedCheckoutEvent and keeps
    // the time, customer, and lane that is passed into it

    public FinishedCheckoutEvent(double time, Customer customer, CheckoutLane lane) {
        super.setTime(super.getTime() - time);
        super.setCustomer(customer);

        lane.remove(customer);
    }
}