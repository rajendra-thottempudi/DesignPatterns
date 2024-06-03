package Structural.Decorator.PizzaExample;

public class Mushroom extends PizzaDecorator {

    public Mushroom(Pizza newPizza) {
        super(newPizza);
    }

    @Override
    public String bakePizza() {
        return super.bakePizza() + " with Mushroom Toppings";
    }

    @Override
    public float getCost() {
        return super.getCost()+80;
    }
}
