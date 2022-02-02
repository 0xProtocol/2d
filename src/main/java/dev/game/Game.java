package dev.game;

import dev.display.Display;
import dev.gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;

public class Game implements Runnable {

    private Display display;
    public int width, height;
    public String title;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    private BufferedImage img;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        //display = new dev.display.Display(title, width, height);
    }

    @Override
    public void run() {
        init();

        //loop
        while (running) {
            tick();
            render();
        }
        stop();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics(); // draw things to the canvas
        g.clearRect(0,0,width,height);

        //g.drawRect(10,50,50,70);
        //g.setColor(Color.RED);
        //g.fillRect(10,50,50,70);

        g.drawImage(img,20,20,null);
        bs.show();
        g.dispose();
    }

    private void init() {
        display = new Display(title, width, height);
        img = ImageLoader.loadImage("/textures/texture_one.jpg");
    }

    private void tick() {

    }

    public synchronized void start() {
        if (running)
            return; // do nothing
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
