package toofer.gfx;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import toofer.GameConstants;

/**
 * Created by benjamintoofer on 12/12/14.
 */
public class SpriteSheet {

    private String path;
    private Image image;
    private int width,height;

    private PixelReader pixelReader;
    private WritableImage newImage;

    //public int pixels[];

    public SpriteSheet(String path)
    {
        image = new Image(path);

        if(image == null)
        {
            return;
        }
        System.out.println("image heght "+image.getHeight());
        this.path = path;
        this.width = (int)image.getWidth();
        this.height = (int)image.getHeight();
        //this.pixels = new int[width * height];

        pixelReader = image.getPixelReader();

        /*for( int readY = 0; readY < height; readY++)
        {
            for( int readX = 0; readX < width; readX++)
            {
                pixels[readY * width + readX] = pixelReader.getArgb(readX,readY) & 0xff;
                System.out.println(pixels[readY * width + readX]);
            }
        }*/
    }

    public Image cropImage(int col, int row, int width, int height)
    {
        int spriteWidth = GameConstants.SPRITE_WIDTH;

        return newImage = new WritableImage(pixelReader,col * spriteWidth,row * spriteWidth,width,height);
    }
}
