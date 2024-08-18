package tetrisCode;

import tetris.StartStateInterface;

public class StartState extends Tetris implements StartStateInterface {
    @Override
    public void printScreen() {
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
                    if(i == screenWidth / 2 - 6)
                    {
                        System.out.print("TETRIS GAME");
                        i = i + 11;
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
                    if(i == screenWidth / 2 - 11)
                    {
                        System.out.print("s : start / q : quit");
                        i = i + 20;
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
}
