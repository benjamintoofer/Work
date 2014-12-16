package toofer.level;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import toofer.GameConstants;
import toofer.gfx.SpriteSheet;

/**
 * Created by benjamintoofer on 12/14/14.
 */
public class Tile {

    /*
    Tile Constants
     */
    public static final int GRASS = 0;
    public static final int STONE = 1;


    public Image tileImage;
    private int tileID;
    private PixelReader pixelReader;
    private WritableImage writableImage;

    public Tile(int tileType,SpriteSheet ss)
    {
        tileID = tileType;

        int spriteWidth = GameConstants.SPRITE_WIDTH;

        tileImage = ss.cropImage(tileID,0,spriteWidth,spriteWidth);
    }


}
