package Creational.Builder.PizzaExample;

public enum Topping {

    PEPPERONI {
        public float getCost(){
            return 30;
        }
    }, CHICKEN{
        public float getCost(){
            return 35;
        }
    }, MUSHROOM{
        public float getCost(){
            return 20;
        }
    };

    public abstract float getCost();
}

/*

// Base class
public abstract class Topping {
    // Abstract method to be implemented by subclasses
    public abstract float getCost();
}

// Child class for Pepperoni Topping
public class PepperoniTopping extends Topping {
    @Override
    public float getCost() {
        return 30;
    }
}

// Child class for Chicken Topping
public class ChickenTopping extends Topping {
    @Override
    public float getCost() {
        return 35;
    }
}

// Child class for Mushroom Topping
public class MushroomTopping extends Topping {
    @Override
    public float getCost() {
        return 20;
    }
}

*/