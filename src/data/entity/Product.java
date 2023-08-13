package data.entity;

import java.math.BigDecimal;

public class Product {
    private String locationId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private Double taxRate;

    public Product(String productName) {
        this.locationId = "";
        this.productName = productName;
        this.price = BigDecimal.valueOf(0);
        this.quantity = 0;
        this.taxRate = 0.0;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public void setTaxRate() {
        this.taxRate = 0.0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "locationId='" + locationId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", taxRate=" + taxRate +
                '}';
    }
}
