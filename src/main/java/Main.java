
import DB.*;
import Pricer.SimplePricer;
import Pricer.Tax.SimpleTax;
import Product.Product;
import UserWorkPackage.ActionUser;



public class Main {
    public static void main(String[] args) {



        Product sourCream = new Product("Сметана", "Простоквашино", 150);
        Product milk = new Product("Молоко", "Простоквашино", 80);
        Product pelmeni = new Product("Пельмени", "Пельмэн", 300);
        Product bread = new Product("Хлеб", "Краюшка", 30);

        Warehouse warehouse = Warehouse.getInstance();

        warehouse.addProduct(milk, 10);
        warehouse.addProduct(milk, 5);
        warehouse.addProduct(sourCream, 10);
        warehouse.addProduct(pelmeni, 10);
        warehouse.addProduct(bread, 10);





        ActionUser actionUser = new ActionUser();
        actionUser.startShop(Warehouse.getInstance(), new SimplePricer(new SimpleTax()));


    }






}
