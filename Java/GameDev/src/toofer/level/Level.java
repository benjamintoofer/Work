package toofer.level;

/**
 * Created by benjamintoofer on 12/15/14.
 */
public class Level {

    private String levelID;
    private int size,row,col;
    public int[] levelMap;
    private int arrayIndex;

    public Level()
    {
        size = row = col = arrayIndex = 0;
    }

    public String getLevelID()
    {
        return levelID;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;

        if(levelMap == null)
        {
            levelMap = new int[size];
        }
    }

    public int getCol()
    {
        return col;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public void setLevelID(String levelID)
    {
        this.levelID = levelID;
    }

    public void addInttoMap(int num)
    {
        levelMap[arrayIndex++] = num;
    }

}
