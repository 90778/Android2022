package de.hs_weingarten.ma_explintentslist;


import java.util.ArrayList;

public class TruckSingleton  {

    private static TruckSingleton singletonInstance = null;
    private static ArrayList<Truck> trucks;
    public static TruckSingleton getInstance() {
        if(singletonInstance == null) {
            singletonInstance = new TruckSingleton();
            trucks = new ArrayList<Truck>();
        }
        return singletonInstance;
    }

    public static void addTruck(String _name, double _weightTons, int _noAxles) {
        Truck newTruck = new Truck();
        newTruck.setData(_name,_weightTons,_noAxles);
        trucks.add(newTruck);
    }

    public static int getNoTrucks() {
        return trucks.size();
    }

    public static Truck getTruck(int i) {
        return trucks.get(i);
    }

    public static ArrayList<Truck> getTrucks() {
        return trucks;
    }
}

class Truck{
    private String name;
    private double weightTons;
    private int noAxles;
    public void setData(String _name, double _weightTons, int _noAxles){
        name = _name;
        weightTons = _weightTons;
        noAxles = _noAxles;
    }

    public String getName() {
        return name;
    }

    public double getWeightTons() {
        return weightTons;
    }

    public int getNoAxles() {
        return noAxles;
    }
}

