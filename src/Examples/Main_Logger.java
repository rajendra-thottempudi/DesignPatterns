package Examples;

// Singleton class
class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Private constructor to prevent instantiation
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void log(String log) {
        System.out.println(log);
    }
}

// Main class to demonstrate usage
public class Main_Logger {
    public static void main(String[] args) {
        Singleton logger1 = Singleton.getInstance();
        System.out.println(logger1);

        Singleton logger2 = Singleton.getInstance();
        System.out.println(logger2);

        MyClass.doSomething("hello");
    }
}

// Another class with a static method
class MyClass {
    public static void doSomething(String text) {
        System.out.println(text);
    }
}

