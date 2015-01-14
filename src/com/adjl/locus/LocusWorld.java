package com.adjl.locus;

import com.adjl.virtualcamera.VirtualWorld;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class LocusWorld implements VirtualWorld {

    private final PApplet mSketch;
    private final float mWidth;
    private final float mHeight;
    private final float mDepth;
    private final int mStroke;

    LocusWorld(PApplet sketch) {
        mSketch = sketch;
        mWidth = 2000.0f;
        mHeight = 2000.0f;
        mDepth = 2000.0f;
        mStroke = mSketch.color(255, 255, 255);
    }

    float getWidth() {
        return mWidth;
    }

    float getDepth() {
        return mDepth;
    }

    @Override
    public float getHeight() {
        return mHeight;
    }

    @Override
    public boolean isWithin(PVector position) {
        return (position.x >= -mWidth / 2.0f) && (position.x < mWidth / 2.0f)
                && (position.y >= 0.0f) && (position.y < mHeight) && (position.z >= -mDepth / 2.0f)
                && (position.z < mDepth / 2.0f);
    }

    @Override
    public void draw() {
        mSketch.noStroke();
        mSketch.pushMatrix();
        mSketch.translate(0.0f, -mHeight / 2.0f, 0.0f);
        mSketch.box(mWidth, mHeight, mDepth);
        mSketch.popMatrix(); // TODO(adjl): Nest push/pop matrices?
        mSketch.stroke(mStroke);
        mSketch.pushMatrix();
        mSketch.rotateX(PConstants.HALF_PI);
        mSketch.rect(0, 0, mWidth, mDepth);
        mSketch.popMatrix();
    }
}
