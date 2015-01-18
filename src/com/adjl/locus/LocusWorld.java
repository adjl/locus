package com.adjl.locus;

import com.adjl.virtualcamera.SimpleWorld;

import processing.core.PApplet;
import processing.core.PConstants;

public class LocusWorld extends SimpleWorld {

    private final PApplet mSketch;
    private final float mWidth;
    private final float mHeight;
    private final float mDepth;
    private final int mStroke;

    LocusWorld(PApplet sketch, float width, float height, float depth) {
        super(sketch, width, height, depth);
        mSketch = sketch;
        mWidth = width;
        mHeight = height;
        mDepth = depth;
        mStroke = mSketch.color(255, 255, 255);
    }

    public float getWidth() {
        return mWidth;
    }

    public float getDepth() {
        return mDepth;
    }

    @Override
    public void draw() {
        mSketch.noStroke();
        mSketch.pushMatrix();
        mSketch.translate(0.0f, -mHeight / 2.0f, 0.0f);
        mSketch.box(mWidth, mHeight, mDepth);
        mSketch.popMatrix();
        mSketch.stroke(mStroke);
        mSketch.pushMatrix();
        mSketch.rotateX(PConstants.HALF_PI);
        mSketch.rect(0.0f, 0.0f, mWidth, mDepth);
        mSketch.popMatrix();
    }
}
