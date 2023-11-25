package DB;

import Product.Product;

import java.util.List;

public interface DB {
    void addProduct(Product product, int count);

    void removeProduct(Product product, int count);

    int getCountProduct(Product product);

    List<Product> getListProduct();

    boolean isEmpty();

    void clear();
}
