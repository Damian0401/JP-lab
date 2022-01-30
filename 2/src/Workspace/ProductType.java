package Workspace;

import Workspace.Tuple;

import java.util.List;

public class ProductType {
    private final int typeNumber;
    private final List<Tuple> similarity;

    ProductType(int typeNumber, List<Tuple> similarity) {
        this.typeNumber = typeNumber;
        this.similarity = similarity;
    }

    public int getTypeNumber() {
        return typeNumber;
    }

    public int getValueOfSimilarity(int itemNumber){
        var similarity = this.similarity.stream()
                .filter(x -> x.getKey() == itemNumber)
                .findAny().orElse(null);

        return similarity != null ? similarity.getValue() : 0;
    }

    @Override
    public String toString() {
        return "{" +
                "typeNumber=" + typeNumber +
                ", Similarity=" + similarity +
                '}';
    }
}
