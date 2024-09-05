package Behavioral.ChainOfResponsibilityExample.LoggerExample;

public class ConsoleBasedLogger extends Logger {

    public ConsoleBasedLogger(int levels) {
        this.levels=levels;
    }

    @Override
    public void setNextLevelLogger(Logger logger){
        this.next = logger;
    }

    @Override
    protected void logMessage(int levels, String msg) {
        if(levels >= this.levels){
            System.out.println("CONSOLE LOGGER INFO: "+msg);
        }
        if(this.next!=null) next.logMessage(levels, msg);
    }
}
