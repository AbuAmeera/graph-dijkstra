package com.example.prj3;

import java.text.DecimalFormat;
import java.util.*;

class Graph {

     void addEdge(ArrayList<ArrayList<Vert>> adj, String u, String v) {


         Calc c = new Calc();
         int flagu=0,flagv=0;
         Double lat1=0.0,lat2=0.0,long1=0.0,long2=0.0;

         for (int i=0;i<c.citys.size();i++){
//city,12,12
             int space = c.citys.get(i).indexOf(",");
             int space1 = c.citys.get(i).lastIndexOf(",");
             String str = c.citys.get(i).substring(0,space);

             if ( str.equalsIgnoreCase(u) ){
                 flagu = i;
                 lat1 = Double.parseDouble( c.citys.get(i).substring(space+1,space1) );
                 long1 = Double.parseDouble( c.citys.get(i).substring(space1+1) );
             }
         }

         for (int i=0;i<c.citys.size();i++){

             int space = c.citys.get(i).indexOf(",");
             int space1 = c.citys.get(i).lastIndexOf(",");
             String str = c.citys.get(i).substring(0,space);

             if ( str.equalsIgnoreCase(v) ){
                 flagv = i;
                 lat2 = Double.parseDouble( c.citys.get(i).substring(space+1,space1) );
                 long2 = Double.parseDouble( c.citys.get(i).substring(space1+1) );
             }
         }
         Double ans = c.distance(lat1,lat2,long1,long2,1,1);
         DecimalFormat df = new DecimalFormat("0.00");
         double fdist = Double.parseDouble(df.format(ans));
            Vert vert = new Vert();
            vert.setCity(v);
            vert.setDist(fdist);

            Vert vert1 = new Vert();
            vert1.setCity(u);
            vert1.setDist(fdist);


        adj.get(flagu).add(vert);
        adj.get(flagv).add(vert1);

    }



    void printGraph(ArrayList<ArrayList<Vert> > adj) {
        Calc c = new Calc();
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\nAdjacency list of vertex " + i);
            System.out.print("head");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> "+adj.get(i).get(j).toString());
            }
            System.out.println();
        }
    }




   public static Vert printShortestDistance( String src, String dest) {

       PriorityQueue<Vert> start = new PriorityQueue<Vert>();
       PriorityQueue<Vert> finish = new PriorityQueue<Vert>();

       Calc c = new Calc();

       if (find(dest) != -1) {


           int x = find(src);
           if (x != -1) {

               Vert vert = new Vert();
               int sp = c.citys.get(x).indexOf(",");
               vert.setCity( c.citys.get(x).substring(0,sp));
               vert.setDist(0);
               vert.setFulldis(0);

               start.add(vert);
               while (!start.isEmpty()) {

                   Vert n = start.poll();

                   if (n.getCity().equalsIgnoreCase(dest)) {

                       return n;
                   } else {
                      int f = find(n.getCity());
                       int size = c.adj.get(f).size();

                       for (int i = 0; i < c.adj.get(x).size(); i++) {
                           x = find(n.getCity());
                           Vert v1 = c.adj.get(x).get(i);
                           v1.setDist(c.adj.get(x).get(i).getDist());

                           v1.setFulldis(n.getFulldis() + v1.getDist());
                           v1.setCity(c.adj.get(x).get(i).getCity());


                           double sum = v1.getFulldis() + n.getDist();

                           if ( !start.contains(v1) && !finish.contains(v1) ) {

                               v1.setParent(n);

                               v1.setFulldis(sum);
                               start.add(v1);

                           } else {

                               if (v1.getFulldis() > sum) {

                                   v1.setFulldis(sum);
                                   v1.setParent(n);
                                   if (finish.contains(v1)) {

                                       finish.remove(v1);
                                       start.add(v1);
                                   }
                               }
                           }
                       }
                   }
                   finish.add(n);
                   System.out.println(n.getCity());
               }
               return null;

           } else {
               return null;
           }
       } else {
           return null;
       }

   }


    public static int find (String city){
        Calc c = new Calc();

        for (int i=0;i<c.adj.size();i++){
            String str = c.citys.get(i);
            int space = str.indexOf(",");
            str = str.substring(0,space);

            if (city.equalsIgnoreCase(str)){
                return i;
            }
        }
        return -1;

    }


}





