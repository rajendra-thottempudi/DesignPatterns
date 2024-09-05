package Behavioral.ChainOfResponsibilityExample.LoggerExample;

public abstract class Logger {
    public static int OUTPUTINFO=1;
    public static int ERRORINFO=2;
    public static int DEBUGINFO=3;
    protected int levels;
    Logger next;
    public abstract void setNextLevelLogger(Logger nextLevelLogger);
//    {
//        this.nextLevelLogger = nextLevelLogger;
//    }
//    public void logMessage(int levels, String msg){
//        if(this.levels<=levels){
//            displayLogInfo(msg);
//        }
//        if (nextLevelLogger!=null) {
//            nextLevelLogger.logMessage(levels, msg);
//        }
//    }
    protected abstract void logMessage(int levels, String msg);
}
