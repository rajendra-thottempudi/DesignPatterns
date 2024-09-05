package Behavioral.ChainOfResponsibilityExample.LoggerExample;

public class DebugBasedLogger extends Logger {

    public DebugBasedLogger(int levels) {
        this.levels=levels;
    }

    @Override
    public void setNextLevelLogger(Logger logger){
        this.next = logger;
    }

    @Override
    protected void logMessage(int levels, String msg) {
        if(levels >= this.levels){
            System.out.println("DEBUG LOGGER INFO: "+msg);
        }
        if(this.next!=null) next.logMessage(levels, msg);
    }
}
