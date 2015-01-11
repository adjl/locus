package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

abstract class Beam {

    final float BEAM_MAX_LENGTH = 25.0f;

    PApplet mSketch;
    PVector origin, position, velocity, acceleration;
    float terminalVelocity, rotationX, rotationZ, length, size;
    int colour;

    Beam(PApplet sketch, BeamType beamType) {
        mSketch = sketch;
        terminalVelocity = beamType.terminalVelocity();
        size = beamType.size();
        int[] COLOURS =
                { sketch.color(255, 0, 0), sketch.color(0, 255, 0), sketch.color(0, 0, 255),
                    sketch.color(0, 255, 255), sketch.color(255, 0, 255), sketch.color(255, 255, 0) };
        colour = COLOURS[(int) sketch.random(COLOURS.length)];
    }

    void moveBeam() {
        velocity.add(acceleration);
        velocity.limit(terminalVelocity);
        position.add(velocity);
    }

    void draw() {
        float opacity = PApplet.map(BEAM_MAX_LENGTH - length, 0, BEAM_MAX_LENGTH, 0, 255);
        mSketch.strokeWeight(1);
        mSketch.pushMatrix();
        mSketch.translate(position.x, position.y, position.z);
        mSketch.rotateX(rotationX);
        mSketch.rotateZ(rotationZ);
        mSketch.scale(1, size, 1);
        mSketch.beginShape(PConstants.LINES);
        mSketch.stroke(colour);
        mSketch.vertex(0, 0, 0);
        mSketch.stroke(colour, opacity);
        mSketch.vertex(0, length, 0);
        mSketch.endShape(PConstants.CLOSE);
        mSketch.popMatrix();
    }

    abstract boolean isGone(World world);

    abstract void move();
}
