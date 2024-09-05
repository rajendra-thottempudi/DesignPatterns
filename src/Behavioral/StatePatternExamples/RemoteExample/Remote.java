package Behavioral.StatePatternExamples.RemoteExample;

//acts like a controller to control tv states
public class Remote {

    private State tvState;

    public void setState(State state) {
        this.tvState=state;
    }

    public State getState() {
        return this.tvState;
    }

    public void action() {
        this.tvState.doAction();
    }

    //CAN ALSO be used like this
    public void turnOn(){
        this.setState(new TVStartState());
        this.action();
    }

    public void turnOff(){
        this.setState(new TVStopState());
        this.action();
    }

}
