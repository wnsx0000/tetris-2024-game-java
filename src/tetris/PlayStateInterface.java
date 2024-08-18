package tetris;

public interface PlayStateInterface {
    void printScreen();
    void printScore();

    void addBlockToBoard();
    boolean isEnd();
    void clearLine();
    boolean isGameOver();
    boolean isValid();

    void generateBlock();
    void moveBlockLeft();
    void moveBlockRight();
    void moveBlockDown();
    void moveBlockUp();
    void rotateBlockRight();
    void rotateBlockLeft();
    void setCurrentBlockNull();

    void startInputThread();
    void endInputThread();
    void startBlockThread();
    void endBlockThread();
    boolean isInputEntered();
    String getInputValue();
}
