package DB;

import DB.WORKER.WorkerDB;
import Product.Product;

import java.util.HashMap;
import java.util.List;

public class Basket implements DB {
    private HashMap<Product, Integer> hashMap;
    private WorkerDB workerDB;

    public Basket() {
        this.hashMap = new HashMap<>();
        this.workerDB = new WorkerDB();
    }
    @Override
    public void addProduct(Product product, int count){
        workerDB.addProduct(product, count, hashMap);
    }
    @Override
    public void removeProduct(Product product, int count){
        workerDB.removeProduct(product, count, hashMap);
    }
    @Override
    public int getCountProduct(Product product){
        return workerDB.getCountProduct(product, hashMap);
    }
    @Override
    public List<Product> getListProduct (){
        return workerDB.getListProduct(hashMap);
    }
    @Override
    public boolean isEmpty(){
        return workerDB.isEmpty(hashMap);
    }
    @Override
    public void clear(){
        workerDB.clear(hashMap);
    }

    @Override
    public String toString() {
        return "Ваша корзина";
    }
}
