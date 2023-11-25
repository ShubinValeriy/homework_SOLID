package Product;

import java.util.Objects;

public class Product {
    protected String name;
    protected String manufacturer;
    protected double costPrice;

    public Product(String name, String manufacturer, double costPrice) {
        this.name = name;
        this.manufacturer = manufacturer;
        checkPrice(costPrice);
        this.costPrice = costPrice;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void changeCostPrice(double costPrice) {
        checkPrice(costPrice);
        this.costPrice = costPrice;
    }

    protected void checkPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не может быть меньше или равна нулю");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Product guest = (Product) obj;
        return name == guest.name && manufacturer == guest.manufacturer && costPrice == guest.costPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturer, costPrice);
    }

    @Override
    public String toString() {
        return String.format("%1$s \"%2$s\"", name, manufacturer);
    }

}
