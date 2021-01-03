package com.monotonic.collections._4_maps.before;

import java.util.ArrayList;
import java.util.List;

public class NaiveProductLookupTable implements ProductLookupTable
{
    private final List<Product> products = new ArrayList<>();
    @Override
    public void addProduct(final Product productToAdd)
    {
          int id = productToAdd.getId();
          for(Product p : products)
          {
              if(p.getId() == id)
              {
                  throw new IllegalArgumentException("Unable to add product");
              }
          }
          products.add(productToAdd);
    }

    @Override
    public Product lookupById(final int id)
    {
        for(Product p : products)
        {
            if(p.getId() == id)
            {
                return p;
            }

        }
        return null;
    }

    @Override
    public void clear()
    {
        products.clear();
    }
}
