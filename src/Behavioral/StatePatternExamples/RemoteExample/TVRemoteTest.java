package Behavioral.StatePatternExamples.RemoteExample;

public class TVRemoteTest {

    public static void main(String[] args) {
        Remote context = new Remote();
        State tvStartState = new TVStartState();
        State tvStopState = new TVStopState();

        context.setState(tvStartState);
        context.action();


        context.setState(tvStopState);
        context.action();

    }

}
