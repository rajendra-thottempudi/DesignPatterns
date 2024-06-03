package Creational.Builder.PizzaExample;
import java.util.*;

public class Pizza {

    private float totalPrice = 0;

    private Size size;
    private List<Topping> toppings = new ArrayList<>();
    private Crust crust;
    private Cheese cheese;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void addToPrice(float price) {
        this.totalPrice = totalPrice + price;
    }
}