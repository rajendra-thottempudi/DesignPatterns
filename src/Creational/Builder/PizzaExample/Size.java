package Creational.Builder.PizzaExample;

public enum Size {
    MEDIUM {
        //works even without override annotation
        @Override
        public float getCost() {
            return 100;
        }
    }, LARGE {
        //works even without override annotation
        @Override
        public float getCost() {
            return 160;
        }
    };

    // this method should be implemented in all the enum constants of type Size
    public abstract float getCost();
}