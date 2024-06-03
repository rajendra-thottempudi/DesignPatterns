package Structural.Decorator.PizzaExample;

public class PizzaDecoratorTest {

    /*
    The patterns like decorator (also called as structural design patterns)
    are used for extensibility or to provide structural changes to already created objects.
    Decorator is used to add new features of an existing object to create a new object.
    */

    public static void main(String[] args) {
        // Creating a pizza with Pepperoni topping
        Pizza pizza = new Pepperoni(new BasePizza());

        // Checking the description
        String description = pizza.bakePizza();
        if ("Basic Pizza with Pepperoni Toppings".equals(description)) {
            System.out.println("Description Test Passed: " + description);
        } else {
            System.out.println("Description Test Failed");
        }

        // Checking the cost
        double cost = pizza.getCost();
        if (cost == 210.0) {
            System.out.println("Cost Test Passed: " + cost);
        } else {
            System.out.println("Cost Test Failed");
        }
    }
}