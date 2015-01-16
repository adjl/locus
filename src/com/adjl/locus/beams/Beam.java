package com.adjl.locus.beams;

import com.adjl.locus.LocusWorld;

public interface Beam {

    public boolean isGone(LocusWorld world);

    void move();

    void draw();
}
