abstract class Beam {

  final float maxLength = 25;
  final color red = color(255, 0, 0);
  final color green = color(0, 255, 0);
  final color blue = color(0, 0, 255);
  final color yellow = color(255, 255, 0);
  final color[] colours = {red, green, blue, yellow};

  PVector origin, position, velocity, acceleration;
  float terminalVelocity, angle, length, opacity, size;
  color colour;

  Beam(BeamType beamType) {
    terminalVelocity = beamType.terminalVelocity();
    opacity = beamType.opacity();
    size = beamType.size();
    colour = colours[int(random(colours.length))];
  }

  Beam(BeamType beamType, float originX, float originY, int colourID) {
    origin = new PVector(originX, originY);
    position = new PVector(originX, originY);
    terminalVelocity = beamType.terminalVelocity();
    opacity = beamType.opacity();
    size = beamType.size();
    colour = colours[colourID];
  }

  void drawBeam(float positionX, float positionY) {
    float tailOpacity = map(maxLength - length, 0, maxLength, 0, opacity);
    pushMatrix();
    translate(positionX, positionY);
    rotate(angle);
    scale(size);
    beginShape(QUADS);
    fill(colour, opacity); // Body
    vertex(0, 0);
    vertex(1, 0);
    fill(colour, tailOpacity); // Tail
    vertex(1, length);
    vertex(0, length);
    fill(colour, opacity * 2); // Head
    vertex(0, 0);
    vertex(1, 0);
    fill(colour, 0);
    vertex(1, 1);
    vertex(0, 1);
    endShape(CLOSE);
    popMatrix();
  }

  abstract boolean isGone();
  abstract void move();
  abstract void draw();
}