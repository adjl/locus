package com.adjl.locus;

import com.adjl.virtualcamera.VirtualCamera;
import com.adjl.virtualcamera.VirtualWorld;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.IntList;

/**
 * @author Helena Josol
 */
public class Locus extends PApplet {

    final int BEAM_CHANCE_OF_FIRING = 1; // 1 in 1 (always)
    final int COLOURS_COUNT = 6; // Number of colours
    final float WORLD_WIDTH = 2000.0f;
    final float WORLD_HEIGHT = 2000.0f;
    final float WORLD_DEPTH = 2000.0f;
    final float CAMERA_HEIGHT = 50.0f;
    final float CAMERA_SPEED = 3.0f;
    final float BEAM_MAX_LENGTH = 25.0f;
    final int[] COLOURS = { color(255, 0, 0), color(0, 255, 0), color(0, 0, 255),
            color(0, 255, 255), color(255, 0, 255), color(255, 255, 0) };

    World world;
    LocusBeams beams;
    VirtualCamera camera;
    boolean running;

    @Override
    public void setup() {
        size(displayWidth, displayHeight, P3D);
        noCursor();
        world = new World(WORLD_WIDTH, WORLD_HEIGHT, WORLD_DEPTH);
        beams = new LocusBeams(world);
        camera = new VirtualCamera(this, world, CAMERA_HEIGHT, CAMERA_SPEED);
        running = true;
    }

    @Override
    public void draw() {
        background(color(0, 0, 0));
        world.draw();
        if (running) {
            beams.update();
        }
        beams.draw();
        camera.set();
    }

    @Override
    public void keyPressed() {
        camera.move(key);
    }

    class World implements VirtualWorld {

        float width;
        float height;
        float depth;

        World(float width, float height, float depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
        }

        float width() {
            return width;
        }

        float depth() {
            return depth;
        }

        @Override
        public float getHeight() {
            return height;
        }

        @Override
        public boolean isWithin(PVector position) {
            return (position.x >= -width / 2.0f) && (position.x < width / 2.0f)
                    && (position.y >= 0.0f) && (position.y < height)
                    && (position.z >= -depth / 2.0f) && (position.z < depth / 2.0f);
        }

        @Override
        public void draw() {
            rectMode(CENTER);
            stroke(color(255, 255, 255));
            strokeWeight(2);
            noFill();
            pushMatrix();
            rotateX(HALF_PI);
            rect(0, 0, width, depth);
            popMatrix();
        }
    }

    class LocusBeams {

        World world;
        ArrayList<Beam> beams;
        IntList colours;

        LocusBeams(World world) {
            this.world = world;
            beams = new ArrayList<>();
            colours = new IntList();
            for (int i = 0; i < COLOURS_COUNT; i++) {
                colours.append(i);
            }
        }

        void update() {
            if ((int) random(BEAM_CHANCE_OF_FIRING) == 0) {
                beams.add(newBeam());
            }
            for (int i = beams.size() - 1; i >= 0; i--) {
                beams.get(i).move();
                if (beams.get(i).isGone(world)) {
                    beams.remove(i);
                }
            }
        }

        void draw() {
            pushMatrix();
            translate(world.width() / 2, 0, world.depth() / 2);
            rotateY(PI);
            for (Beam beam : beams) {
                beam.draw();
            }
            popMatrix();
        }

        Beam newBeam() {
            Beam beam = null;
            int direction = (int) random(6); // Number of directions
            switch (direction) {
                case 0: // Up
                    beam = new UpwardsBeam(randomBeamType(), world);
                    break;
                case 1: // Down
                    beam = new DownwardsBeam(randomBeamType(), world);
                    break;
                case 2: // Left
                    beam = new LeftwardsBeam(randomBeamType(), world);
                    break;
                case 3: // Right
                    beam = new RightwardsBeam(randomBeamType(), world);
                    break;
                case 4: // Forward
                    beam = new ForwardsBeam(randomBeamType(), world);
                    break;
                case 5: // Backward
                    beam = new BackwardsBeam(randomBeamType(), world);
                    break;
                default:
                    break;
            }
            return beam;
        }

        BeamType randomBeamType() {
            return BeamType.values()[(int) random(BeamType.values().length)];
        }
    }

    abstract class Beam {

        PVector origin, position, velocity, acceleration;
        float terminalVelocity, rotationX, rotationZ, length, size;
        int colour;

        Beam(BeamType beamType) {
            terminalVelocity = beamType.terminalVelocity();
            size = beamType.size();
            colour = COLOURS[(int) random(COLOURS.length)];
        }

        void moveBeam() {
            velocity.add(acceleration);
            velocity.limit(terminalVelocity);
            position.add(velocity);
        }

        void draw() {
            float opacity = map(BEAM_MAX_LENGTH - length, 0, BEAM_MAX_LENGTH, 0, 255);
            strokeWeight(1);
            pushMatrix();
            translate(position.x, position.y, position.z);
            rotateX(rotationX);
            rotateZ(rotationZ);
            scale(1, size, 1);
            beginShape(LINES);
            stroke(colour);
            vertex(0, 0, 0);
            stroke(colour, opacity);
            vertex(0, length, 0);
            endShape(CLOSE);
            popMatrix();
        }

        abstract boolean isGone(World world);

        abstract void move();
    }

    enum BeamType {

