package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class BackwardsBeam extends BeamImpl implements Beam {

    private final PVector mOrigin;
    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mRotationX;
    private final float mRotationZ;

    private float mLength;

    BackwardsBeam(PApplet sketch, BeamType type, LocusWorld world) {
        super(sketch, type);
        mOrigin = new PVector(nextInt(world.getWidth()), -nextInt(world.getHeight()), 0.0f);
        mPosition = new PVector(mOrigin.x, mOrigin.y, mOrigin.z);
        mVelocity = new PVector(0.0f, 0.0f, type.getVelocity());
        mAcceleration = new PVector(0.0f, 0.0f, type.getAcceleration());
        mRotationX = PConstants.PI + PConstants.HALF_PI;
        mRotationZ = 0.0f;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.z - mLength * getSize() >= world.getDepth();
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mPosition.z - mOrigin.z) / getSize() + 1.0f, Beam.MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mRotationX, mRotationZ, mLength);
    }
}
