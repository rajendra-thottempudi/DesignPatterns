package Behavioral.StatePatternExamples.ControllerExample;

public class StatePatternDemo {

    //A State Pattern says that "the class behavior changes based on its state".
    //In State Pattern, we create objects which represent various states and a context object whose behavior varies as its state object changes.

    Controller controller;
    StatePatternDemo(String con) {
        controller = new Controller();
        //the following trigger should be made by the user
        if(con.equalsIgnoreCase("management"))
            controller.setManagementConnection();
        if(con.equalsIgnoreCase("sales"))
            controller.setSalesConnection();
        if(con.equalsIgnoreCase("accounting"))
            controller.setAccountingConnection();
        controller.open();
        controller.log();
        controller.close();
        controller.update();
    }

// to run : type "java StatePatternDemo.java sales/management/accounting" in terminal in this directory
    public static void main(String args[]) {

        new StatePatternDemo(args[0]);

    }

}
