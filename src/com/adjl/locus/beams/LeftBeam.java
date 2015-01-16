package com.adjl.locus.beams;

import com.adjl.locus.LocusWorld;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class LeftBeam extends BeamImpl implements Beam {

    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mOriginX;

    private float mLength;

    LeftBeam() {
        this(BeamType.getBeamType());
    }

    private LeftBeam(BeamType type) {
        super(type);
        mPosition = new PVector(getWorld().getWidth() - 1.0f, -nextInt(getWorld().getHeight()),
                nextInt(getWorld().getDepth()));
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
        mLength = PApplet.min((mOriginX - mPosition.x) / getSize() + 1.0f, MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mLength, 0.0f, PConstants.PI + PConstants.HALF_PI);
    }
}
