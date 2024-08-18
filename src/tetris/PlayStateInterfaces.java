package tetris;

import tetrisCode.*;

public class PlayStateInterfaces {
    public static PlayStateInterface getPlayStateInstance()
    {
        return new PlayState();
    }
}
