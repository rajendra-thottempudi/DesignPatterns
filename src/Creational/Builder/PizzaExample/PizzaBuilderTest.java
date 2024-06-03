package Creational.Builder.PizzaExample;

public class PizzaBuilderTest {

    public static void main(String[] args) {
        // Create a Pizza using the builder pattern
        Pizza pizza = new PizzaBuilder().withCrust(Crust.THIN).withTopping(Topping.CHICKEN).withSize(Size.LARGE).build();

        // Print the properties of the Pizza
        System.out.println("Topping: " + pizza.getToppings());
        System.out.println("Expected Topping: " + Topping.CHICKEN);
        System.out.println("Size: " + pizza.getSize());
        System.out.println("Expected Size: " + Size.LARGE);
        System.out.println("Crust: " + pizza.getCrust());
        System.out.println("Expected Crust: " + Crust.THIN);
        System.out.println("Total Price: " + pizza.getTotalPrice());
        System.out.println("Expected Total Price: 265.0");

        // Manually check the expected values
        if (pizza.getToppings().get(0) == Topping.CHICKEN &&
                pizza.getSize() == Size.LARGE &&
                pizza.getCrust() == Crust.THIN &&
                pizza.getTotalPrice() == 265.0) {
            System.out.println("Pizza built successfully.");
        } else {
            System.out.println("Pizza building failed.");
        }
    }
}