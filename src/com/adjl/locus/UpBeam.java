package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PVector;

class UpBeam extends BeamImpl implements Beam {

    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mOriginY;

    private float mLength;

    UpBeam(BeamType type, LocusWorld world) {
        super(type);
        mPosition = new PVector(nextInt(world.getWidth()), 0.0f, nextInt(world.getDepth()));
        mVelocity = new PVector(0.0f, -type.getVelocity(), 0.0f);
        mAcceleration = new PVector(0.0f, -type.getAcceleration(), 0.0f);
        mOriginY = mPosition.y;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.y + mLength * getSize() <= -world.getHeight();
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mOriginY - mPosition.y) / getSize() + 1.0f, MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mLength, 0.0f, 0.0f);
    }
}
