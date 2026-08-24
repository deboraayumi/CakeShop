package com.deboraayumi.seed;

import java.util.ArrayList;
import java.util.List;

import com.deboraayumi.model.Product;
import com.deboraayumi.model.Product.Tag;
import com.deboraayumi.repository.ProductRepository;


public class ProductSeeder {

    public static void exec(String[] args) {
        
        ProductRepository productRepository = new ProductRepository();
        List<Product> products = new ArrayList<>();

        products.add(new Product("Strawberry Short Cake", 18.00, 30, List.of(Tag.STRAWBERRY, Tag.CAKE, Tag.FRUIT)));
        products.add(new Product("Dark Chocolate Cocoa Cookie", 8.50, 25, List.of(Tag.CHOCOLATE, Tag.COOKIE, Tag.BITTER)));
        products.add(new Product("Apple Pie", 14.90, 12, List.of(Tag.PIE, Tag.FRUIT)));
        products.add(new Product("Vanilla Cake", 22.00, 8, List.of(Tag.VANNILA, Tag.CAKE)));
        products.add(new Product("Vegan Chocolate Brownie", 9.90, 15, List.of(Tag.CHOCOLATE, Tag.VEGAN)));
        products.add(new Product("Gluten-Free Oat Cookie", 7.50, 20, List.of(Tag.COOKIE, Tag.GLUTEN_FREE)));
        products.add(new Product("Lactose-Free Chocolate Mousse", 11.00, 10, List.of(Tag.CHOCOLATE, Tag.LACTOSE_FREE)));
        products.add(new Product("Banana Pie", 13.50, 12, List.of(Tag.PIE, Tag.FRUIT)));
        products.add(new Product("Espresso Coffee", 6.00, 40, List.of(Tag.DRINK, Tag.BITTER)));
        products.add(new Product("Strawberry Juice", 8.00, 18, List.of(Tag.DRINK, Tag.FRUIT, Tag.STRAWBERRY)));
    
        productRepository.saveProducts(products);
    }


}
