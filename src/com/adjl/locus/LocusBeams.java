package com.adjl.locus;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

class LocusBeams {

    PApplet mSketch;
    LocusWorld mWorld;
    ArrayList<Beam> mBeams;

    LocusBeams(PApplet sketch, LocusWorld world) {
        mSketch = sketch;
        mWorld = world;
        mBeams = new ArrayList<>();
        BeamImpl.setSketch(mSketch);
    }

    void update() {
        mBeams.add(newBeam());
        for (int i = mBeams.size() - 1; i >= 0; i--) {
            mBeams.get(i).move();
            if (mBeams.get(i).isGone(mWorld)) {
                mBeams.remove(i);
            }
        }
    }

    void draw() {
        mSketch.pushMatrix();
        mSketch.translate(mWorld.getWidth() / 2.0f, 0.0f, mWorld.getDepth() / 2.0f);
        mSketch.rotateY(PConstants.PI);
        for (Beam beam : mBeams) {
            beam.draw();
        }
        mSketch.popMatrix();
    }

    Beam newBeam() {
        Beam beam = null;
        int direction = (int) mSketch.random(6); // Number of directions
        switch (direction) {
            case 0: // Up
                beam = new UpBeam(randomBeamType(), mWorld);
                break;
            case 1: // Down
                beam = new DownBeam(randomBeamType(), mWorld);
                break;
            case 2: // Left
                beam = new LeftBeam(randomBeamType(), mWorld);
                break;
            case 3: // Right
                beam = new RightBeam(randomBeamType(), mWorld);
                break;
            case 4: // Forward
                beam = new ForwardBeam(randomBeamType(), mWorld);
                break;
            case 5: // Backward
                beam = new BackwardBeam(randomBeamType(), mWorld);
                break;
            default:
                break;
        }
        return beam;
    }

    BeamType randomBeamType() {
        return BeamType.values()[(int) mSketch.random(BeamType.values().length)];
    }
}
