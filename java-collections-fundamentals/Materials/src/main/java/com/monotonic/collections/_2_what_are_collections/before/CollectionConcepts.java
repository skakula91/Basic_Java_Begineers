package com.monotonic.collections._2_what_are_collections.before;

import com.monotonic.collections.common.Product;

import java.util.*;

public class CollectionConcepts
{
    public static void main(String[] args)
    {
        Product door = new Product("Wooden Door", 35);
        Product floorPanel = new Product("Floor Panel", 25);
        Product window = new Product("Glass Window", 10);

        Collection<Product> products = new ArrayList<Product>();
        products.add(door);
        products.add(floorPanel);
        products.add(window);

//        Iterator<Product> iterator = products.iterator();
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            if (product.getWeight() > 20)
//            {
//                iterator.remove();
//            }
//        }
        System.out.println(products.size());
        System.out.println(products.isEmpty());
        System.out.println(products.contains(window));
        System.out.println(products.remove(window));
        System.out.println(products.contains(window));
    }
}
