package toofer.level;

import java.io.*;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by benjamintoofer on 12/14/14.
 */
public class LevelManager {

    private File levelText;
    private BufferedReader bufferedReader;
    private String bufferText;
    private ArrayList<Level> levelList;
    private int currentLevelIndex = 0;

    public LevelManager(URL path) throws IOException
    {
        levelList = new ArrayList<Level>();


        try {
            bufferedReader = new BufferedReader(new FileReader(path.getFile()));


            while((bufferText = bufferedReader.readLine())!= null)
            {
                getTextInput(bufferText);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No Level File Found");
        }

    }

    public Level getLevel()
    {
        return levelList.get(currentLevelIndex);
    }
    private void getTextInput(String textInput)
    {
        int startIndex = 0;

        //Get name of level
        if(textInput.startsWith("n"))
        {
            levelList.add(new Level());

            startIndex = textInput.indexOf(":");
            startIndex++;

            String name = textInput.substring(startIndex,textInput.length()).trim();
            levelList.get(levelList.size() - 1).setLevelID(name);
        }
        else if(textInput.startsWith("s"))
        {
            startIndex = textInput.indexOf(":");
            startIndex++;

            int size = Integer.parseInt(textInput.substring(startIndex,textInput.length()).trim());
            levelList.get(levelList.size() - 1).setSize(size);
        }
        else if(textInput.matches(".*\\d.*"))
        {
            //Increase row count
            int row = levelList.get(levelList.size() - 1).getRow();
            int col = levelList.get(levelList.size() - 1).getCol();
            row++;
            levelList.get(levelList.size() - 1).setRow(row);

            //Setting col count if it hasnt been set yet
            if(col == 0)
            {
                for(char c:textInput.toCharArray())
                {
                    if(Character.isDigit(c))
                    {
                        col++;
                    }

                }
                levelList.get(levelList.size() - 1).setCol(col);
            }

            //Getting digits and placing into map array
            for(char c:textInput.toCharArray())
            {
                if(Character.isDigit(c))
                {
                    int num = Character.getNumericValue(c);
                    levelList.get(levelList.size() - 1).addInttoMap(num);
                }
            }

        }
    }
}
