package com.monotonic.collections._4_maps.before;

import java.util.HashMap;
import java.util.Map;

public class Java8Enhancements
{
    public static void main(String[] args)
    {
        Product defaultProd = new Product(
            -1, "Whatever the customer wants", 100);

        Map<Integer, Product> idToProduct = new HashMap<>();
        idToProduct.put(1, ProductFixtures.door);
        idToProduct.put(2, ProductFixtures.floorPanel);
        idToProduct.put(3, ProductFixtures.window);

        Product product = idToProduct.getOrDefault(10,defaultProd) ;
        System.out.println(product);
        System.out.println(idToProduct.get(10));
        System.out.println();

        product = idToProduct.computeIfAbsent(10, (id) -> new Product(id, "Custom Product", 100));
        System.out.println(product);
        System.out.println(idToProduct.get(10));
        System.out.println();

        product = idToProduct.replace(1, new Product(1, "Big Door", 100));
        System.out.println(product);
        System.out.println(idToProduct.get(1));
        System.out.println();

        idToProduct.replaceAll((id,oldProduct) -> new Product(oldProduct.getId(), oldProduct.getName(), oldProduct.getWeight()+10));
        System.out.println(idToProduct);
        System.out.println();

        idToProduct.forEach((key,value) -> System.out.println(key+"->"+ value));

    }
}
