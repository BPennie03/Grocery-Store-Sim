package me.braden.project3;

import me.braden.project3.events.ArrivalEvent;
import me.braden.project3.events.Event;
import me.braden.project3.events.FinishedCheckoutEvent;
import me.braden.project3.events.FinishedShoppingEvent;
import me.braden.project3.lanes.CheckoutLane;
import me.braden.project3.lanes.ExpressLane;
import me.braden.project3.lanes.RegularLane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[]args) throws FileNotFoundException {

        PriorityQueue<Event> events = new PriorityQueue<>();
        ArrayList<Customer> customers = new ArrayList<>();
        int numRegularLanes = 9;
        int numExpressLanes = 3;
        double averageWaitTime;
        double totalWaitTime = 0;

        ArrayList<CheckoutLane> checkoutLanes = createLanes(numExpressLanes, numRegularLanes);

        // Lines 21 - 31 read through the file and make new Customers based off of the information from the file and
        // creating an arrivalEvent for each customer and adding it to the queue of events
        File inputFile = new File("arrival medium.txt");
        Scanner scanFile = new Scanner(inputFile);

        // Customer ID is used to track customers by giving them a unique incrementing value to distinguish them
        int customerID = 0;

        while (scanFile.hasNext()) {
            customerID++; // increments the customer's ID number for each customer created
            double arrivalTime = scanFile.nextDouble();
            int itemAmount = scanFile.nextInt();
            double avgPickupTime = scanFile.nextDouble();

            Customer currentCustomer = new Customer(itemAmount, avgPickupTime, customerID);
            customers.add(currentCustomer);
            events.add(new ArrivalEvent(arrivalTime, currentCustomer));
        }

        // While the priority queue of events is not empty, it goes through each event, seeing which event is at
        // the top of the queue, and performing the specified methods
        while (!events.isEmpty()) {
            Event currentEvent = events.poll();
            Customer customer = currentEvent.getCustomer();

            if (currentEvent instanceof ArrivalEvent) {
                events.add(finishedShopping((ArrivalEvent) currentEvent));
            } else if (currentEvent instanceof FinishedShoppingEvent) {
                customer.chooseRandomLane(checkoutLanes).add(customer);
                events.add(new FinishedCheckoutEvent(customer.getTimeInLane(),
                        customer, customer.chooseRandomLane(checkoutLanes)));
            } else {
                totalWaitTime += customer.getTimeInLane();
                events.remove(currentEvent);
            }

        }

        // average wait time is computed by taking the total wait time for every customer, dividing it by the
        // amount of customers served, and finally dividing it again by 60 to output this to minutes rather than
        // seconds
        averageWaitTime = (totalWaitTime / customerID) / 60;

        System.out.print("The average wait time (in line) for " + numExpressLanes + " express lanes and "
                + numRegularLanes + " regular lanes is ");
        System.out.printf("%.2f", averageWaitTime);
        System.out.print(" minutes");

        // Calls the logfile method which is used to output final data to a separate "output.txt" file
        logFile(customers);
    }

    /**
     *
     * @param arrival
     * @return a new finishedShoppingEvent
     */
    public static FinishedShoppingEvent finishedShopping(ArrivalEvent arrival) {
        Customer eventCustomer = arrival.getCustomer();
        double shoppingTime = (eventCustomer.getNumItems() * eventCustomer.getAvgPickupTime()) + arrival.getTime();
        return new FinishedShoppingEvent(eventCustomer, shoppingTime);
    }

    /***
     *
     * @param expressLanes
     * @param regularLanes
     * @return an arrayList of all the checkout lanes
     */
    public static ArrayList<CheckoutLane> createLanes(int expressLanes, int regularLanes) {
        ArrayList<CheckoutLane> checkoutLanes = new ArrayList<>();
        for (int i=0; i<expressLanes; i++) {
            checkoutLanes.add(new ExpressLane());
        }

        for (int i=0; i<regularLanes; i++) {
            checkoutLanes.add(new RegularLane());
        }

        return checkoutLanes;
    }

    /***
     * Writes all of the data into a separate output file
     * @param customers
     * @throws FileNotFoundException
     */
    public static void logFile(ArrayList<Customer> customers) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");

        for (Customer customer : customers) {
            pw.println(customer);

        }
        pw.close();

    }

}
