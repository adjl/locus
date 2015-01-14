package com.adjl.locus;

import com.adjl.virtualcamera.VirtualCamera;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author Helena Josol
 */
public class Locus extends PApplet {

    private final int mBackground;
    private final int mFill;
    private final int mStrokeWeight;

    private LocusWorld mWorld;
    private LocusBeams mBeams;
    private VirtualCamera mCamera;
    private boolean mRunning;

    /**
     * TODO(adjl): Write Javadoc.
     */
    public Locus() {
        mBackground = color(0, 0, 0);
        mFill = color(0, 0, 0);
        mStrokeWeight = 2;
    }

    @Override
    public void setup() {
        size(displayWidth, displayHeight, PConstants.P3D);
        rectMode(PConstants.CENTER);
        noCursor();
        fill(mFill);
        strokeWeight(mStrokeWeight);
        mWorld = new LocusWorld(this);
        mBeams = new LocusBeams(this, mWorld);
        mCamera = new VirtualCamera(this, mWorld, 50.0f, 3.0f);
        mRunning = true;
    }

    @Override
    public void draw() {
        background(mBackground);
        mWorld.draw();
        if (mRunning) {
            mBeams.update();
        }
        mBeams.draw();
        mCamera.set();
    }

    @Override
    public void keyPressed() {
        mCamera.move(key);
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { "--present", com.adjl.locus.Locus.class.getName() });
    }
}
