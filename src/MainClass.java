import tetris.*;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        StartStateInterface startInstance = StartStateInterfaces.getStartStateInstance();

        Scanner inputStream = new Scanner(System.in);
        String inputString;
        while(true)
        {
            startInstance.printScreen();
            inputString = inputStream.next();
            if(inputString.equals("s"))
            {
                doGame();
            }
            else if(inputString.equals("q"))
            {
                return;
            }
            else
            {
                continue;
            }
        }
    }

    public static void doGame()
    {
        PlayStateInterface playInstance = PlayStateInterfaces.getPlayStateInstance();
        playInstance.startInputThread();
        playInstance.startBlockThread();

        loopOut:
        while(true)
        {
            playInstance.generateBlock();

            if(!playInstance.isValid())
            {
                playInstance.endBlockThread();
                playInstance.endInputThread();
                playInstance.printScore();
                break loopOut;
            }
            playInstance.addBlockToBoard();

            String inputString;
            while(true)
            {
                if(playInstance.isInputEntered())
                {
                    inputString = playInstance.getInputValue();

                    if(inputString.equals("a")) // left
                    {
                        playInstance.moveBlockLeft();
                        if(playInstance.isValid()) playInstance.addBlockToBoard();
                        else playInstance.moveBlockRight();
                    }
                    else if(inputString.equals("d")) // right
                    {
                        playInstance.moveBlockRight();
                        if(playInstance.isValid()) playInstance.addBlockToBoard();
                        else playInstance.moveBlockLeft();
                    }
                    else if(inputString.equals("s")) // down
                    {
                        playInstance.moveBlockDown();
                        if(playInstance.isValid()) playInstance.addBlockToBoard();
                        else playInstance.moveBlockUp();
                    }
                    else if(inputString.equals("r")) // rotateRight
                    {
                        playInstance.rotateBlockRight();
                        if(playInstance.isValid()) playInstance.addBlockToBoard();
                        else playInstance.rotateBlockLeft();
                    }
                    else
                    {
                        continue;
                    }
                    playInstance.printScreen();
                }

                if(playInstance.isEnd())
                {
                    playInstance.clearLine();
                    playInstance.printScreen();
                    playInstance.setCurrentBlockNull();

                    if(playInstance.isGameOver())
                    {
                        playInstance.endInputThread();
                        playInstance.endBlockThread();
                        playInstance.printScore();
                        break loopOut;
                    }
                    break;
                }
            }
        }
    }
}
