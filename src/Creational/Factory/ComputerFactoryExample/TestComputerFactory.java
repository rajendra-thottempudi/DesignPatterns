package Creational.Factory.ComputerFactoryExample;

public class TestComputerFactory {

    /*
    Creational.Factory pattern removes the instantiation of actual implementation classes from client code.
    Creational.Factory pattern makes our code more robust, less coupled and easy to extend.
    For example, we can easily change PC class implementation because client program is unaware of this.
    Creational.Factory pattern provides abstraction between implementation and client classes through inheritance.
    */

    public static void main(String[] args) {
        Computer pc = ComputerFactory.getComputer("pc","2 GB","500 GB","2.4 GHz");
        Computer server = ComputerFactory.getComputer("server","16 GB","1 TB","2.9 GHz");
        System.out.println("Creational.Factory PC Config::"+pc);
        System.out.println("Creational.Factory Server Config::"+server);
    }

}
