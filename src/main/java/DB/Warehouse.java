package DB;

import DB.WORKER.WorkerDB;
import Product.Product;

import java.util.*;


public class Warehouse implements DB{
    private static Warehouse warehouse;
    private WorkerDB workerDB;

    private TreeMap<Product,Integer> treeMap;

    private Warehouse(){
        this.treeMap = new TreeMap<>(Comparator.comparingDouble(o -> o.getCostPrice()));
        this.workerDB = new WorkerDB();
    }

    public static Warehouse getInstance() {
        if (warehouse == null) warehouse = new Warehouse();
        return warehouse;
    }
    @Override
    public void addProduct(Product product, int count){
        workerDB.addProduct(product, count, treeMap);
    }
    @Override
    public void removeProduct(Product product, int count){
        workerDB.removeProduct(product, count, treeMap);
    }
    @Override
    public int getCountProduct(Product product){
        return workerDB.getCountProduct(product, treeMap);
    }
    @Override
    public List<Product> getListProduct (){
        return workerDB.getListProduct(treeMap);
    }
    @Override
    public boolean isEmpty(){
        return workerDB.isEmpty(treeMap);
    }
    @Override
    public void clear(){
        workerDB.clear(treeMap);
    }

    @Override
    public String toString(){
        return "Наш склад";
    }
}
