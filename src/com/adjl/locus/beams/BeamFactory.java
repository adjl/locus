package com.adjl.locus.beams;

import com.adjl.locus.LocusWorld;

import processing.core.PApplet;

import java.util.Random;

public class BeamFactory {

    private static final Random RANDOM = new Random();
    private static final int NUMBER_OF_BEAMS = 6;

    private BeamFactory() {}

    static int randomise(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static void setSketchAndWorld(PApplet sketch, LocusWorld world) {
        BeamImpl.setSketchAndWorld(sketch, world);
    }

    public static Beam createBeam() {
        switch (randomise(NUMBER_OF_BEAMS)) {
            case 0:
                return new UpBeam();
            case 1:
                return new DownBeam();
            case 2:
                return new LeftBeam();
            case 3:
                return new RightBeam();
            case 4:
                return new ForwardBeam();
            case 5:
                return new BackwardBeam();
            default:
                return null;
        }
    }
}
