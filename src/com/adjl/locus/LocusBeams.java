package com.adjl.locus;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PConstants;

class LocusBeams {

    private final PApplet mSketch;
    private final LocusWorld mWorld;
    private final ArrayList<Beam> mBeams;
    private final Random mRandom;

    LocusBeams(PApplet sketch, LocusWorld world) {
        mSketch = sketch;
        mWorld = world;
        mBeams = new ArrayList<>();
        mRandom = new Random();
        BeamImpl.setSketch(mSketch);
    }

    private Beam createRandomBeam() {
        switch (mRandom.nextInt(6)) {
            case 0: // Up
                return new UpBeam(getRandomBeamType(), mWorld);
            case 1: // Down
                return new DownBeam(getRandomBeamType(), mWorld);
            case 2: // Left
                return new LeftBeam(getRandomBeamType(), mWorld);
            case 3: // Right
                return new RightBeam(getRandomBeamType(), mWorld);
            case 4: // Forward
                return new ForwardBeam(getRandomBeamType(), mWorld);
            case 5: // Backward
                return new BackwardBeam(getRandomBeamType(), mWorld);
            default:
                return null;
        }
    }

    private BeamType getRandomBeamType() {
        BeamType[] types = BeamType.values();
        return types[mRandom.nextInt(types.length)];
    }

    void update() {
        mBeams.add(createRandomBeam());
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
}
