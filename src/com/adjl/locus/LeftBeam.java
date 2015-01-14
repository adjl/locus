package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class LeftBeam extends BeamImpl implements Beam {

    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mOriginX;

    private float mLength;

    LeftBeam(PApplet sketch, BeamType type, LocusWorld world) {
        super(sketch, type);
        mPosition = new PVector(world.getWidth() - 1.0f, -nextInt(world.getHeight()),
                nextInt(world.getDepth()));
        mVelocity = new PVector(-type.getVelocity(), 0.0f, 0.0f);
        mAcceleration = new PVector(-type.getAcceleration(), 0.0f, 0.0f);
        mOriginX = mPosition.x;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.x + mLength * getSize() < 0.0f;
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mOriginX - mPosition.x) / getSize() + 1.0f, Beam.MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mLength, 0.0f, PConstants.PI + PConstants.HALF_PI);
    }
}
