import data.entity.Clothes;
import data.entity.Food;
import data.entity.OtherProduct;
import data.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingCartUi {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            List<Product> productList = new ArrayList<>();
            productList.add(new Food("potato chips"));
            productList.add(new OtherProduct("book"));
            productList.add(new OtherProduct("pencil"));
            productList.add(new Clothes("shirt"));

            System.out.print("Input: ");
            String userInput = keyboard.nextLine();
            System.out.println();

            if(userInput.isBlank()) {
                continue;
            }

            String locationId = getLocationId(userInput);
            String[][] inputProductList = getProductInfo(userInput);

            if(locationId == null || inputProductList == null) {
                continue;
            }

            for(Product product : productList) {
                product.setLocationId(locationId);
                product.setTaxRate();

                for(String[] inputProduct : inputProductList) {
                    if(inputProduct[1].toLowerCase().contains(product.getProductName())) {
                        product.setQuantity(Integer.parseInt(inputProduct[0]));
                        product.setPrice(BigDecimal.valueOf(Double.parseDouble(inputProduct[2])));
                    }
                }
            }

            ShoppingCart shoppingCart = new ShoppingCart(productList);
            printReceipt(shoppingCart);
        }
        while(true);
    }

    public static String getLocationId(String userInput) {
        Pattern pattern = Pattern.compile("[lL]ocation: (\\w{2})");
        Matcher matcher = pattern.matcher(userInput);

        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String[][] getProductInfo(String userInput) {
        Pattern pattern = Pattern.compile("(\\d+)\\s+(.+?)\\s+\\w+\\s+(\\d+\\W\\d+)");
        Matcher matcher = pattern.matcher(userInput);

        int productCount = 0;
        while(matcher.find()) {
            productCount++;
        }

        if(productCount > 0) {
            matcher.reset();
            String[][] inputProductList = new String[productCount][3];
            int index = 0;
            while(matcher.find()) {
                inputProductList[index][0] = matcher.group(1);
                inputProductList[index][1] = matcher.group(2);
                inputProductList[index][2] = matcher.group(3);
                index++;
            }
            return inputProductList;
        }
        return null;
    }

    public static void printReceipt(ShoppingCart shoppingCart) {
        System.out.printf("%-15s %10s %7s\n", "item", "price", "qty");
        for (Product product: shoppingCart.getProductList()){
            if (product.getQuantity() > 0){
                System.out.printf("%-15s $%9.2f %7s\n", product.getProductName(), product.getPrice(), product.getQuantity());
            }
        }
        System.out.printf("%-26s $%6.2f\n", "subtotal", shoppingCart.getSubtotal());
        System.out.printf("%-26s $%6.2f\n", "tax", shoppingCart.getTax());
        System.out.printf("%-26s $%6.2f\n", "total", shoppingCart.getTotal());
        System.out.println();
    }
}
