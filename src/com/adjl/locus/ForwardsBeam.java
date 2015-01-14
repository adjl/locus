package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class ForwardsBeam extends BeamImpl implements Beam {

    private final PVector mOrigin;
    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mRotationX;
    private final float mRotationZ;

    private float mLength;

    ForwardsBeam(PApplet sketch, BeamType type, LocusWorld world) {
        super(sketch, type);
        mOrigin = new PVector(nextInt(world.getWidth()), -nextInt(world.getHeight()),
                world.getDepth() - 1.0f);
        mPosition = new PVector(mOrigin.x, mOrigin.y, mOrigin.z);
        mVelocity = new PVector(0.0f, 0.0f, -type.getVelocity());
        mAcceleration = new PVector(0.0f, 0.0f, -type.getAcceleration());
        mRotationX = PConstants.HALF_PI;
        mRotationZ = 0.0f;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.z + mLength * getSize() < 0.0f;
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mOrigin.z - mPosition.z) / getSize() + 1.0f, Beam.MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mRotationX, mRotationZ, mLength);
    }
}
