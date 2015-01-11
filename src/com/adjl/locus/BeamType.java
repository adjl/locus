package com.adjl.locus;

enum BeamType {

    SLOW(1.0f, 0.1f, 3.0f, 5.0f), NORMAL(2.0f, 0.2f, 6.0f, 7.0f), FAST(3.0f, 0.3f, 9.0f, 9.0f);

    final float mVelocity;
    final float mAcceleration;
    final float mTerminalVelocity;
    final float mSize;

    BeamType(float velocity, float acceleration, float terminalVelocity, float size) {
        mVelocity = velocity;
        mAcceleration = acceleration;
        mTerminalVelocity = terminalVelocity;
        mSize = size;
    }

    float velocity() {
        return mVelocity;
    }

    float acceleration() {
        return mAcceleration;
    }

    float terminalVelocity() {
        return mTerminalVelocity;
    }

    float size() {
        return mSize;
    }
}
