package Workspace;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solver {
    /**
     * List of racks
     */
    private final List<Rack> racks;
    /**
     * List of products with similarity
     */
    private final List<ProductType> productTypes;
    /**
     * List of products with their quantity
     */
    private final List<Tuple> productQuantity;
    /**
     * Warehouse with products and their current quantity
     */
    private final Warehouse warehouse;
    /**
     * Current best solution
     */
    private int[] bestSolution;
    /**
     * Value of current best solution
     */
    private int bestSolutionValue;
    /**
     * Type of solution selected by user
     */
    private final SolutionType solutionType;

    /**
     * Constructor
     * @param solutionType Type of searched solution
     * @param racks List of available racks
     * @param productTypes List of all available products with similarity
     * @param productsQuantity Quantity of available products
     */
    Solver(SolutionType solutionType , List<Rack> racks, List<ProductType> productTypes, List<Tuple> productsQuantity){
        this.solutionType = solutionType;
        this.racks = racks;
        this.productTypes = productTypes;
        this.productQuantity = productsQuantity;
        this.warehouse = new Warehouse(productsQuantity);
    }

    /**
     * Solve chosen problem and return the best solution
     * @return The best solution
     */
    public Solution solve(){
        // Create an object to transport the solution
        var solution = new Solution();

        // Sort racks by max shelf capacity
        racks.sort(Comparator.comparingInt(Rack::getShelfMaxCapacity).reversed());

        // Fill each rack
        for (var rack : racks){
            // Fill each shelf
            for (int i = 0; i < rack.getShelfCount(); i++){
                // If it is first shelf in the rack or previous solution is not valid
                if (bestSolution == null || !warehouse.isSolutionValid(bestSolution)) {
                    // Shelf with the length of searched solution
                    var shelf = new int[rack.getShelfMaxCapacity()];
                    // Get available product types from warehouse
                    var products = warehouse.getAvailableProductTypes();
                    // Reset old one
                    resetBestSolution();
                    // Find new one
                    findBestSolution(products, shelf, 0);
                }
                // Add found solution to rack
                rack.addShelf(bestSolution.clone());
                // Delete used products from warehouse
                warehouse.useProducts(bestSolution);
            }
            // Add filled rack to final solution
            solution.addRack(rack);
            // Reset found solution after the entire rack is full
            resetBestSolution();
        }

        // Sort racks by rackNumber
        solution.sortRacks(Comparator.comparingInt(Rack::getRackNumber));

        // Return solution
        return solution;
    }

    /**
     * Reset BestSolution and delete from warehouse used products
     */
    private void resetBestSolution(){
        bestSolutionValue = -1;
        bestSolution = null;
    }

    /**
     * Count Similarity between products on shelf
     * @param shelf Shelf with products
     * @return Value of similarity
     */
    private int countSimilarityOfShelf(int[] shelf){
        var result = 1;
        for (int i : shelf) {
            for (int j : shelf) {
                result += getSimilarityWithNumber(i, j);
            }
            result -= getSimilarityWithNumber(i, i);
        }
        return result;
    }

    /**
     * Get similarity between two products
     * @param fromNumber First product
     * @param withNumber Second product
     * @return Similarity between products
     */
    private int getSimilarityWithNumber(int fromNumber, int withNumber){
        if (fromNumber == -1 || withNumber == -1)
            return 0;
        var item = productTypes.stream()
                .filter(x -> x.getTypeNumber() == fromNumber)
                .findAny().orElse(null);
        return item != null ? item.getValueOfSimilarity(withNumber) : 0;
    }

    /**
     * Generate all solutions and find the best one
     * @param input Type of products to generate solution
     * @param item Array with length of searched solution
     * @param count Count of generated numbers by algorithm
     */
    private void findBestSolution(int[] input, int[] item, int count){
        if (count < item.length){
            for (int i : input) {
                item[count] = i;
                findBestSolution(input, item, count + 1);
            }
        }else{
            if (warehouse.isSolutionValid(item) && countValue(item) > bestSolutionValue){
                bestSolution = item.clone();
                bestSolutionValue = countValue(item);
            }
        }
    }

    /**
     * Count value of shelf
     * @param shelf Shelf with products
     * @return Value of shelf
     */
    private int countValue(int[] shelf){
        var multiplier = 1;
        switch (solutionType){
            case LESS_LOAD:
                multiplier = countShelfLoad(shelf);
                break;
            case LESS_POPULAR:
                multiplier = countItemsPopularity(shelf);
                break;
        }
        return countSimilarityOfShelf(shelf) * multiplier;
    }

    /**
     * Count load of the shelf
     * @param shelf Shelf with products
     * @return Load of the shelf
     */
    private int countShelfLoad(int[] shelf){
        var countOfEmptySpace = (Arrays.stream(shelf)
                .filter(x -> x == -1).count());
        return countOfEmptySpace != shelf.length ? (int) countOfEmptySpace : 0;
    }

    /**
     * Count popularity of products in shelf
     * @param shelf Shelf with products
     * @return Popularity of products
     */
    private int countItemsPopularity(int[] shelf){
        var quantity = 0;
        for (int product : shelf)
            quantity += getProductsQuantity(product);
        return quantity * 5;
    }

    /**
     * Get quantity of product
     * @param productType Type of product
     * @return Quantity of product
     */
    private int getProductsQuantity(int productType){
        var product = productQuantity.stream()
                .filter(x -> x.getKey() == productType)
                .findAny().orElse(null);
        return product != null ? product.getValue() : 0;
    }
}
