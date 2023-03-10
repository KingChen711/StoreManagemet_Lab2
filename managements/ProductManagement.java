package managements;

import BuilderPattern.Product.ProductBuilder;
import models.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ProductManagement {

    private static ProductManagement instance;
    private List<Product> productList;

    private ProductManagement() {

    }

    public static ProductManagement getInstance() {
        if (instance == null) {
            instance = new ProductManagement();
        }
        return instance;
    }

    public void listAllProducts() {
        productList.forEach(product -> System.out.println(product.toString()));
    }

    public List<String> getProductIds() {
        return productList
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    public void readProductList() {
        productList = new ArrayList<>();
        String currentWorkingDirectory = System.getProperty("user.dir");
        File file = new File(currentWorkingDirectory + "/src/resources/products.txt");
        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] texts = line.split(",");
                Product newProduct =
                        new ProductBuilder()
                                .addId(texts[0])
                                .addName(texts[1])
                                .addUnit(texts[2])
                                .addOrigin(texts[3])
                                .addPrice(Double.parseDouble(texts[4]))
                                .build();

                productList.add(newProduct);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

}