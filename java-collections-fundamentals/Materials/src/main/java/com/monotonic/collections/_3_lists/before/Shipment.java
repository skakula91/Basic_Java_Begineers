package com.monotonic.collections._3_lists.before;

import com.monotonic.collections.common.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Shipment implements Iterable<Product>
{
    private static final int LIGHT_VAN_MAX_WEIGHT = 20;
    private final List<Product> products = new ArrayList<Product>();

    private List<Product> lightVanProducts;
    private List<Product> heavyVanProducts;
    public void add(Product product)
    {
        products.add(product);
    }

    public boolean replace(Product oldProduct, Product newProduct)
    {
        var indexOfReplace = products.indexOf(oldProduct);

        if(indexOfReplace == -1)
        {
            return false;
        }
        products.set(indexOfReplace,newProduct);

        return true;
    }

    public void prepare()
    {
        // Sort
        products.sort(Product.BY_WEIGHT);

        // find the split point
        int splitPoint = findSplitPoint();


        lightVanProducts = products.subList(0,splitPoint);
        heavyVanProducts = products.subList(splitPoint,products.size());
    }

    private int findSplitPoint() {
        for(int i = 0 ; i < products.size(); i++)
        {
            Product product = products.get(i);
            if(product.getWeight() > LIGHT_VAN_MAX_WEIGHT)
            {
                return i;
            }
        }
        return 0;
    }

    public List<Product> getHeavyVanProducts()
    {
        return heavyVanProducts;
    }

    public List<Product> getLightVanProducts()
    {
        return lightVanProducts;
    }

    public Iterator<Product> iterator()
    {
        return products.iterator();
    }
}
