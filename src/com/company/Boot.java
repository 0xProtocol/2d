package com.company;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Boot {

    public Boot() {
        Display.setTitle("2d");
        try {
            Display.setDisplayMode(new DisplayMode(600, 400));
            Display.create(); // Screen
        } catch (LWJGLException e) {
            e.printStackTrace();
        }



        while (!Display.isCloseRequested()) {
            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    public static void main(String[] args) {
        new Boot();
    }
}
