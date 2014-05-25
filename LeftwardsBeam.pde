class LeftwardsBeam extends Beam {

  LeftwardsBeam(BeamType beamType, Platform platform) {
    super(beamType);
    origin = new PVector(platform.width() - 1, -int(random(platform.height())), int(random(platform.depth())));
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(-beamType.velocity(), 0, 0);
    acceleration = new PVector(-beamType.acceleration(), 0, 0);
    rotationX = 0;
    rotationZ = PI + HALF_PI;
  }

  LeftwardsBeam(BeamType beamType, float originX, float originY, float originZ, int colourID) {
    super(beamType, originX, originY, originZ, colourID);
    velocity = new PVector(-beamType.velocity(), 0, 0);
    acceleration = new PVector(-beamType.acceleration(), 0, 0);
    rotationX = 0;
    rotationZ = PI + HALF_PI;
  }

  boolean isGone(Platform platform) {
    return position.x + length * size < 0;
  }

  void move() {
    velocity.add(acceleration);
    velocity.limit(terminalVelocity);
    position.add(velocity);
    length = min((origin.x - position.x) / size + 1, maxLength);
  }

  void draw() {
    drawBeam(position.x - size * 0.5, position.y + size * 0.5, position.z + size * 0.5);
  }
}