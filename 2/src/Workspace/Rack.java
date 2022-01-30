package Workspace;

import java.util.ArrayList;
import java.util.List;

public class Rack {
    /**
     * Number of specific rack
     */
    private final int rackNumber;
    /**
     * Max capacity of spacific rack
     */
    private final int shelfMaxCapacity;
    /**
     * Number of shelves inside rack
     */
    private final int shelfCount;
    /**
     * Shelves inside rack
     */
    private final List<int[]> shelves;

    /**
     * Constructor
     * @param rackNumber Number of specific rack
     * @param shelfMaxCapacity Max capacity of each shelf
     * @param shelfCount Number of shelves
     */
    Rack(int rackNumber, int shelfMaxCapacity, int shelfCount) {
        this.rackNumber = rackNumber;
        this.shelfMaxCapacity = shelfMaxCapacity;
        this.shelfCount = shelfCount;
        this.shelves = new ArrayList<>();
    }

    /**
     * Get number of the rack
     * @return Number of the rack
     */
    public int getRackNumber() {
        return rackNumber;
    }

    /**
     * Get max capacity of each shelf
     * @return Capacity of shelves
     */
    public int getShelfMaxCapacity() {
        return shelfMaxCapacity;
    }

    /**
     * Get number of shelves
     * @return Number of shelves
     */
    public int getShelfCount() {
        return shelfCount;
    }

    /**
     * Add shelf to rack
     * @param shelf Shelf to add
     */
    public void addShelf(int[] shelf){
        this.shelves.add(shelf);
    }

    /**
     * Display all shelves from inside rack
     */
    public void display(){
        System.out.print(this.rackNumber + ";");
        for (var shelf : shelves){
            System.out.print(shelf[0] != -1 ? shelf[0] : "_");
            for(int i = 1; i < shelf.length; i++){
                System.out.print(",");
                System.out.print(shelf[i] != -1 ? shelf[i] : "_");
            }
            System.out.print(";");
        }
        System.out.println();
    }
}
