package Workspace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Warehouse {
    /**
     * List of products and their quantity in warehouse
     */
    public List<Tuple> products;

    /**
     * Constructor
     * @param productsQuantity Quantity of the products
     */
    Warehouse(List<Tuple> productsQuantity){
        this.products = new ArrayList<>();
        for (var product : productsQuantity){
            products.add(new Tuple(product.getKey(), product.getValue()));
        }
    }

    /**
     * Check if in warehouse is enough products to fulfil shelf
     * @param solution Array with product types
     * @return 'true' if solution is valid, otherwise 'false'
     */
    public boolean isSolutionValid(int[] solution){
        for (var product : this.products)
            if (Arrays.stream(solution)
                    .filter(x -> x == product.getKey()).count() > product.getValue())
                return false;
        return true;
    }

    /**
     * Delete products from warehouse
     * @param solution Array with products types
     */
    public void useProducts(int[] solution){
        for (var product : this.products)
            product.setValue((int) (product.getValue() - Arrays.stream(solution)
                                .filter(x -> x == product.getKey()).count()));
    }

    /**
     * Get list with product types available in warehouse
     * @return Array with available product types with empty space (-1)
     */
    public int[] getAvailableProductTypes(){
        var availableProductTypes = products.stream()
                .filter(x -> x.getValue() != 0)
                .map(Tuple::getKey)
                .collect(Collectors.toList());
        availableProductTypes.add(-1);
        return availableProductTypes.stream()
                .mapToInt(x -> x).toArray();

    }
}
