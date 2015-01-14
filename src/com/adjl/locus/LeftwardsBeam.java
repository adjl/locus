package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class LeftwardsBeam extends BeamImpl implements Beam {

    private final PVector mOrigin;
    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mRotationX;
    private final float mRotationZ;

    private float mLength;

    LeftwardsBeam(PApplet sketch, BeamType type, LocusWorld world) {
        super(sketch, type);
        mOrigin = new PVector(world.getWidth() - 1.0f, -nextInt(world.getHeight()),
                nextInt(world.getDepth()));
        mPosition = new PVector(mOrigin.x, mOrigin.y, mOrigin.z);
        mVelocity = new PVector(-type.getVelocity(), 0.0f, 0.0f);
        mAcceleration = new PVector(-type.getAcceleration(), 0.0f, 0.0f);
        mRotationX = 0.0f;
        mRotationZ = PConstants.PI + PConstants.HALF_PI;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.x + mLength * getSize() < 0.0f;
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mOrigin.x - mPosition.x) / getSize() + 1.0f, Beam.MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mRotationX, mRotationZ, mLength);
    }
}
