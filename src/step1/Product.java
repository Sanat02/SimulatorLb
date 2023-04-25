package step1;

import java.awt.*;
import java.util.Random;

public class Product {
    private double weight;
    private ProductType goodType;
    private Quality qualityOfGood;
    private double price;
    private Random random;

    public Product() {
        random = new Random();
        this.goodType = getProductType();
        this.weight = random.nextInt(10, 51);
        qualityOfGood = getQuality();
        price = getPrice();


    }

    private ProductType getProductType() {
        int num = random.nextInt(0, 6);
        ProductType element1 = ProductType.DYE; //пока что просто проинициализируем
        switch (num) {
            case 0:
                element1 = ProductType.DYE;
                break;
            case 1:
                element1 = ProductType.FLOUR;
                break;
            case 2:
                element1 = ProductType.GRAIN;
                break;
            case 3:
                element1 = ProductType.MEAT;
                break;
            case 4:
                element1 = ProductType.DRIED_FOOD;
                break;
            case 5:
                element1 = ProductType.TEXTILE;
                break;
            default:
                System.out.println("NO such product!");

        }
        return element1;
    }

    private double priceOfProduct() {
        double num;
        switch (goodType) {
            case DYE -> num = 60;
            case MEAT -> num = 120;
            case FLOUR -> num = 55;
            case GRAIN -> num = 35;
            case TEXTILE -> num = 150;
            case DRIED_FOOD -> num = 200;
            default -> num = 0;
        }
        return num;
    }


    private double getPrice() {
        price = priceOfProduct();
        switch (qualityOfGood) {
            case NORMAL -> price *= 1.2;
            case LITTLE_SPOILED -> price *= 0.95;
            case HALF_SPOILED -> price *= 0.55;
            case ALMOST_SPOILED -> price *= 0.25;
            case WHOLE_SPOILED -> price *= 0.1;
        }
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "weight=" + weight +
                ", goodType=" + goodType +
                ", qualityOfGood=" + qualityOfGood +
                ", price=" + price +
                '}';
    }

    private Quality getQuality() {
        int num = random.nextInt(0, 5);
        Quality element1 = Quality.NORMAL; //пока что просто проинициализируем
        switch (num) {
            case 0:
                element1 = Quality.NORMAL;
                break;
            case 1:
                element1 = Quality.LITTLE_SPOILED;
                break;
            case 2:
                element1 = Quality.HALF_SPOILED;
                break;
            case 3:
                element1 = Quality.ALMOST_SPOILED;
                break;
            case 4:
                element1 = Quality.WHOLE_SPOILED;
                break;
            default:
                System.out.println("NO such pquality!");

        }
        return element1;

    }

}
