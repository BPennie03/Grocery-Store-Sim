package me.braden.project3.lanes;

import me.braden.project3.Customer;

public class RegularLane extends CheckoutLane{

    @Override
    public void add(Customer customer) {
        super.add(customer);
        double timeToAdd;

        timeToAdd = (customer.getNumItems() * 3) + 120; // these times were converted to seconds

        super.setTime(super.getTime() + timeToAdd);
        customer.setTimeInLane(timeToAdd + super.getTime());
    }
    
    @Override
    public void remove(Customer customer) {
        super.remove(customer);
        double timeToRemove;
        timeToRemove = (customer.getNumItems() * 3) + 120;

        super.setTime(super.getTime() - timeToRemove);
    }

}
