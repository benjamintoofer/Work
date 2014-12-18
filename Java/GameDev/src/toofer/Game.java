package toofer;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import toofer.gfx.Screen;
import toofer.gfx.SpriteSheet;
import toofer.level.LevelManager;
import toofer.level.Tile;
import toofer.toofer.input.KeyDownHandler;
import toofer.toofer.input.KeyUpHandler;

import java.io.File;
import java.io.IOException;

/**
 * Created by benjamintoofer on 12/11/14.
 */
public class Game extends Canvas implements Runnable{

    private int width,height,scale;
    private int ticks = 0;
    private int frames = 0;

    private byte imageData[];
    private GraphicsContext gc;
    private PixelWriter pixelWriter;
    private  WritableImage writableImage;

    private SpriteSheet spriteSheet = new SpriteSheet("/sprite_sheet_2.png");
    private LevelManager levelManager;
    private Tile[] tileArray;

    private Screen screen;

    private Player myPlayer;

    private boolean running = false;


    public Game(int width,int height,int scale)
    {
        this.width = width;
        this.height = height;
        this.scale = scale;

        init();
    }

    private void init()
    {
        this.imageData = new byte[width * height];
        this.gc = this.getGraphicsContext2D();
        this. writableImage = new WritableImage(width,height);

        try {
            this.levelManager = new LevelManager(this.getClass().getResource("/Levels.txt"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        myPlayer = new Player(width/2,height/2,spriteSheet);
        tileArray = new Tile[]{new Tile(Tile.GRASS,spriteSheet),new Tile(Tile.STONE,spriteSheet)};
        screen = new Screen(levelManager,tileArray,myPlayer);


        //Add even handlers
        this.addEventHandler(KeyEvent.KEY_PRESSED,new KeyDownHandler(myPlayer));
        this.addEventHandler(KeyEvent.KEY_RELEASED,new KeyUpHandler(myPlayer));

        this.setWidth(width * scale);
        this.setHeight(height * scale);
    }

    /*
    Thread methods
     */
    @Override
    public void run()
    {
        double lastTime = System.currentTimeMillis();
        long lastTimer = System.nanoTime();
        double nsPerTick = 1000000000D/60D;

        try{
            while(running)
            {
                if((System.nanoTime() - lastTimer)/nsPerTick >= 1)
                {
                    ticks++;
                    tick();
                    lastTimer = System.nanoTime();
                }


                Platform.runLater(new Runnable() {
                    @Override
                    public void run()
                    {
                        frames++;
                        render();
                    }
                });

                if(System.currentTimeMillis() - lastTime >= 1000)
                {
                    System.out.println("frames rendered: "+frames+", ticks: "+ticks );
                    lastTime = System.currentTimeMillis();
                    frames = 0;
                    ticks = 0;
                }

                Thread.sleep(4);
            }
        }catch(InterruptedException ex){}


    }

    public synchronized void start()
    {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop()
    {
        running = false;
    }

    /*
    Update methods
     */
    public void tick()
    {
        myPlayer.tick();
    }

    public void render()
    {
        gc.clearRect(0,0,width * scale,height * scale);
        screen.render(gc);

    }

    private void createImageData()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++ )
            {
                int r = (55 & 0x00ff0000);
                int g = (55 & 0x0000ff00);
                int b = (55 & 0x000000ff);
                imageData[i+j] = (byte)(r + g + b);
            }
        }
    }

    private void drawImageData()
    {
        int r = 0;
        int g = 0;
        int b = 0;
        Color k = Color.rgb(r,g,b);

        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {

                pixelWriter = writableImage.getPixelWriter();
                pixelWriter.setColor(j,i,k);
                if(r >= 255 || b >= 255 || g >= 255)
                {
                   r =g = b = 0;
                }else{
                    r++;g++;
                }

                k = Color.rgb(r,g,b);

            }
        }
        gc.drawImage(writableImage,0,0,width * scale,height * scale);
    }
}

