class ForwardsBeam extends Beam {

  ForwardsBeam(BeamType beamType, Platform platform) {
    super(beamType);
    origin = new PVector(int(random(platform.width())), 0, int(random(platform.depth())));
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(0, beamType.velocity(), 0);
    acceleration = new PVector(0, beamType.acceleration(), 0);
    angle = 0;
  }

  ForwardsBeam(BeamType beamType, float originX, float originY, float originZ, int colourID) {
    super(beamType, originX, originY, originZ, colourID);
    velocity = new PVector(0, beamType.velocity(), 0);
    acceleration = new PVector(0, beamType.acceleration(), 0);
    angle = 0;
  }

  boolean isGone(Platform platform) {
    return position.y - length * size >= platform.height();
  }

  void move() {
    velocity.add(acceleration);
    velocity.limit(terminalVelocity);
    position.add(velocity);
    length = min((position.y - origin.y) / size + 1, maxLength);
  }

  void draw() {
    drawBeam(position.x - size * 0.5, position.y - size * 0.5, position.z - size * 0.5); // TODO
  }
}