package com.monotonic.collections._5_streams.before;

import com.monotonic.collections._5_streams.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;

public class StreamProducts
{
    public static void main(String[] args)
    {
        Product door = new Product(1, "Wooden Door", 35);
        Product floorPanel = new Product(2, "Floor Panel", 25);
        Product window = new Product(3, "Glass Window", 10);

        List<Product> products = new ArrayList<>();
        products.add(door);
        products.add(floorPanel);
        products.add(window);
        products.add(floorPanel);
        products.add(window);

        //namesOfLightProductsWeightSortedLoop(products);
        namesOfLightProductsWeightSortedStreams(products);
    }

    private static void namesOfLightProductsWeightSortedStreams(final List<Product> products)
    {
        products.stream().filter(p -> p.getWeight() < 30)
                .sorted(comparingInt(Product :: getWeight))
                .map(Product::getName)
                .forEach(System.out::println);

       Optional<Product> lst = products.stream().min(comparingInt(Product :: getWeight));
       if (lst != null)
       {
           System.out.println(lst);
       }

    }

    private static void namesOfLightProductsWeightSortedLoop(List<Product> products)
    {
        List<Product> lightProducts = new ArrayList<>();

        for(Product p : products)
        {
            if(p.getWeight() < 30)
            {
                lightProducts.add(p);
            }
        }

        lightProducts.sort(comparingInt(Product :: getWeight));

        for (Product product : lightProducts)
        {
            System.out.println(product.getName());
        }
    }
}
