package DB.WORKER;

import DB.DB;
import Product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkerDB {

    public void addProduct(Product product, int count, Map<Product, Integer> map) {
        checkCount(count);
        if (map.containsKey(product)) {
            int newCount = map.get(product) + count;
            map.put(product, newCount);
        } else {
            map.put(product, count);
        }
    }

    public void removeProduct(Product product, int count, Map<Product, Integer> map) {
        checkCount(count);
        checkProduct(product, map);
        int newCount = map.get(product) - count;
        if (newCount < 0) {
            throw new IllegalArgumentException("Продукта - " + product + " нет в достаточном количестве");
        }
        map.put(product, newCount);
    }

    public int getCountProduct(Product product, Map<Product, Integer> map) {
        checkProduct(product, map);
        return map.get(product);
    }

    public List<Product> getListProduct(Map<Product, Integer> map) {
        Set<Product> setProduct = map.keySet();
        List<Product> listProduct = new ArrayList<Product>();
        for (Product product : setProduct) {
            listProduct.add(product);
        }
        return listProduct;
    }

    public boolean isEmpty(Map<Product, Integer> map) {
        return map.isEmpty();
    }

    public void clear(Map<Product, Integer> map) {
        map.clear();
    }

    public String moveFromTo(DB from, DB to, Product product, int count) {
        checkCount(count);
        if (from.getCountProduct(product) < count) {
            return "Товара " + product + " недостаточно на " + from;
        }
        from.removeProduct(product, count);
        to.addProduct(product, count);
        return "Товар " + product + " добавлен в " + to;
    }

    private void checkProduct(Product product, Map<Product, Integer> map) {
        if (!map.containsKey(product)) {
            throw new IllegalArgumentException("Продукта - " + product + " нет");
        }
    }

    private void checkCount(double count) {
        if (count < 0) {
            throw new IllegalArgumentException("Количество товара не может быть меньше нуля");
        }
    }


}
