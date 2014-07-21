abstract class Beam {

  PVector origin, position, velocity, acceleration;
  float terminalVelocity, rotationX, rotationZ, length, size;
  color colour;

  Beam(BeamType beamType) {
    terminalVelocity = beamType.terminalVelocity();
    size = beamType.size();
    colour = COLOURS[int(random(COLOURS.length))];
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
