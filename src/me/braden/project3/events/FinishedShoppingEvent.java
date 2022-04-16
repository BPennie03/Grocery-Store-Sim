package me.braden.project3.events;

import me.braden.project3.Customer;

public class FinishedShoppingEvent extends Event {

    // FinishedShoppingEvent is a sub-class makes a finishedShoppingEvent and takes a specific customer and the
    // time the customer is done shopping

    // doneShopping represents the time the customer finished shopping
    public FinishedShoppingEvent(Customer eventCustomer, double doneShopping) {
        super.setTime(doneShopping);
        super.setCustomer(eventCustomer);
    }
}
