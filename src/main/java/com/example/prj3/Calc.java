package com.example.prj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Character.isLetter;

public class Calc {

    static ArrayList<String> citys = new ArrayList<String>();
     ArrayList<String> relation = new ArrayList<String>();
    static ArrayList<ArrayList<Vert> > adj = new ArrayList<ArrayList<Vert> >();

    public void emp(){
        citys.clear();
        relation.clear();
        adj.clear();
    }

    public void Scan(String text) throws FileNotFoundException {

        File file = new File(text);
        Scanner scan = new Scanner(file);

        int f1,f2,i=1;
        String f3 = scan.nextLine();
        int space = f3.indexOf(" ");

        f1 = Integer.parseInt( f3.substring(0,space) ); ////citys number
        f2 = Integer.parseInt( f3.substring(space+1) ); ////citys relations number

        while (scan.hasNextLine()) {

            StringBuilder p = new StringBuilder();
            p.append(scan.nextLine());

            if ( i <= f1 ){
            citys.add(p.toString());
            }
            else {
                relation.add(p.toString());
            }
            i++;
        }
    }

    public void fun(){

        Graph g = new Graph();

        for (int i = 0; i < citys.size(); i++)
            adj.add(new ArrayList<Vert>());

        for (int i=0;i<relation.size();i++){
            String city1="",city2="";
            int space = relation.get(i).indexOf(",");
            city1 = relation.get(i).substring(0,space);
            city2 = relation.get(i).substring(space+1);
            g.addEdge(adj,city1,city2);
        }


   // g.printGraph(adj);
    }



    public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return (Math.sqrt(distance))/1000;
    }
 int si;
    List<String> list1 = new ArrayList<>();
    public String shorts(String src,String path){
        Graph g = new Graph();
        Vert v = new Vert();

        v =  g.printShortestDistance(src,path);

        if (v == null){
            return "Nopath";
        }
        String str = v.fulldis+ "\n";

        List<String> list = new ArrayList<>();

        while (v.parent != null){
           list.add( v.getCity());
           list1.add(v.getCity());
           v = v.parent;
           si++;
        }
        list1.add(src);
        Collections.reverse(list);
         str+=  src+"\n";
        int count=0;

        for (int i=0;i<list.size();i++){
            str += list.get(i)+"\n";
        }
        return str;
    }

    static final int mapWidth = 614, mapHeight = 1141;
    static final double mapLongitudeStart = 33.5, mapLatitudeStart = 33.5;
    static final double mapLongitude = 36.5-mapLongitudeStart,
    mapLatitude = mapLatitudeStart-29.5;

    public static String getPositionOnScreen(double latitude, double longitude){

        longitude -= mapLongitudeStart;
        latitude = mapLatitudeStart-latitude;

        double x = (mapWidth * (longitude / mapLongitude));
        double y = (mapHeight * (latitude / mapLatitude))-20;

       return x + " " +y;
    }







}
