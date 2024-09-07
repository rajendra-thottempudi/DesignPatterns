package practice;
import java.util.*;

interface Topping{
    public double getPrice();
    public String getDescription();
}

class MushroomTopping implements Topping{
    double price = 10;

    public double getPrice(){
        return price;
    }

    public String getDescription(){
        return "Mushroom Topping";
    }
}

enum Size {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int value;

    Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

enum Cheese{
    Pepperoni,
    Italian,
    Normal
}

class Pizza{
    List<Topping> toppings;
    Size size;
    Cheese cheese;
    double totalprice;

    Pizza(){
        toppings = new ArrayList<>();
    }
    //getters and setters

    public void addTopping(Topping t){
        toppings.add(t);
    }

    public void addCheese(Cheese c){
        cheese = c;
    }

    public void withSize(Size s){
        this.size = s;
    }

    public double getTotalprice(){
        double sum = 0.0;
        for(Topping t: toppings){
            sum += t.getPrice();
        }
        return totalprice = sum * size.getValue();
    }
}

class PizzaBuilder{
    Pizza pizza;

    PizzaBuilder(){
        this.pizza = new Pizza();
    }

    public PizzaBuilder addTopping(Topping t){
        pizza.addTopping(t);
        return this;
    }

    public PizzaBuilder addCheese(Cheese c){
        pizza.addCheese(c);
        return this;
    }

    public PizzaBuilder withSize(Size s){
        pizza.withSize(s);
        return this;
    }

    public Pizza build() {
        return this.pizza;
    }
}


public class PizzaExample {
    public static void main(String[] args){
        Pizza p = new PizzaBuilder().addCheese(Cheese.Italian)
                                    .addTopping(new MushroomTopping())
                                    .withSize(Size.MEDIUM)
                                    .build();
        System.out.println(" I am building a pizza  AND ITS price is : " + p.getTotalprice());
    }
}
