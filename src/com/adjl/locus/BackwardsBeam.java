package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class BackwardsBeam extends Beam {

    BackwardsBeam(PApplet sketch, BeamType beamType, World world) {
        super(sketch, beamType);
        origin =
                new PVector((int) sketch.random(world.width()), (int) -sketch.random(world
                        .getHeight()), 0);
        position = new PVector(origin.x, origin.y, origin.z);
        velocity = new PVector(0, 0, beamType.velocity());
        acceleration = new PVector(0, 0, beamType.acceleration());
        rotationX = PConstants.PI + PConstants.HALF_PI;
        rotationZ = 0.0f;
    }

    @Override
    boolean isGone(World world) {
        return position.z - length * size >= world.depth();
    }

    @Override
    void move() {
        moveBeam();
        length = PApplet.min((position.z - origin.z) / size + 1, BEAM_MAX_LENGTH);
    }
}
