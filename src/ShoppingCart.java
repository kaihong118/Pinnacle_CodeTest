import data.entity.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ShoppingCart {
    private List<Product> productList;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal total;

    public ShoppingCart(List<Product> productList) {
        this.productList = productList;
        setSubtotal();
        setTax();
        setTotal();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setSubtotal() {
        BigDecimal subtotal = BigDecimal.valueOf(0);
        for(Product product : productList) {
            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
        }
        this.subtotal = subtotal;
    }

    public void setTax() {
        BigDecimal taxPrice = BigDecimal.valueOf(0);
        for(Product product : productList) {
            taxPrice = taxPrice.add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())).multiply(BigDecimal.valueOf(product.getTaxRate())));
        }
        taxPrice = taxPrice.divide(BigDecimal.valueOf(0.05),0,RoundingMode.CEILING).multiply(BigDecimal.valueOf(0.05));
        this.tax = taxPrice;
    }

    public void setTotal() {
        this.total = this.subtotal.add(this.tax);
    }
}
