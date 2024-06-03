package Structural.Decorator.PizzaExample;

public class Pepperoni extends PizzaDecorator {

    public Pepperoni(Pizza newPizza) {
        super(newPizza);
    }

    @Override
    public String bakePizza() {
        return super.bakePizza() + " with Pepperoni Toppings";
    }

    @Override
    public float getCost() {
        return super.getCost()+110;
    }
}
