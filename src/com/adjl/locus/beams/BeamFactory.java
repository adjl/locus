package com.adjl.locus.beams;

import com.adjl.locus.LocusWorld;

import processing.core.PApplet;

import java.util.Random;

public class BeamFactory {

    private static final Random RANDOM = new Random();
    private static final int NUMBER_OF_BEAMS = 6;

    private BeamFactory() {}

    private static BeamType getBeamType() {
        BeamType[] types = BeamType.values();
        return types[RANDOM.nextInt(types.length)];
    }

    public static void setSketchAndWorld(PApplet sketch, LocusWorld world) {
        BeamImpl.setSketchAndWorld(sketch, world);
    }

    public static Beam createBeam() {
        switch (RANDOM.nextInt(NUMBER_OF_BEAMS)) {
            case 0:
                return new UpBeam(getBeamType());
            case 1:
                return new DownBeam(getBeamType());
            case 2:
                return new LeftBeam(getBeamType());
            case 3:
                return new RightBeam(getBeamType());
            case 4:
                return new ForwardBeam(getBeamType());
            case 5:
                return new BackwardBeam(getBeamType());
            default:
                return null;
        }
    }
}
