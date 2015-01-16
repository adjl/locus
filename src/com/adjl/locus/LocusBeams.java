package com.adjl.locus;

import com.adjl.locus.beams.Beam;
import com.adjl.locus.beams.BeamFactory;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

class LocusBeams {

    private final PApplet mSketch;
    private final LocusWorld mWorld;
    private final ArrayList<Beam> mBeams;

    LocusBeams(PApplet sketch, LocusWorld world) {
        mSketch = sketch;
        mWorld = world;
        mBeams = new ArrayList<>();
        BeamFactory.setSketchAndWorld(mSketch, mWorld);
    }

    void update() {
        mBeams.add(BeamFactory.createBeam());
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
