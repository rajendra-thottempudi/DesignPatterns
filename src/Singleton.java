public class Singleton {
    //earlyLoading
    public static Singleton objA = new Singleton();

    private Singleton(){};

    public static Singleton getInstance(){
        return objA;
    }

    //LazyLoading
    public static Singleton objB;

    public static Singleton getInstanceLazy(){
        if(objB == null){
            synchronized (Singleton.class){
                if(objB == null){
                    objB = new Singleton();
                }
            }
        }

        return objB;
    }
}
