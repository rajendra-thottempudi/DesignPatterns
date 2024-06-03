package Creational.Builder.PizzaExample;

public class PizzaBuilder {

    Pizza pizza = new Pizza();
    boolean sizeSet = false;
    boolean crustSet = false;

    public PizzaBuilder withTopping(Topping topping) {
        pizza.addTopping(topping);
        pizza.addToPrice(topping.getCost());
        return this;
    }

    public PizzaBuilder withSize(Size size) {
        pizza.setSize(size);
        pizza.addToPrice(size.getCost());
        sizeSet = true;
        return this;
    }

    public PizzaBuilder withCrust(Crust crust) {
        pizza.setCrust(crust);
        pizza.addToPrice(crust.getCost());
        crustSet = true;
        return this;
    }

    public Pizza build() {
        //ensuring that size and crust are mandatory to build a pizza
        if (!sizeSet) {
            throw new IllegalStateException("Size must be set for the pizza.");
        }
        if (!crustSet) {
            throw new IllegalStateException("Crust must be set for the pizza.");
        }
        Pizza builtPizza = this.pizza;
        this.pizza = new Pizza();  // Reset the builder for potential reuse
        sizeSet = false;
        crustSet = false;
        return builtPizza;
    }

    public double calculatePrice() {
        return pizza.getTotalPrice();
    }

}