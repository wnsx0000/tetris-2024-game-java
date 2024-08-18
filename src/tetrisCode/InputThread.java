package tetrisCode;

public class InputThread extends Thread{
    PlayState playInstance;
    @Override
    public void run()
    {
        int input;
        String value;
        while(playInstance.inputThreadFlag)
        {
            try
            {
                input = PlayState.nonBlockingInput();

                value = switch(input)
                {
                    case 1 -> "a";
                    case 2 -> "d";
                    case 3 -> "s";
                    case 4 -> "r";
                    default -> "w";
                };

                if(!value.equals("w"))
                {
                    playInstance.inputQueue.add(value);
                }
            }
            catch(Exception e)
            {
                ;
            }
        }
    }

    InputThread(PlayState p)
    {
        playInstance = p;
    }
}
