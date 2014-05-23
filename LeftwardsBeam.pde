class LeftwardsBeam extends Beam {

  LeftwardsBeam(BeamType beamType) {
    super(beamType);
    origin = new PVector(width - 1, int(random(height)));
    position = new PVector(origin.x, origin.y);
    velocity = new PVector(-beamType.velocity(), 0);
    acceleration = new PVector(-beamType.acceleration(), 0);
    angle = PI + HALF_PI;
  }

  LeftwardsBeam(BeamType beamType, float originX, float originY, int colourID) {
    super(beamType, originX, originY, colourID);
    velocity = new PVector(-beamType.velocity(), 0);
    acceleration = new PVector(-beamType.acceleration(), 0);
    angle = PI + HALF_PI;
  }

  boolean isGone() {
    return position.x + length * size < 0;
  }

  void move() {
    velocity.add(acceleration);
    velocity.limit(terminalVelocity);
    position.add(velocity);
    length = min((origin.x - position.x) / size + 1, maxLength);
  }

  void draw() {
    drawBeam(position.x - size * 0.5, position.y + size * 0.5);
  }
}