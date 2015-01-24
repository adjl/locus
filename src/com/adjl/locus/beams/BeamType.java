package com.adjl.locus.beams;

enum BeamType {

    SLOW(1.0f, 0.1f, 3.0f, 5.0f), NORMAL(2.0f, 0.2f, 6.0f, 7.0f), FAST(3.0f, 0.3f, 9.0f, 9.0f);

    private final float mVelocity;
    private final float mAcceleration;
    private final float mTerminalVelocity;
    private final float mSize;

    private BeamType(float velocity, float acceleration, float terminalVelocity, float size) {
        mVelocity = velocity;
        mAcceleration = acceleration;
        mTerminalVelocity = terminalVelocity;
        mSize = size;
    }

    static BeamType getBeamType() {
        BeamType[] types = BeamType.values();
        return types[BeamFactory.randomise(types.length)];
    }

    float getVelocity() {
        return mVelocity;
    }

    float getAcceleration() {
        return mAcceleration;
    }

    float getTerminalVelocity() {
        return mTerminalVelocity;
    }

    float getSize() {
        return mSize;
    }
}
