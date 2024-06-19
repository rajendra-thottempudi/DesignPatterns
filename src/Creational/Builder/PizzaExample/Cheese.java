package Creational.Builder.PizzaExample;

public enum Cheese {
    AMERICAN {
        public float getCost() {
            return 40;
        }
    }, ITALIAN {
        public float getCost() {
            return 60;
        }
    };

    public abstract float getCost();
}

/*
// Base class
public abstract class Cheese {
    // Abstract method to be implemented by subclasses
    public abstract float getCost();
}

// Child class for American Cheese
public class AmericanCheese extends Cheese {
    @Override
    public float getCost() {
        return 40;
    }
}

// Child class for Italian Cheese
public class ItalianCheese extends Cheese {
    @Override
    public float getCost() {
        return 60;
    }
}

*/