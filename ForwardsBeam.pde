class ForwardsBeam extends Beam {

  ForwardsBeam(BeamType beamType, Platform platform) {
    super(beamType);
    origin = new PVector(int(random(platform.width())), -int(random(platform.height())), platform.depth() - 1);
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(0, 0, -beamType.velocity());
    acceleration = new PVector(0, 0, -beamType.acceleration());
    rotationX = HALF_PI;
    rotationZ = 0;
  }

  ForwardsBeam(BeamType beamType, float originX, float originY, float originZ, int colourID) {
    super(beamType, originX, originY, originZ, colourID);
    velocity = new PVector(0, 0, -beamType.velocity());
    acceleration = new PVector(0, 0, -beamType.acceleration());
    rotationX = HALF_PI;
    rotationZ = 0;
  }

  boolean isGone(Platform platform) {
    return position.z + length * size < 0;
  }

  void move() {
    velocity.add(acceleration);
    velocity.limit(terminalVelocity);
    position.add(velocity);
    length = min((origin.z - position.z) / size + 1, maxLength);
  }

  void draw() {
    drawBeam(position.x - size * 0.5, position.y - size * 0.5, position.z - size * 0.5);
  }
}