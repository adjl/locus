package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class RightwardsBeam extends Beam {

    RightwardsBeam(PApplet sketch, BeamType beamType, World world) {
        super(sketch, beamType);
        origin =
                new PVector(0, (int) -sketch.random(world.getHeight()), (int) sketch.random(world
                        .depth()));
        position = new PVector(origin.x, origin.y, origin.z);
        velocity = new PVector(beamType.velocity(), 0, 0);
        acceleration = new PVector(beamType.acceleration(), 0, 0);
        rotationX = 0.0f;
        rotationZ = PConstants.HALF_PI;
    }

    @Override
    boolean isGone(World world) {
        return position.x - length * size >= world.width();
    }

    @Override
    void move() {
        moveBeam();
        length = PApplet.min((position.x - origin.x) / size + 1, BEAM_MAX_LENGTH);
    }
}
