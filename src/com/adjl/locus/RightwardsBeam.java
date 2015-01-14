package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class RightwardsBeam extends BeamImpl implements Beam {

    private final PVector mOrigin;
    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mRotationX;
    private final float mRotationZ;

    private float mLength;

    RightwardsBeam(PApplet sketch, BeamType type, LocusWorld world) {
        super(sketch, type);
        mOrigin = new PVector(0.0f, -nextInt(world.getHeight()), nextInt(world.getDepth()));
        mPosition = new PVector(mOrigin.x, mOrigin.y, mOrigin.z);
        mVelocity = new PVector(type.getVelocity(), 0.0f, 0.0f);
        mAcceleration = new PVector(type.getAcceleration(), 0.0f, 0.0f);
        mRotationX = 0.0f;
        mRotationZ = PConstants.HALF_PI;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.x - mLength * getSize() >= world.getWidth();
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mPosition.x - mOrigin.x) / getSize() + 1.0f, Beam.MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mRotationX, mRotationZ, mLength);
    }
}
