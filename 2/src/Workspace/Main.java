package Workspace;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Default path to files with data
        var productsQuantityFileName = "data/product-quantity.csv";
        var productsFileName = "data/products.csv";
        var racksFileName = "data/racks.csv";

        // Default solution type
        var solutionType = SolutionType.DEFAULT;

        // Object to scan data from user
        var scanner = new Scanner(System.in);

        // Select and read filenames
        System.out.println("Do you want to enter your own file names or leave the default?");
        System.out.println("[1] - Enter own");
        System.out.println("[Other numbers] - Leave default");

        // Check if user entered data
        if(!scanner.hasNextInt()) {
            System.out.println("Entered invalid data");
            System.exit(-1);
        }

        // Select filenames
        var selectedNumber = Integer.parseInt(scanner.nextLine());
        if (selectedNumber == 1){
            var jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/";
            System.out.println("Enter filename with products quantity in format: 'TypeNumber; Quantity'");
            productsQuantityFileName = jarPath + scanner.nextLine();
            System.out.println("Enter filename with racks in format: 'RackNumber; ShelfMaxCapacity; ShelfCount'");
            racksFileName = jarPath + scanner.nextLine();
            System.out.println("Enter filename with products in format: 'TypeNumber;*(RelatedTypeNumber,Similarity)'");
            productsFileName = jarPath + scanner.nextLine();
        }
 
        // Create InputStream
        var racksStream = Main.class.getResourceAsStream(racksFileName); 
        var productsStream = Main.class.getResourceAsStream(productsFileName);
        var productsQuantityStream = Main.class.getResourceAsStream(productsQuantityFileName);

        // Select solution type
        System.out.println("Select solution type:");
        System.out.println("[1] - Less load - fill racks with relative good value of similarity and low load of shelves");
        System.out.println("[2] - Less popularity - fill racks with relative good value of similarity and with preference to less popular products");
        System.out.println("[Other numbers] - Default - fill racks with the best value of similarity");

        // Check if user entered data
        if(!scanner.hasNextInt()) {
            System.out.println("Entered invalid data");
            System.exit(-1);
        }

        // Select solution type
        selectedNumber = Integer.parseInt(scanner.nextLine());
        switch (selectedNumber){
            case 1:
                solutionType = SolutionType.LESS_LOAD;
                break;
            case 2:
                solutionType = SolutionType.LESS_POPULAR;
                break;
        }

        // Close the scanner
        scanner.close();

        // Object to read data from specific files
        var csvReader = new CsvReader("[;]", "[,]");

        // Read products with their quantity
        List<Tuple> productsQuantity = null;
        try {
            productsQuantity = csvReader.readProductQuantity(productsQuantityStream);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unable to read " + productsQuantityFileName);
            System.exit(-1);
        }

        // Read racks
        List<Rack> racks = null;
        try {
            racks = csvReader.readRacks(racksStream);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unable to read " + racksFileName);
            System.exit(-1);
        }

        // Read products with their similarity
        List<ProductType> productTypes = null;
        try {
            productTypes = csvReader.readProducts(productsStream);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unable to read " + productsFileName);
            System.exit(-1);
        }

        // Create object with data from files
        var solver = new Solver(solutionType, racks, productTypes, productsQuantity);

        // Find solution
        var solution = solver.solve();

        // Display solution
        solution.display();
    }
}
