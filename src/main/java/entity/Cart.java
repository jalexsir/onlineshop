package entity;

import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Cart {
    LinkedHashMap<Product, Integer> productList;

    public Cart(LinkedHashMap<Product, Integer> productList) {
        this.productList = productList;
    }

    public Cart() {
    }

    public LinkedHashMap<Product, Integer> getProducts() {
        return productList;
    }

    public void setProducts(LinkedHashMap<Product, Integer> productList) {
        this.productList = productList;
    }

    public int getSize() {
        int size = 0;
        for (Map.Entry<Product, Integer> item : productList.entrySet()) {
            size += item.getValue();
        }
        return size;
    }

    public int getSumm() {
        int summ = 0;
        for (Map.Entry<Product, Integer> item : productList.entrySet()) {
            summ += ((item.getKey()).getPrice()) * item.getValue();
        }
        return summ;
    }


}
