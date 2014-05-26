abstract class Beam {

  final float maxLength = 25;
  final color red = color(255, 0, 0);
  final color green = color(0, 255, 0);
  final color blue = color(0, 0, 255);
  final color yellow = color(255, 255, 0);
  final color cyan = color(0, 255, 255);
  final color magenta = color(255, 0, 255);
  final color[] colours = {red, green, blue, yellow, cyan, magenta};

  PVector origin, position, velocity, acceleration;
  float terminalVelocity, rotationX, rotationZ;
  float length, size;
  color colour;

  Beam(BeamType beamType) {
    terminalVelocity = beamType.terminalVelocity();
    size = beamType.size();
    colour = colours[int(random(colours.length))];
  }

  Beam(BeamType beamType, float originX, float originY, float originZ, int colourID) {
    origin = new PVector(originX, originY, originZ);
    position = new PVector(originX, originY, originZ);
    terminalVelocity = beamType.terminalVelocity();
    size = beamType.size();
    colour = colours[colourID];
  }

  void drawBeam(float positionX, float positionY, float positionZ) {
    float opacity = map(maxLength - length, 0, maxLength, 0, 255);
    pushMatrix();
    strokeWeight(1);
    translate(positionX, positionY, positionZ);
    rotateX(rotationX);
    rotateZ(rotationZ);
    scale(size);
    beginShape(LINES);
    stroke(colour);
    vertex(0, 0, 0);
    stroke(colour, opacity);
    vertex(0, length, 0);
    endShape(CLOSE);
    popMatrix();
  }

  abstract boolean isGone(Platform platform);
  abstract void move();
  abstract void draw();
}