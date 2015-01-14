package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

abstract class BeamImpl {

    private final PApplet mSketch;
    private final float mTerminalVelocity;
    private final float mSize;
    private final int mColour;

    BeamImpl(PApplet sketch, BeamType type) {
        mSketch = sketch;
        mTerminalVelocity = type.getTerminalVelocity();
        mSize = type.getSize();
        int[] COLOURS = { sketch.color(255, 0, 0), sketch.color(0, 255, 0),
            sketch.color(0, 0, 255), sketch.color(0, 255, 255), sketch.color(255, 0, 255),
            sketch.color(255, 255, 0) };
        mColour = COLOURS[nextInt(COLOURS.length)];
    }

    float getSize() {
        return mSize;
    }

    int nextInt(float bound) {
        return (int) mSketch.random(bound);
    }

    void move(PVector position, PVector velocity, PVector acceleration) {
        velocity.add(acceleration);
        velocity.limit(mTerminalVelocity);
        position.add(velocity);
    }

    void draw(PVector position, float length, float rotationX, float rotationZ) {
        float opacity = PApplet.map(Beam.MAX_LENGTH - length, 0.0f, Beam.MAX_LENGTH, 0.0f, 255.0f);
        mSketch.strokeWeight(1.0f); // TODO(adjl): Use default stroke weight?
        mSketch.pushMatrix();
        mSketch.translate(position.x, position.y, position.z);
        mSketch.rotateX(rotationX);
        mSketch.rotateZ(rotationZ);
        mSketch.scale(1.0f, mSize, 1.0f);
        mSketch.beginShape(PConstants.LINES);
        mSketch.stroke(mColour);
        mSketch.vertex(0.0f, 0.0f, 0.0f);
        mSketch.stroke(mColour, opacity);
        mSketch.vertex(0.0f, length, 0.0f);
        mSketch.endShape(PConstants.CLOSE);
        mSketch.popMatrix();
    }
}
