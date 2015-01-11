package com.adjl.locus;

import com.adjl.virtualcamera.VirtualCamera;

import processing.core.PApplet;

/**
 * @author Helena Josol
 */
public class Locus extends PApplet {

    final float WORLD_WIDTH = 2000.0f;
    final float WORLD_HEIGHT = 2000.0f;
    final float WORLD_DEPTH = 2000.0f;
    final float CAMERA_HEIGHT = 50.0f;
    final float CAMERA_SPEED = 3.0f;

    World world;
    LocusBeams beams;
    VirtualCamera camera;
    boolean running;

    @Override
    public void setup() {
        size(displayWidth, displayHeight, P3D);
        noCursor();
        world = new World(this, WORLD_WIDTH, WORLD_HEIGHT, WORLD_DEPTH);
        beams = new LocusBeams(this, world);
        camera = new VirtualCamera(this, world, CAMERA_HEIGHT, CAMERA_SPEED);
        running = true;
    }

    @Override
    public void draw() {
        background(color(0, 0, 0));
        world.draw();
        if (running) {
            beams.update();
        }
        beams.draw();
        camera.set();
    }

    @Override
    public void keyPressed() {
        camera.move(key);
    }

    public static void main(String _args[]) {
        PApplet.main(new String[] { "--present", com.adjl.locus.Locus.class.getName() });
    }
}
