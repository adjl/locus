class BackwardsBeam extends Beam {

  BackwardsBeam(BeamType beamType, Platform platform) {
    super(beamType);
    origin = new PVector(int(random(platform.width())), -int(random(platform.height())), 0);
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(0, 0, beamType.velocity());
    acceleration = new PVector(0, 0, beamType.acceleration());
    rotationX = PI + HALF_PI;
    rotationZ = 0;
  }

  BackwardsBeam(BeamType beamType, float originX, float originY, float originZ, int colourID) {
    super(beamType, originX, originY, originZ, colourID);
    velocity = new PVector(0, 0, beamType.velocity());
    acceleration = new PVector(0, 0, beamType.acceleration());
    rotationX = PI + HALF_PI;
    rotationZ = 0;
  }

  boolean isGone(Platform platform) {
    return position.z - length * size >= platform.depth();
  }

  void move() {
    velocity.add(acceleration);
    velocity.limit(terminalVelocity);
    position.add(velocity);
    length = min((position.z - origin.z) / size + 1, maxLength);
  }

  void draw() {
    drawBeam(position.x - size * 0.5, position.y - size * 0.5, position.z - size * 0.5);
  }
}