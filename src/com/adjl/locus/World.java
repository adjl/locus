package com.adjl.locus;

import com.adjl.virtualcamera.VirtualWorld;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class World implements VirtualWorld {

    PApplet mSketch;
    float mWidth;
    float mHeight;
    float mDepth;

    World(PApplet sketch, float width, float height, float depth) {
        mSketch = sketch;
        mWidth = width;
        mHeight = height;
        mDepth = depth;
    }

    float width() {
        return mWidth;
    }

    float depth() {
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
        mSketch.rectMode(PConstants.CENTER);
        mSketch.stroke(mSketch.color(255, 255, 255));
        mSketch.strokeWeight(2);
        mSketch.noFill();
        mSketch.pushMatrix();
        mSketch.rotateX(PConstants.HALF_PI);
        mSketch.rect(0, 0, mWidth, mDepth);
        mSketch.popMatrix();
    }
}
