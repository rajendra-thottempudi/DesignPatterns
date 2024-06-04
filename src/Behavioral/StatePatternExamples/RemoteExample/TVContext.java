package Behavioral.StatePatternExamples.RemoteExample;

public class TVContext implements State {

    //Notice that Context also implements State and keep a reference of its current state and forwards the request to the state implementation.

    private State tvState;

    public void setState(State state) {
        this.tvState=state;
    }

    public State getState() {
        return this.tvState;
    }

    @Override
    public void doAction() {
        this.tvState.doAction();
    }

}
