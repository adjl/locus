package com.adjl.locus.beams;

import com.adjl.locus.LocusWorld;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

abstract class BeamImpl {

    static final float MAX_LENGTH = 25.0f;

    private static PApplet sSketch;
    private static LocusWorld sWorld;
    private static int[] sColours;

    private final float mTerminalVelocity;
    private final float mSize;
    private final int mColour;

    BeamImpl(BeamType type) {
        mTerminalVelocity = type.getTerminalVelocity();
        mSize = type.getSize();
        mColour = sColours[BeamFactory.randomise(sColours.length)];
    }

    static void setSketchAndWorld(PApplet sketch, LocusWorld world) {
        sSketch = sketch;
        sWorld = world;
        sColours = new int[] { sSketch.color(255, 0, 0), sSketch.color(0, 255, 0),
                sSketch.color(0, 0, 255), sSketch.color(0, 255, 255), sSketch.color(255, 0, 255),
                sSketch.color(255, 255, 0) };
    }

    static LocusWorld getWorld() {
        return sWorld;
    }

    static int nextInt(float bound) {
        return (int) sSketch.random(bound);
    }

    float getSize() {
        return mSize;
    }

    void move(PVector position, PVector velocity, PVector acceleration) {
        velocity.add(acceleration);
        velocity.limit(mTerminalVelocity);
        position.add(velocity);
    }

    void draw(PVector position, float length, float rotationX, float rotationZ) {
        float opacity = PApplet.map(MAX_LENGTH - length, 0.0f, MAX_LENGTH, 0.0f, 255.0f);
        sSketch.pushMatrix();
        sSketch.translate(position.x, position.y, position.z);
        sSketch.rotateX(rotationX);
        sSketch.rotateZ(rotationZ);
        sSketch.scale(1.0f, mSize, 1.0f);
        sSketch.beginShape(PConstants.LINES);
        sSketch.stroke(mColour);
        sSketch.vertex(0.0f, 0.0f, 0.0f);
        sSketch.stroke(mColour, opacity);
        sSketch.vertex(0.0f, length, 0.0f);
        sSketch.endShape(PConstants.CLOSE);
        sSketch.popMatrix();
    }
}
