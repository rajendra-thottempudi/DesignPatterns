package Creational.Builder.PizzaExample;

public enum Crust {

    THIN  {
        public float getCost(){
            return 70;
        }
    } , STUFFED{
        public float getCost(){
            return 90;
        }
    };

    public abstract float getCost();
}

/*

// Base class
public abstract class Crust {
    // Abstract method to be implemented by subclasses
    public abstract float getCost();
}

// Child class for Thin Crust
public class ThinCrust extends Crust {
    @Override
    public float getCost() {
        return 70;
    }
}

// Child class for Stuffed Crust
public class StuffedCrust extends Crust {
    @Override
    public float getCost() {
        return 90;
    }

}*/