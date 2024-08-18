package tetrisCode;

import tetris.PlayStateInterface;
import tetris.PlayStateInterfaces;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PlayState extends Tetris implements PlayStateInterface {
    final private int speed = 800; // sleep time (ms)
    private int score = 0;
    private final Board currentBoard;
    private Block currentBlock = null;
    private Block tmpMovingBlock = null;
    private final InputThread inputThread;
    private final BlockMovingThread blockMovingThread;
    private Boolean canGoDown = true;
    Queue<String> inputQueue;
    boolean inputThreadFlag = true;
    boolean blockThreadFlag = true;
    private boolean isMoving;

    public PlayState()
    {
        currentBoard = new Board();
        inputThread = new InputThread(this);
        blockMovingThread = new BlockMovingThread(this);
        inputQueue = new LinkedList<String>();
    }

    int getSpeed() { return speed; }
    int getScore() { return score; }
    synchronized Block getCurrentBlock() { return currentBlock; }
    void increaseScore() { score += 10; }
    synchronized public Boolean getCanGoDown() { return canGoDown; }
    public boolean isMoving() { return isMoving; }
    public void setIsMoving(boolean b) { isMoving = b; }

    @Override
    public synchronized void printScreen()
    {
        justPrintScreen();

        if(isMoving())
        {
            setIsMoving(false);
        }
    }

    private synchronized void justPrintScreen()
    {
        clearConsole();

        for(int i = 0; i < (screenHeight - boardHeight) / 2; i++)
        {
            for(int j = 0; j < screenWidth; j++)
            {
                System.out.print('@');
            }

            System.out.println();
        }

        for(int i = 0; i < boardHeight; i++)
        {
            for (int j = 0; j < (screenWidth - boardWidth) / 2; j++)
            {
                System.out.print('@');
            }

            for(int j = 0; j < boardWidth; j++)
            {
                System.out.print((currentBoard.tetrisBoard[i][j] == 1 ? 'O' : ' '));
            }

            for (int j = 0; j < (screenWidth - boardWidth) / 2; j++)
            {
                System.out.print('@');
            }

            System.out.println();
        }

        for(int i = 0; i < (screenHeight - boardHeight) / 2; i++)
        {
            for(int j = 0; j < screenWidth; j++)
            {
                System.out.print('@');
            }
            System.out.println();
        }

        System.out.println("Score : " + Integer.toString(getScore()) + '\n');
        System.out.println("a : Left");
        System.out.println("d : Right");
        System.out.println("s : Drop");
        System.out.println("r : Rotate");
    }

    @Override
    public synchronized void printScore()
    {
        addWrongCurrentBlockToBoard();

        printGameOverScreen();
        try
        {
            Thread.sleep(1500);
        }
        catch (Exception e)
        {
            ;
        }

        Scanner input = new Scanner(System.in);
        String str;
        printScoreScreen();
        str = input.next();
    }

    private synchronized void addWrongCurrentBlockToBoard()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(isIndexValid(i, j, currentBlock) &&
                        (currentBoard.tetrisBoard[currentBlock.getKeyX() + i][currentBlock.getKeyY() + j] != 1))
                {
                    currentBoard.tetrisBoard[currentBlock.getKeyX() + i][currentBlock.getKeyY() + j] =
                            (currentBlock.shape[currentBlock.getBlockState()][i][j] == 1) ? 1 : 0;
                }
            }
        }
    }

    private synchronized void printGameOverScreen()
    {
        clearConsole();

        for(int i = 0; i < (screenHeight - boardHeight) / 2; i++)
        {
            for(int j = 0; j < screenWidth; j++)
            {
                System.out.print('@');
            }

            System.out.println();
        }

        for(int i = 0; i < boardHeight; i++)
        {
            if(i == boardWidth / 2 + 1)
            {
                for (int j = 0; j < screenWidth; j++)
                {
                    System.out.print('-');
                }
                System.out.println();
                continue;
            }

            if(i == boardWidth / 2)
            {
                for (int j = 0; j < screenWidth; j++)
                {
                    if(j == screenWidth / 2 - 5)
                    {
                        System.out.print("GAME OVER");
                        j = j + 9;
                    }
                    else
                    {
                        System.out.print(' ');
                    }
                }
                System.out.println();
                continue;
            }

            if(i == boardWidth / 2 - 1)
            {
                for (int j = 0; j < screenWidth; j++)
                {
                    System.out.print('-');
                }
                System.out.println();
                continue;
            }

            for (int j = 0; j < (screenWidth - boardWidth) / 2; j++)
            {
                System.out.print('@');
            }

            for(int j = 0; j < boardWidth; j++)
            {
                System.out.print((currentBoard.tetrisBoard[i][j] == 1 ? 'O' : ' '));
            }

            for (int j = 0; j < (screenWidth - boardWidth) / 2; j++)
            {
                System.out.print('@');
            }

            System.out.println();
        }

        for(int i = 0; i < (screenHeight - boardHeight) / 2; i++)
        {
            for(int j = 0; j < screenWidth; j++)
            {
                System.out.print('@');
            }
            System.out.println();
        }

        System.out.println("Score : " + Integer.toString(getScore()) + '\n');
        System.out.println("a : Left");
        System.out.println("d : Right");
        System.out.println("s : Drop");
        System.out.println("r : Rotate");
    }

    private synchronized void printScoreScreen()
    {
        clearConsole();

        for (int i = 0; i < screenWidth; i++)
        {
            System.out.print('@');
        }
        System.out.println();

        for (int j = 2; j < screenHeight; j++)
        {
            if(j == screenHeight / 2)
            {
                for (int i = 0; i < screenWidth; i++)
                {
                    if ((i == 0) || (i == screenWidth - 1))
                    {
                        System.out.print('@');
                    }
                    else
                    {
                        System.out.print(' ');
                    }
                    if(i == screenWidth / 2 - 5)
                    {
                        System.out.print("GAME OVER");
                        i = i + 9;
                    }
                }
                System.out.println();
                continue;
            }

            if(j == screenHeight / 2 + 2)
            {
                for (int i = 0; i < screenWidth; i++)
                {
                    if ((i == 0) || (i == screenWidth - 1))
                    {
                        System.out.print('@');
                    }
                    else
                    {
                        System.out.print(' ');
                    }
                    if(i == screenWidth / 2 - (i + 13 + Integer.toString(getScore()).length()) / 2)
                    {
                        System.out.print("Your Score : " + getScore());
                        i = i + 13 + Integer.toString(getScore()).length();
                    }
                }
                System.out.println();
                continue;
            }

            if(j == screenHeight / 2 + 3)
            {
                for (int i = 0; i < screenWidth; i++)
                {
                    if ((i == 0) || (i == screenWidth - 1))
                    {
                        System.out.print('@');
                    }
                    else
                    {
                        System.out.print(' ');
                    }
                    if(i == screenWidth / 2 - 13)
                    {
                        System.out.print("Press any key to restart");
                        i = i + 24;
                    }
                }
                System.out.println();
                continue;
            }

            for (int i = 0; i < screenWidth; i++)
            {
                if ((i == 0) || (i == screenWidth - 1))
                {
                    System.out.print('@');
                }
                else
                {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }

        for (int i = 0; i < screenWidth; i++)
        {
            System.out.print('@');
        }

        System.out.print("\n\nYour input : ");
    }

    @Override
    public synchronized void addBlockToBoard()
    {
        if(tmpMovingBlock != null) // remove
        {
            removeBlockInBoard(tmpMovingBlock);
        }

        addBlockToBoard(currentBlock);
    }

    private synchronized boolean isIndexValid(int i, int j, Block block) // is currentBlock's i, j index is valid?
    {
        boolean rst = true;
        if((block.getKeyX() + i) < 0 || (block.getKeyX() + i) >= Tetris.boardHeight)
        {
            rst = false;
        }
        else if((block.getKeyY() + j) < 0 || (block.getKeyY() + j >= Tetris.boardWidth))
        {
            rst = false;
        }
        return rst;
    }

    private synchronized void removeBlockInBoard(Block block)
    {
        if(block == null)
        {
            return;
        }

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(isIndexValid(i, j, block) && (block.shape[block.getBlockState()][i][j] == 1))
                {
                    currentBoard.tetrisBoard[block.getKeyX() + i][block.getKeyY() + j] = 0;
                }
            }
        }
    }

    private synchronized void addBlockToBoard(Block block)
    {
        if(block == null)
        {
            return;
        }

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(isIndexValid(i, j, block) && (currentBoard.tetrisBoard[block.getKeyX() + i][block.getKeyY() + j] != 1))
                {
                    currentBoard.tetrisBoard[block.getKeyX() + i][block.getKeyY() + j] =
                            (block.shape[block.getBlockState()][i][j] == 1) ? 1 : 0;
                }
            }
        }
    }

    @Override
    public synchronized boolean isEnd()
    {
        return !canGoDown;
    }

    @Override
    public synchronized void clearLine()
    {
        int sum;
        for(int i = 0; i < Tetris.boardHeight; i++)
        {
            sum = 0;
            for(int j = 0; j < Tetris.boardWidth; j++)
            {
                if(currentBoard.tetrisBoard[i][j] == 1)
                {
                    sum++;
                }
            }

            if(sum == Tetris.boardWidth) // delete row i
            {
                deleteLine(i);
                increaseScore();
            }
        }
    }

    private synchronized void deleteLine(int row)
    {
        for(int i = row; i >= 0; i--) // swap i and i - 1
        {
            if(i == 0)
            {
                for(int j = 0; j < Tetris.boardWidth; j++)
                {
                    currentBoard.tetrisBoard[i][j] = 0;
                }

                break;
            }

            for(int j = 0; j < Tetris.boardWidth; j++) // swap
            {
                currentBoard.tetrisBoard[i][j] = currentBoard.tetrisBoard[i - 1][j];
            }
        }
    }

    @Override
    public boolean isGameOver() { // don't need to implement
        return false;
    }

    @Override
    public synchronized boolean isValid()
    {
        if(currentBlock == null)
        {
            return false;
        }

        boolean rst = true;

        // remove tmp in board
        removeBlockInBoard(tmpMovingBlock);

        // judge
        outer:
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(currentBlock.shape[currentBlock.getBlockState()][i][j] == 1)
                {
                    if((!isIndexValid(i, j, currentBlock)) ||
                            (currentBoard.tetrisBoard[currentBlock.getKeyX() + i][currentBlock.getKeyY() + j] == 1))
                    {
                        rst = false;
                        break outer;
                    }
                }
            }
        }

        // write tmp to board
        addBlockToBoard(tmpMovingBlock);

        return rst;
    }

    @Override
    public synchronized void generateBlock()
    {
        double randomValue = Math.random();
        switch( ((int)(randomValue * 100)) % 7 )
        {
            case 0:
                currentBlock = new jBlock();
                break;
            case 1:
                currentBlock = new lBlock();
                break;
            case 2:
                currentBlock = new oBlock();
                break;
            case 3:
                currentBlock = new sBlock();
                break;
            case 4:
                currentBlock = new tBlock();
                break;
            case 5:
                currentBlock = new zBlock();
                break;
            case 6:
                currentBlock = new iBlock();
                break;
        }

        canGoDown = true;
    }

    @Override
    public synchronized void setCurrentBlockNull()
    {
        currentBlock = null;
        tmpMovingBlock = null;
    }

    @Override
    public synchronized void moveBlockLeft()
    {
        tmpMovingBlock = currentBlock.clone();
        currentBlock.setKeyY(currentBlock.getKeyY() - 1);
    }

    @Override
    public synchronized void moveBlockRight()
    {
        tmpMovingBlock = currentBlock.clone();
        currentBlock.setKeyY(currentBlock.getKeyY() + 1);
    }

    @Override
    public synchronized void moveBlockDown()
    {
        tmpMovingBlock = currentBlock.clone();
        currentBlock.setKeyX(currentBlock.getKeyX() + 1);
    }

    @Override
    public synchronized void moveBlockUp()
    {
        tmpMovingBlock = currentBlock.clone();
        currentBlock.setKeyX(currentBlock.getKeyX() - 1);

        canGoDown = false;
    }

    @Override
    public synchronized void rotateBlockLeft()
    {
        tmpMovingBlock = currentBlock.clone();
        currentBlock.setBlockState((currentBlock.getBlockState() - 1 + 4) % 4);
    }

    @Override
    public synchronized void rotateBlockRight()
    {
        tmpMovingBlock = currentBlock.clone();
        currentBlock.setBlockState((currentBlock.getBlockState() + 1) % 4);
    }

    @Override
    public void startInputThread()
    {
        inputThreadFlag = true;
        inputThread.start();
    }

    @Override
    public void endInputThread()
    {
        inputThreadFlag = false;
    }

    @Override
    public void startBlockThread()
    {
        blockThreadFlag = true;
        blockMovingThread.start();
    }

    @Override
    public void endBlockThread()
    {
        blockThreadFlag = false;
    }

    @Override
    public synchronized boolean isInputEntered()
    {
        boolean rst;
        rst = !inputQueue.isEmpty();

        if(rst) // input entered
        {
            if(isMoving())
            {
                return false;
            }
            else // not moving
            {
                setIsMoving(true);

                return true;
            }
        }
        else // input not entered
        {
            return false;
        }
    }

    @Override
    public synchronized String getInputValue()
    {
        return inputQueue.poll();
    }
}

