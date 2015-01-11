package com.adjl.locus;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.data.IntList;

class LocusBeams {

    final int BEAM_CHANCE_OF_FIRING = 1; // 1 in 1 (always)
    final int COLOURS_COUNT = 6; // Number of colours

    PApplet mSketch;
    World mWorld;
    ArrayList<Beam> beams;
    IntList colours;

    LocusBeams(PApplet sketch, World world) {
        mSketch = sketch;
        mWorld = world;
        beams = new ArrayList<>();
        colours = new IntList();
        for (int i = 0; i < COLOURS_COUNT; i++) {
            colours.append(i);
        }
    }

    void update() {
        if ((int) mSketch.random(BEAM_CHANCE_OF_FIRING) == 0) {
            beams.add(newBeam());
        }
        for (int i = beams.size() - 1; i >= 0; i--) {
            beams.get(i).move();
            if (beams.get(i).isGone(mWorld)) {
                beams.remove(i);
            }
        }
    }

    void draw() {
        mSketch.pushMatrix();
        mSketch.translate(mWorld.width() / 2, 0, mWorld.depth() / 2);
        mSketch.rotateY(PConstants.PI);
        for (Beam beam : beams) {
            beam.draw();
        }
        mSketch.popMatrix();
    }

    Beam newBeam() {
        Beam beam = null;
        int direction = (int) mSketch.random(6); // Number of directions
        switch (direction) {
            case 0: // Up
                beam = new UpwardsBeam(mSketch, randomBeamType(), mWorld);
                break;
            case 1: // Down
                beam = new DownwardsBeam(mSketch, randomBeamType(), mWorld);
                break;
            case 2: // Left
                beam = new LeftwardsBeam(mSketch, randomBeamType(), mWorld);
                break;
            case 3: // Right
                beam = new RightwardsBeam(mSketch, randomBeamType(), mWorld);
                break;
            case 4: // Forward
                beam = new ForwardsBeam(mSketch, randomBeamType(), mWorld);
                break;
            case 5: // Backward
                beam = new BackwardsBeam(mSketch, randomBeamType(), mWorld);
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
