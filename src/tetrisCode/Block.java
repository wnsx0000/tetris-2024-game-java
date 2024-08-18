package tetrisCode;

import java.util.Objects;

public class Block implements Cloneable{
    private int keyX;
    private int keyY;
    private int blockState;
    int[][][] shape;

    Block()
    {
        keyX = 0;
        keyY = Tetris.boardWidth / 2 - 1;
        blockState = 0;
        shape = new int[4][][];
    }

    public synchronized int getKeyX() { return keyX; }
    public synchronized int getKeyY() { return keyY; }
    public synchronized int getBlockState() { return blockState; }
    public synchronized void setKeyX(int keyX) { this.keyX = keyX; }
    public synchronized void setKeyY(int keyY) { this.keyY = keyY; }
    public synchronized void setBlockState(int blockState) { this.blockState = blockState; }

    @Override
    protected synchronized Block clone()
    {
        Block newBlock = null;
        try
        {
            newBlock = ((Block) super.clone());
        }
        catch(CloneNotSupportedException e)
        {
            ;
        }

        return newBlock;
    }

    synchronized void setProperKeyX(int[][] arr)
    {
        outer:
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(arr[i][j] == 1)
                {
                    this.keyX = ((-1) * i);
                    break outer;
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // don't need to change
    }

    @Override
    public String toString() {
        String s;
        s = "KeyX : " + keyX + ", KeyY : " + keyY + ", state : " + blockState;

        return s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyX, keyY, blockState);
    }
}

class jBlock extends Block {
    jBlock()
    {
        int[][] tmp0 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 1}
        };
        shape[0] = tmp0;
        int[][] tmp1 = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
        };
        shape[1] = tmp1;
        int[][] tmp2 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 1}
        };
        shape[2] = tmp2;
        int[][] tmp3 = {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 1, 1, 0}
        };
        shape[3] = tmp3;
        setProperKeyX(tmp0);
    }
}

class lBlock extends Block {
    lBlock()
    {
        int[][] tmp0 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 1, 1, 1}
        };
        shape[0] = tmp0;
        int[][] tmp1 = {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 0}
        };
        shape[1] = tmp1;
        int[][] tmp2 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 1, 0, 0}
        };
        shape[2] = tmp2;
        int[][] tmp3 = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0}
        };
        shape[3] = tmp3;
        setProperKeyX(tmp0);
    }
}

class oBlock extends Block {
    oBlock()
    {
        int[][] tmp0 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0}
        };
        shape[0] = tmp0;
        shape[1] = tmp0;
        shape[2] = tmp0;
        shape[3] = tmp0;
        setProperKeyX(tmp0);
    }
}

class sBlock extends Block {
    sBlock()
    {
        int[][] tmp0 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 1, 1, 0}
        };
        shape[0] = tmp0;
        int[][] tmp1 = {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0}
        };
        shape[1] = tmp1;
        shape[2] = tmp0;
        shape[3] = tmp1;
        setProperKeyX(tmp0);
    }
}

class tBlock extends Block {
    tBlock()
    {
        int[][] tmp0 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 1, 1, 1}
        };
        shape[0] = tmp0;
        int[][] tmp1 = {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 0}
        };
        shape[1] = tmp1;
        int[][] tmp2 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 1, 0}
        };
        shape[2] = tmp2;
        int[][] tmp3 = {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0}
        };
        shape[3] = tmp3;
        setProperKeyX(tmp0);
    }
}

class zBlock extends Block {
    zBlock()
    {
        int[][] tmp0 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 1}
        };
        shape[0] = tmp0;
        int[][] tmp1 = {
                {0, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 0}
        };
        shape[1] = tmp1;
        shape[2] = tmp0;
        shape[3] = tmp1;
        setProperKeyX(tmp0);
    }
}

class iBlock extends Block {
    iBlock()
    {
        int[][] tmp0 = {
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0}
        };
        shape[0] = tmp0;
        int[][] tmp1 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1}
        };
        shape[1] = tmp1;
        shape[2] = tmp0;
        shape[3] = tmp1;
        setProperKeyX(tmp0);
    }
}