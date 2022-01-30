package Workspace;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {
    /**
     * Separator between items in file
     */
    private final String itemsSeparator;
    /**
     * Separator between key and value in file
     */
    private final String keyValueSeparator;

    /**
     * Constructor
     * @param itemsSeparator Separator between items in file
     * @param keyValueSeparator Separator between key and value in file
     */
    public CsvReader(String itemsSeparator, String keyValueSeparator) {
        this.itemsSeparator = itemsSeparator;
        this.keyValueSeparator = keyValueSeparator;
    }

    /**
     * Read products with their quantity from specific file
     * @param stream stream with list of products with their quantity
     * @return List of products mapped to Tuple objects
     */
    public List<Tuple> readProductQuantity(InputStream stream){
        try (InputStreamReader reader = new InputStreamReader(stream)){
            BufferedReader bufferedReader = new BufferedReader(reader);
            return bufferedReader.lines()
                    .skip(1)
                    .map(line -> line.split(itemsSeparator))
                    .map(array -> new Tuple(Integer.parseInt(array[0]), Integer.parseInt(array[1])))
                    .collect(Collectors.toList());
        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Read list of racks from specific file
     * @param stream stream with list of racks
     * @return List of racks mapped to Rack objects
     */
    public List<Rack> readRacks(InputStream stream){
        try (InputStreamReader reader = new InputStreamReader(stream)){
            BufferedReader bufferedReader = new BufferedReader(reader);
            return bufferedReader.lines()
                    .skip(1)
                    .map(line -> line.split(itemsSeparator))
                    .map(array -> new Rack(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2])))
                    .collect(Collectors.toList());
        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Read list of products with their similarities from specific file
     * @param stream stream with list of products
     * @return List of racks mapped to ProductType objects
     */
    public List<ProductType> readProducts(InputStream stream){
        try (InputStreamReader reader = new InputStreamReader(stream)){
            BufferedReader bufferedReader = new BufferedReader(reader);
            return bufferedReader.lines()
                    .skip(1)
                    .map(line -> line.split(itemsSeparator))
                    .map(array -> new ProductType(
                            Integer.parseInt(array[0]),
                            Arrays.stream(array)
                                .skip(1)
                                .map(row -> row.split(keyValueSeparator))
                                .map(row -> new Tuple(Integer.parseInt(row[0]), Integer.parseInt(row[1])))
                                .collect(Collectors.toList())))
                    .collect(Collectors.toList());

        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }
}
