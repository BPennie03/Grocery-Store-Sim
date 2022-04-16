package me.braden.project3.lanes;

import me.braden.project3.Customer;

public class ExpressLane extends CheckoutLane {

    @Override
    public void add(Customer customer) {
        super.add(customer);
        double timeToAdd = 0;

        timeToAdd = (customer.getNumItems() * 6) + 60;

        customer.setTimeInLane(timeToAdd + super.getTime());
        super.setTime(super.getTime() + timeToAdd);
    }

    @Override
    public void remove(Customer customer) {
        super.remove(customer);
        double timeToRemove = 0;
        timeToRemove = (customer.getNumItems() * 6) + 60;

        super.setTime(super.getTime() - timeToRemove);
    }

}
