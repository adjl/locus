package com.adjl.locus;

import java.util.Random;

import processing.core.PApplet;

class BeamFactory {

    private static final Random RANDOM = new Random();
    private static final int NUM_BEAMS = 6;

    private BeamFactory() {
    }

    private static BeamType getBeamType() {
        BeamType[] types = BeamType.values();
        return types[RANDOM.nextInt(types.length)];
    }

    static void setSketch(PApplet sketch) {
        BeamImpl.setSketch(sketch);
    }

    static Beam createBeam(LocusWorld world) {
        switch (RANDOM.nextInt(NUM_BEAMS)) {
            case 0:
                return new UpBeam(getBeamType(), world);
            case 1:
                return new DownBeam(getBeamType(), world);
            case 2:
                return new LeftBeam(getBeamType(), world);
            case 3:
                return new RightBeam(getBeamType(), world);
            case 4:
                return new ForwardBeam(getBeamType(), world);
            case 5:
                return new BackwardBeam(getBeamType(), world);
            default:
                return null;
        }
    }
}
