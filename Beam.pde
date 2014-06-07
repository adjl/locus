abstract class Beam {

  final float maxLength = 25.0f;
  final color[] colours = {#FF0000, #00FF00, #0000FF, #FFFF00, #00FFFF, #FF00FF};

  PVector origin, position, velocity, acceleration;
  float terminalVelocity, rotationX, rotationZ;
  float length, size;
  color colour;

  Beam(BeamType beamType) {
    terminalVelocity = beamType.terminalVelocity();
    size = beamType.size();
    colour = colours[int(random(colours.length))];
  }

  void moveBeam() {
    velocity.add(acceleration);
    velocity.limit(terminalVelocity);
    position.add(velocity);
  }

  void draw() {
    float opacity = map(maxLength - length, 0, maxLength, 0, 255);
    pushMatrix();
    strokeWeight(1);
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