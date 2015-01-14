package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PVector;

class UpwardsBeam extends BeamImpl implements Beam {

    private final PVector mOrigin;
    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mRotationX;
    private final float mRotationZ;

    private float mLength;

    UpwardsBeam(PApplet sketch, BeamType type, LocusWorld world) {
        super(sketch, type);
        mOrigin = new PVector(nextInt(world.getWidth()), 0.0f, nextInt(world.getDepth()));
        mPosition = new PVector(mOrigin.x, mOrigin.y, mOrigin.z);
        mVelocity = new PVector(0.0f, -type.getVelocity(), 0.0f);
        mAcceleration = new PVector(0.0f, -type.getAcceleration(), 0.0f);
        mRotationX = 0.0f;
        mRotationZ = 0.0f;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.y + mLength * getSize() <= -world.getHeight();
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mOrigin.y - mPosition.y) / getSize() + 1.0f, Beam.MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mRotationX, mRotationZ, mLength);
    }
}