        SLOW(1.0f, 0.1f, 3.0f, 5.0f), NORMAL(2.0f, 0.2f, 6.0f, 7.0f), FAST(3.0f, 0.3f, 9.0f, 9.0f);

        final float velocity;
        final float acceleration;
        final float terminalVelocity;
        final float size;

        BeamType(float velocity, float acceleration, float terminalVelocity, float size) {
            this.velocity = velocity;
            this.acceleration = acceleration;
            this.terminalVelocity = terminalVelocity;
            this.size = size;
        }

        float velocity() {
            return velocity;
        }

        float acceleration() {
            return acceleration;
        }

        float terminalVelocity() {
            return terminalVelocity;
        }

        float size() {
            return size;
        }
    }

    class ForwardsBeam extends Beam {

        ForwardsBeam(BeamType beamType, World world) {
            super(beamType);
            origin =
                    new PVector((int) random(world.width()), (int) -random(world.getHeight()),
                            world.depth() - 1);
            position = new PVector(origin.x, origin.y, origin.z);
            velocity = new PVector(0, 0, -beamType.velocity());
            acceleration = new PVector(0, 0, -beamType.acceleration());
            rotationX = HALF_PI;
            rotationZ = 0.0f;
        }

        @Override
        boolean isGone(World world) {
            return position.z + length * size < 0;
        }

        @Override
        void move() {
            moveBeam();
            length = min((origin.z - position.z) / size + 1, BEAM_MAX_LENGTH);
        }
    }

    class BackwardsBeam extends Beam {

        BackwardsBeam(BeamType beamType, World world) {
            super(beamType);
            origin = new PVector((int) random(world.width()), (int) -random(world.getHeight()), 0);
            position = new PVector(origin.x, origin.y, origin.z);
            velocity = new PVector(0, 0, beamType.velocity());
            acceleration = new PVector(0, 0, beamType.acceleration());
            rotationX = PI + HALF_PI;
            rotationZ = 0.0f;
        }

        @Override
        boolean isGone(World world) {
            return position.z - length * size >= world.depth();
        }

        @Override
        void move() {
            moveBeam();
            length = min((position.z - origin.z) / size + 1, BEAM_MAX_LENGTH);
        }
    }

    class LeftwardsBeam extends Beam {

        LeftwardsBeam(BeamType beamType, World world) {
            super(beamType);
            origin =
                    new PVector(world.width() - 1, (int) -random(world.getHeight()),
                            (int) random(world.depth()));
            position = new PVector(origin.x, origin.y, origin.z);
            velocity = new PVector(-beamType.velocity(), 0, 0);
            acceleration = new PVector(-beamType.acceleration(), 0, 0);
            rotationX = 0.0f;
            rotationZ = PI + HALF_PI;
        }

        @Override
        boolean isGone(World world) {
            return position.x + length * size < 0;
        }

        @Override
        void move() {
            moveBeam();
            length = min((origin.x - position.x) / size + 1, BEAM_MAX_LENGTH);
        }
    }

    class RightwardsBeam extends Beam {

        RightwardsBeam(BeamType beamType, World world) {
            super(beamType);
            origin = new PVector(0, (int) -random(world.getHeight()), (int) random(world.depth()));
            position = new PVector(origin.x, origin.y, origin.z);
            velocity = new PVector(beamType.velocity(), 0, 0);
            acceleration = new PVector(beamType.acceleration(), 0, 0);
            rotationX = 0.0f;
            rotationZ = HALF_PI;
        }

        @Override
        boolean isGone(World world) {
            return position.x - length * size >= world.width();
        }

        @Override
        void move() {
            moveBeam();
            length = min((position.x - origin.x) / size + 1, BEAM_MAX_LENGTH);
        }
    }

    class UpwardsBeam extends Beam {

        UpwardsBeam(BeamType beamType, World world) {
            super(beamType);
            origin = new PVector((int) random(world.width()), 0, (int) random(world.depth()));
            position = new PVector(origin.x, origin.y, origin.z);
            velocity = new PVector(0, -beamType.velocity(), 0);
            acceleration = new PVector(0, -beamType.acceleration(), 0);
            rotationX = 0.0f;
            rotationZ = 0.0f;
        }

        @Override
        boolean isGone(World world) {
            return position.y + length * size <= -world.getHeight();
        }

        @Override
        void move() {
            moveBeam();
            length = min((origin.y - position.y) / size + 1, BEAM_MAX_LENGTH);
        }
    }

    class DownwardsBeam extends Beam {

        DownwardsBeam(BeamType beamType, World world) {
            super(beamType);
            origin =
                    new PVector((int) random(world.width()), 1 - world.getHeight(),
                            (int) random(world.depth()));
            position = new PVector(origin.x, origin.y, origin.z);
            velocity = new PVector(0, beamType.velocity(), 0);
            acceleration = new PVector(0, beamType.acceleration(), 0);
            rotationX = 0.0f;
            rotationZ = PI;
        }

        @Override
        boolean isGone(World world) {
            return position.y - length * size > 0;
        }

        @Override
        void move() {
            moveBeam();
            length = min((position.y - origin.y) / size + 1, BEAM_MAX_LENGTH);
        }
    }

    public static void main(String _args[]) {
        PApplet.main(new String[] { "--present", com.adjl.locus.Locus.class.getName() });
    }
}
