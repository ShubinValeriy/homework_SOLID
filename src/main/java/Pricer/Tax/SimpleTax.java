package Pricer.Tax;

public class SimpleTax extends TaxSystem {
    private final double TAX_RATE = 0.2;

    @Override
    public double calcTax(double price) {
        return (price * (1 + TAX_RATE)) - price;
    }
}
