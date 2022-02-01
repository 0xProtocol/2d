import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private Display display;
    public int width, height;
    public String title;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        //display = new Display(title, width, height);
        //5
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
        g.fillRect(0,0,width,height);
        bs.show();
        g.dispose();
    }

    private void init() {
        display = new Display(title, width, height);
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
