package vn.techmaster.product_list_application.controller;

import org.springframework.web.bind.annotation.*;
import vn.techmaster.product_list_application.dto.ProductRequest;
import vn.techmaster.product_list_application.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final List<Product> productList;

    public ProductController() {
        productList = new ArrayList<>();
        productList.add(new Product(getNonExistId(), "Iphone 11", "Iphone 11 - refurbished", 200, "Apple"));
        productList.add(new Product(getNonExistId(), "Iphone 12", "Iphone 12 - 99%", 350, "Apple"));
        productList.add(new Product(getNonExistId(), "Iphone 13", "Iphone 13 - new brand", 500, "Apple"));
        productList.add(new Product(getNonExistId(), "Iphone 13 Pro Max", "Iphone 13 Pro Max - new brand", 650, "Apple"));
        productList.add(new Product(getNonExistId(), "Samsung Galaxy S22", "Samsung Galaxy S22 - 99%", 300, "Samsung"));
        productList.add(new Product(getNonExistId(), "Samsung Galaxy S23", "Samsung Galaxy S23 - new brand", 700, "Samsung"));
        productList.add(new Product(getNonExistId(), "Samsung Galaxy Ultra S23", "Samsung Galaxy Ultra S23 - new brand", 860, "Samsung"));
        productList.add(new Product(getNonExistId(), "Huawei P60 Pro", "HUAWEI P60 Pro - new brand", 500, "Huawei"));
        productList.add(new Product(getNonExistId(), "Huawei P60 Art", "HUAWEI P60 Art - new brand", 650, "Huawei"));
        productList.add(new Product(getNonExistId(), "Huawei P50 Pro", "HUAWEI P50 Pro - 99%", 350, "Huawei"));
    }

    @GetMapping(path = {"", "/getAllProducts"})
    public List<Product> getProductList() {
        return productList;
    }

    @GetMapping("/{id}")
    public Product getAProduct(@PathVariable String id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @PostMapping
    public Product createNewProduct(@RequestBody ProductRequest productRequest) {
        String randomId = getNonExistId();
        Product newProduct = new Product(randomId, productRequest.name(), productRequest.description(), productRequest.price(), productRequest.brand());
        productList.add(newProduct);
        return newProduct;
    }

    private String getNonExistId() {
        int randomId;

        while (true) {
            boolean isDuplicate = false;
            randomId = randomANumber();
            if (!productList.isEmpty()) {
                for (Product product : productList) {
                    if (product.getId().equals(String.valueOf(randomId))) {
                        isDuplicate = true;
                        break;
                    }
                }
            }

            if (!isDuplicate) {
                break;
            }
        }

        return String.valueOf(randomId);
    }

    private int randomANumber() {
        return new Random().nextInt(10000) + 1;
    }
}
