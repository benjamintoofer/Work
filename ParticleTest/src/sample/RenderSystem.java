package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.awt.*;
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
    //private WritableImage particleImage;
    private byte imageData[];
    private int[] particleRaster;
    private final double WIDTH,HEIGHT;
    private final int COLOR_THRESHOLD = 0xff9f1604;
    private final int PARTICLE_WIDTH = 1;
    public final double SCALE = 2;

    public ArrayList<Gravitron>gravitronList;
    private ArrayList<Particle> particleList;
    private Random rand;

    public double oldX,oldY,currentMouseX,currentMouseY,preMouseX,preMouseY;
    private long lastTime;
    private int frameCounter;
    private int frameRate = 0;

    public Gravitron mouseGravitron;


    public RenderSystem(double width,double height)
    {
        super(width,height);
        WIDTH = width/SCALE;
        HEIGHT = height/SCALE;
        pause = false;
        emit = false;
        rand = new Random();
        //Add event listeners
        this.setOnMouseDragged(new MouseDraggedHandler(this));
        this.setOnMousePressed(new MousePressedHandler(this));
        this.setOnMouseReleased(new MouseReleasedHandler(this));
        this.setOnMouseMoved(new MouseMovedHandler(this));
        this.setOnKeyPressed(new KeyPressedHandler(this));
        this.setOnKeyReleased(new KeyReleasedHandler(this));
        //this.requestFocus();


        particleImage = new BufferedImage((int)WIDTH,(int)HEIGHT,BufferedImage.TYPE_INT_ARGB);
        //particleImage = new WritableImage((int)WIDTH,(int)HEIGHT);
        particleRaster = ((DataBufferInt)particleImage.getRaster().getDataBuffer()).getData();


        particleList = new ArrayList<Particle>();
        gravitronList = new ArrayList<Gravitron>();

        mouseGravitron = new Gravitron();
        mouseGravitron.setxPull(50);
        mouseGravitron.setyPull(50);
    }

    public void tick()
    {
       //System.out.println(oldX);
        if(emit)
        {
            spawnParticles(16);
        }

        if(!pause)
        {
            updateParticles();
        }
    }

    public void render(GraphicsContext gc)
    {
        this.requestFocus();


        //System.out.println("rendering");
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());

        /*
        Convert buffered image to Javafx image
         */
        WritableImage wi = new WritableImage(particleImage.getWidth(), particleImage.getHeight());
        Image im = SwingFXUtils.toFXImage(particleImage, wi);

        //WritableImage im = particleImage;
        //PixelWriter wr = im .getPixelWriter();
        gc.drawImage(im,0,0,WIDTH*SCALE,HEIGHT*SCALE);

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

    private final static float InvSqrt(float x) {
        return Float.intBitsToFloat(0x5f3759d5 - (Float.floatToIntBits(x) >> 1));
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
            if(p.getAge() >= 10000)
            {
                particleListIterator.remove();
            }
        }

        for(Particle p : particleList)
        {

            double x = p.getxPos() + p.getxVel();
            double y = p.getyPos() + p.getyVel();

            p.setxPos(x);
            p.setyPos(y);

            double xVel,yVel;
            int age = p.getAge();

            if(mouseGravitron.isGravityOn())
            {

                xVel = p.getxVel();
                yVel = p.getyVel();

                double dx =  p.getxPos()-mouseGravitron.getxPos();
                double dy = p.getyPos()-mouseGravitron.getyPos();
                double hypot = Math.sqrt(dx*dx + dy*dy);



                if(Math.sqrt(xVel * xVel + yVel * yVel) < 1)
                {
                    age+= 100;
                }

                xVel -= mouseGravitron.getxPull() * dx * (1/(hypot));
                yVel -= mouseGravitron.getyPull() * dy * (1/(hypot));

                xVel *= .98;
                yVel *= .98;
                //System.out.println(xVel);
            }else{
                xVel = p.getxVel() * .97;
                yVel = p.getyVel() * .97;
            }

            p.setxVel(xVel);
            p.setyVel(yVel);



            age += 10;

           p.setAge(age);
            //particleRaster[(int)((x) + (y) * WIDTH)] = additiveColor(particleRaster[(int)((x) + (y) * WIDTH)],COLOR_THRESHOLD);
            //particleRaster[(int)((x) + (y) * WIDTH)] = 0xffff0000;
            for(int x_I = -PARTICLE_WIDTH; x_I < PARTICLE_WIDTH;x_I++)
            {
                for(int y_I = -PARTICLE_WIDTH; y_I < PARTICLE_WIDTH;y_I++)
                {
                    if(p.getxPos() - PARTICLE_WIDTH > 0 && p.getxPos() + PARTICLE_WIDTH < WIDTH && p.getyPos() - PARTICLE_WIDTH > 0 && p.getyPos() + PARTICLE_WIDTH < HEIGHT)
                    {
                        particleRaster[(int)(x+x_I + WIDTH * (int)(y+y_I))] = additiveColor(particleRaster[(int)(x+x_I + WIDTH * (int)(y+y_I))],COLOR_THRESHOLD);
                    }else{
                        p.setAge(age + 200);
                    }

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

        ren.oldX = e.getX()/ren.SCALE;
        ren.oldY = e.getY()/ren.SCALE;
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

            ren.oldX = e.getX()/ren.SCALE;
            ren.oldY = e.getY()/ren.SCALE;
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

class MouseMovedHandler implements EventHandler<MouseEvent>{

    RenderSystem ren;

    public MouseMovedHandler(RenderSystem ren)
    {
        this.ren = ren;
    }

    @Override
    public void handle(MouseEvent e)
    {
        double tempX = ren.currentMouseX;
        double tempY = ren.currentMouseY;

        ren.currentMouseX = e.getX()/ren.SCALE;
        ren.currentMouseY = e.getY()/ren.SCALE;

        ren.preMouseX = tempX;
        ren.preMouseY = tempY;
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
      if(e.getCode() == KeyCode.SPACE)
      {
          ren.mouseGravitron.setxPos(ren.currentMouseX);
          ren.mouseGravitron.setyPos(ren.currentMouseY);
          ren.mouseGravitron.setGravityOn(true);

          //System.out.println(ren.mouseGravitron.getxPos()+" "+ren.mouseGravitron.getyPos());
      }
    }

}

class KeyReleasedHandler implements EventHandler<KeyEvent>{

    RenderSystem ren;

    public KeyReleasedHandler(RenderSystem ren)
    {
        this.ren = ren;
    }
    @Override
    public void handle(KeyEvent e)
    {
        if(e.getCode() == KeyCode.SPACE)
        {
            System.out.println("space bar released");
            ren.mouseGravitron.setGravityOn(false);
        }
    }

}