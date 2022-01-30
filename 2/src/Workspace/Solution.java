package Workspace;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    /**
     * List of racks
     */
    private final List<Rack> racks;

    /**
     * Constructor
     */
    Solution(){
        racks = new ArrayList<>();
    }

    public void sortRacks(Comparator comparator){
        racks.sort(comparator);
    }

    /**
     * Add rack to solution
     * @param rack Rack to add
     */
    public void addRack(Rack rack){
        racks.add(rack);
    }

    /**
     * Display racks from inside solution
     */
    public void display(){
        System.out.println("RackNumber;*(TypeNumber);");
        for (var rack : racks)
            rack.display();
    }
}
