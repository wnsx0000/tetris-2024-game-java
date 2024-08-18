package tetrisCode;

public class BlockMovingThread extends Thread {
    PlayState playInstance;
    BlockMovingThread(PlayState p)
    {
        playInstance = p;
    }
    @Override
    public void run()
    {
        while(playInstance.blockThreadFlag)
        {
            try
            {
                if((playInstance.getCurrentBlock() != null) && (!playInstance.isEnd()))
                {
                    playInstance.printScreen();
                    sleep(playInstance.getSpeed());

                    if(!playInstance.isMoving())
                    {
                        playInstance.setIsMoving(true);

                        playInstance.moveBlockDown();
                        if (playInstance.isValid()) playInstance.addBlockToBoard();
                        else playInstance.moveBlockUp();

                        playInstance.setIsMoving(false);
                    }
                }
            }
            catch (Exception e)
            {
                ;
            }
        }
    }
}
