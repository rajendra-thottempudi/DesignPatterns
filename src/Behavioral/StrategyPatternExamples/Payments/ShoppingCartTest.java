package Behavioral.StrategyPatternExamples.Payments;

public class ShoppingCartTest {
    /*
    * Strategy Pattern is very similar to State Pattern. One of the difference is that Context contains state as instance variable and there can be multiple
    * tasks whose implementation can be dependent on the state whereas in strategy pattern strategy is passed as argument to the method and
    * context object doesn’t have any variable to store it.
    *
    * Strategy pattern is useful when we have multiple algorithms for specific task and we want our application to be flexible
    * to chose any of the algorithm at runtime for specific task.
    */

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("1234",10);
        Item item2 = new Item("5678",40);

        cart.addItem(item1);
        cart.addItem(item2);

        //pay by paypal
        cart.pay(new PaypalStrategy("myemail@example.com", "mypwd"));

        //pay by credit card
        cart.pay(new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"));
    }

}
