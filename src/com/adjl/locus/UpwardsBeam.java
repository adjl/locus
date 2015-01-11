package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PVector;

class UpwardsBeam extends Beam {

    UpwardsBeam(PApplet sketch, BeamType beamType, World world) {
        super(sketch, beamType);
        origin =
                new PVector((int) sketch.random(world.width()), 0, (int) sketch.random(world
                        .depth()));
        position = new PVector(origin.x, origin.y, origin.z);
        velocity = new PVector(0, -beamType.velocity(), 0);
        acceleration = new PVector(0, -beamType.acceleration(), 0);
        rotationX = 0.0f;
        rotationZ = 0.0f;
    }

    @Override
    boolean isGone(World world) {
        return position.y + length * size <= -world.getHeight();
    }

    @Override
    void move() {
        moveBeam();
        length = PApplet.min((origin.y - position.y) / size + 1, BEAM_MAX_LENGTH);
    }
}
