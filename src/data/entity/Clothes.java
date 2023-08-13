package data.entity;

public class Clothes extends Product {
    public Clothes(String productName) {
        super(productName);
    }

    @Override
    public void setTaxRate() {
        if(super.getLocationId().equalsIgnoreCase("CA")) {
            setTaxRate(0.0975);
        }
        else {
            super.setTaxRate();
        }
    }
}
