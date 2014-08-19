package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ListIterator;
import java.util.Random;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

/**
 * Created by benjamintoofer on 8/16/14.
 */
public class RenderSystem extends Canvas {

    public boolean emit,pause;

    private BufferedImage particleImage;
    private int[] particleRaster;
    private final double WIDTH,HEIGHT;
    private final int COLOR_THRESHOLD = 0xff9f1604;

    public ArrayList<Gravitron>gravitronList;
    private ArrayList<Particle> particleList;
    private Random rand;

    public double oldX,oldY;
    private long lastTime;
    private int frameCounter;
    private int frameRate = 0;



    public RenderSystem(double width,double height)
    {
        super(width,height);
        WIDTH = width;
        HEIGHT = height;
        pause = false;
        emit = false;
        rand = new Random();
        //Add event listeners
        this.setOnMouseDragged(new MouseDraggedHandler(this));
        this.setOnMousePressed(new MousePressedHandler(this));
        this.setOnMouseReleased(new MouseReleasedHandler(this));
        this.setOnKeyPressed(new KeyPressedHandler(this));
        //this.requestFocus();


        particleImage = new BufferedImage((int)WIDTH,(int)HEIGHT,BufferedImage.TYPE_INT_ARGB);
        particleRaster = ((DataBufferInt)particleImage.getRaster().getDataBuffer()).getData();

        particleList = new ArrayList<Particle>();
        gravitronList = new ArrayList<Gravitron>();

    }

    public void tick()
    {
       //System.out.println(oldX);
        if(emit)
        {
            spawnParticles(8);
        }

        if(!pause)
        {
            updateParticles();
        }
    }

    public void render(GraphicsContext gc)
    {
        //this.requestFocus();

        //System.out.println("rendering");
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());

        /*
        Convert buffered image to Javafx image
         */
        WritableImage wi = new WritableImage(particleImage.getWidth(), particleImage.getHeight());
        Image im = SwingFXUtils.toFXImage(particleImage, wi);
        gc.drawImage(im,0,0,WIDTH,HEIGHT);

        gc.setFont(new Font(16));
        if((System.currentTimeMillis() - lastTime) >= 1000)
        {
            frameRate = (int)(frameCounter/((System.currentTimeMillis() - lastTime)/1000));
            lastTime = System.currentTimeMillis();
            frameCounter = 0;
        }else{
            frameCounter++;
        }
        gc.setFill(Color.WHITE);
        gc.fillText("Framerate:" + frameRate, 5, 25);
        gc.fillText("Num of Particles:"+particleList.size(),5,45);



    }

    private void spawnParticles(int numParticles)
    {
        double xPos,yPos,xVel,yVel,Vel,angle;

        for(int x_i = 0; x_i < numParticles; x_i++)
        {
            for(int y_i = 0; y_i < numParticles; y_i++)
            {
                Particle p = new Particle();

                xPos = oldX + x_i - (numParticles/2);
                yPos = oldY + y_i - (numParticles/2);

                angle = rand.nextDouble() * Math.PI * 2;
                Vel = rand.nextDouble();

                xVel = Vel * Math.cos(angle);
                yVel = Vel * Math.sin(angle);

                p.setxPos(xPos);
                p.setyPos(yPos);
                p.setxVel(xVel);
                p.setyVel(yVel);

                particleList.add(p);
            }
        }

    }

    private int additiveColor(int c1,int c2)
    {
        int red = (c1 & 0x00ff0000) + (c2 & 0x00ff0000);
        int grn = (c1 & 0x0000ff00) + (c2 & 0x0000ff00);
        int blu = (c1 & 0x000000ff) + (c2 & 0x000000ff);

        if(red > 0x00ff0000){red = 0x00ff0000;}
        if(grn > 0x0000ff00){grn = 0x0000ff00;}
        if(blu > 0x000000ff){grn = 0x000000ff;}

        return (0xff000000 + red + grn + blu);
    }
    private void updateParticles()
    {
        for(int I = 0; I < particleRaster.length; I++){ // clear image of particles
            particleRaster[I] = 0;
        }

        ListIterator<Particle>particleListIterator = particleList.listIterator();

        while(particleListIterator.hasNext())
        {
            Particle p = particleListIterator.next();
            if(p.getAge() >= 1000)
            {
                //particleListIterator.remove();
            }
        }

        for(Particle p : particleList)
        {

            double x = p.getxPos() + p.getxVel();
            double y = p.getyPos() + p.getyVel();

            p.setxPos(x);
            p.setyPos(y);

            if(Math.abs(p.getxVel()) < .0001)
            {
                p.setxVel(0);
            }else{
                p.setxVel(p.getxVel() * .97);
            }
            if(Math.abs(p.getyVel()) < .0001)
            {
                p.setyVel(0);
            }else{
                p.setyVel(p.getyVel() * .97);
            }


            int age = p.getAge();
            age += 10;

           p.setAge(age);
            //particleRaster[(int)((x) + (y) * WIDTH)] = additiveColor(particleRaster[(int)((x) + (y) * WIDTH)],COLOR_THRESHOLD);
            //particleRaster[(int)((x) + (y) * WIDTH)] = 0xffff0000;
            for(int x_I = -2; x_I < 2;x_I++)
            {
                for(int y_I = -2; y_I < 2;y_I++)
                {
                    particleRaster[(int)(x+x_I + WIDTH * (int)(y+y_I))] = additiveColor(particleRaster[(int)(x+x_I + WIDTH * (int)(y+y_I))],COLOR_THRESHOLD);
                }
            }



        }
    }
}

class MouseDraggedHandler implements EventHandler<MouseEvent>{

    RenderSystem ren;

    public MouseDraggedHandler(RenderSystem ren)
    {
        this.ren = ren;
    }
    @Override
    public void handle(MouseEvent e)
    {

        ren.oldX = e.getX();
        ren.oldY = e.getY();
    }
}

class MousePressedHandler implements EventHandler<MouseEvent>{

    RenderSystem ren;

    public MousePressedHandler(RenderSystem ren)
    {
        this.ren = ren;
    }
    @Override
    public void handle(MouseEvent e)
    {
        if(e.isPrimaryButtonDown())
        {
            System.out.println("Mouse pressed");
            ren.emit = true;

            ren.oldX = e.getX();
            ren.oldY = e.getY();
        }else if(e.isSecondaryButtonDown())
        {
            System.out.println("Gravity well place");
            Gravitron g = new Gravitron();
            g.setxPos(e.getX());
            g.setyPos(e.getY());

            ren.gravitronList.add(g);

        }

    }
}

class MouseReleasedHandler implements EventHandler<MouseEvent>{

    RenderSystem ren;

    public MouseReleasedHandler(RenderSystem ren)
    {
        this.ren = ren;
    }

    @Override
    public void handle(MouseEvent e)
    {
        System.out.println("Mouse released");
        ren.emit = false;

        /*if(e.isSecondaryButtonDown())
        {
            ren.gravitronList.remove(0);
        }*/

    }
}

class KeyPressedHandler implements EventHandler<KeyEvent>{

    RenderSystem ren;

    public KeyPressedHandler(RenderSystem ren)
    {
        this.ren = ren;
    }
    @Override
    public void handle(KeyEvent e)
    {
        System.out.println("key pressed");
      if(e.getCode() == KeyCode.SPACE)//keyCode for spacebar
      {
          System.out.println("space bar hit");
      }
    }

}