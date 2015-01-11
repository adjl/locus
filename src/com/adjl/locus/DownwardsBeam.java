package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class DownwardsBeam extends Beam {

    DownwardsBeam(PApplet sketch, BeamType beamType, World world) {
        super(sketch, beamType);
        origin =
                new PVector((int) sketch.random(world.width()), 1 - world.getHeight(),
                        (int) sketch.random(world.depth()));
        position = new PVector(origin.x, origin.y, origin.z);
        velocity = new PVector(0, beamType.velocity(), 0);
        acceleration = new PVector(0, beamType.acceleration(), 0);
        rotationX = 0.0f;
        rotationZ = PConstants.PI;
    }

    @Override
    boolean isGone(World world) {
        return position.y - length * size > 0;
    }

    @Override
    void move() {
        moveBeam();
        length = PApplet.min((position.y - origin.y) / size + 1, BEAM_MAX_LENGTH);
    }
}
