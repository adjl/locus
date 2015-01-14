package com.adjl.locus;

interface Beam {

    static final float MAX_LENGTH = 25.0f;

    boolean isGone(LocusWorld world);

    void move();

    void draw();
}
