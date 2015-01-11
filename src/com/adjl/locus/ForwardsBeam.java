package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class ForwardsBeam extends Beam {

    ForwardsBeam(PApplet sketch, BeamType beamType, World world) {
        super(sketch, beamType);
        origin =
                new PVector((int) sketch.random(world.width()), (int) -sketch.random(world
                        .getHeight()), world.depth() - 1);
        position = new PVector(origin.x, origin.y, origin.z);
        velocity = new PVector(0, 0, -beamType.velocity());
        acceleration = new PVector(0, 0, -beamType.acceleration());
        rotationX = PConstants.HALF_PI;
        rotationZ = 0.0f;
    }

    @Override
    boolean isGone(World world) {
        return position.z + length * size < 0;
    }

    @Override
    void move() {
        moveBeam();
        length = PApplet.min((origin.z - position.z) / size + 1, BEAM_MAX_LENGTH);
    }
}
