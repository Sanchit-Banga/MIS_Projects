package com.dtask;

import java.text.SimpleDateFormat;
import java.util.*;

public class Waiter {

    static int orderId=9900;

    public void takeOrder(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of chefs");
        int c = sc.nextInt();

        while(c<=0){
            System.out.println("Pls enter correct no. of chef > 0= ");
            c = sc.nextInt();
        }

        System.out.print("Enter the no .of customers in the Queue = ");
        int n = sc.nextInt();

        while(c<=0){
            System.out.println("Pls enter correct no. of peoples > 0= ");
            n = sc.nextInt();
        }

        Chef cobj = new Chef();
        long time = System.currentTimeMillis();
        long stime = time;
        for(int i=1;i<=n;i++){
            List<Integer> l = new ArrayList<>();
            System.out.println("Enter the details for order " + i);
            System.out.print("Enter the number of Sandwich = ");
            l.add(sc.nextInt());
            System.out.print("Enter the number of coffee = ");
            l.add(sc.nextInt());
            System.out.print("Enter the number of Cereal = ");
            l.add(sc.nextInt());
            System.out.print("Enter the number of pizzas = ");
            l.add(sc.nextInt());
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            System.out.println();
            System.out.println("Order with id "+ orderId+" is placed at "+formatter.format(new Date(stime)));
            time = cobj.makeOrder(c,l,orderId,time);
            orderId++;

        }
        sc.close();
    }

}
