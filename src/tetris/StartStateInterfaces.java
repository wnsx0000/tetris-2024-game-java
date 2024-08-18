package tetris;

import tetrisCode.StartState;

public class StartStateInterfaces {
    public static StartStateInterface getStartStateInstance()
    {
        return new StartState();
    }
}
