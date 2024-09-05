package Behavioral.ChainOfResponsibilityExample.LoggerExample;

public class ErrorBasedLogger extends Logger {
    public ErrorBasedLogger(int levels) {
        this.levels=levels;
    }

    @Override
    public void setNextLevelLogger(Logger logger){
        this.next = logger;
    }

    @Override
    protected void logMessage(int levels, String msg) {
        if(levels >= this.levels){
            System.out.println("ERROR LOGGER INFO: "+msg);
        }
        if(this.next!=null) next.logMessage(levels, msg);
    }
}
