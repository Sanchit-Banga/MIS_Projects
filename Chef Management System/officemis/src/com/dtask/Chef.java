package com.dtask;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Chef{

    public long makeOrder(int c, List<Integer> lst, int orderId,long time)
    {
        ArrayList<Integer> slist = new ArrayList<>();
        MoveInSyncCafe cf = new MoveInSyncCafe();
        PriorityQueue<Integer> pq = new PriorityQueue<>(c);
        for(int i=0;i<4;i++){
            for(int j=0;j<lst.get(i);j++){
                if(i==0){
                    slist.add(cf.getSandwich());
                }
                else if(i==1){
                    slist.add(cf.getCoffee());
                }
                else if(i==2){
                    slist.add(cf.getCereal());
                }
                else{
                    slist.add(cf.getPizza());
                }
            }
        }
        slist.sort(Collections.reverseOrder());
        int m = slist.size();
        for(int i=0;i<m;i++) {
            if (pq.size() < c) {
                pq.add(slist.get(i));
            } else {
                try {
                    int mn = pq.poll();
                    pq.add(mn + slist.get(i));
                    System.out.println(pq);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        while(pq.size()!=1){
            pq.poll();
        }
        Integer fsh = pq.peek();
        if(fsh==null){
            fsh=0;
        }
        time = time + TimeUnit.MINUTES.toMillis(fsh);
        Date myDate = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        System.out.println("");
        System.out.println("Order with order id " + orderId + " will be ready at = " + formatter.format(myDate));
        System.out.println();
        return time;
    }


}

