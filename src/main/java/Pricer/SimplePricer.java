package Pricer;
import Pricer.Tax.TaxSystem;
import Product.Product;

public class SimplePricer implements Pricer {
    protected TaxSystem taxSystem;
    private final int INCOME_RATE = 10;
    public SimplePricer(TaxSystem taxSystem){
        this.taxSystem = taxSystem;
    }
    @Override
    public double calcSalesPrice(Product product){
        double costPrice = product.getCostPrice();
        double income = costPrice * INCOME_RATE / 100;
        double tax = taxSystem.calcTax(costPrice+income);
        return costPrice + income + tax;
    }



}
