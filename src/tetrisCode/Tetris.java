package tetrisCode;

abstract public class Tetris {
    static {
        System.loadLibrary("MyJNI");
    }
    static final int screenWidth = 30; // 10 + 10 + 10
    static final int screenHeight = 19; // 2 + 15 + 2
    static final int boardWidth = 10;
    static final int boardHeight = 15;

    abstract public void printScreen();
    static native void clearConsole();
    static native int nonBlockingInput();
}
