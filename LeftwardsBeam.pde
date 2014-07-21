class LeftwardsBeam extends Beam {

  LeftwardsBeam(BeamType beamType, World world) {
    super(beamType);
    origin = new PVector(world.width() - 1, -int(random(world.height())), int(random(world.depth())));
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(-beamType.velocity(), 0, 0);
    acceleration = new PVector(-beamType.acceleration(), 0, 0);
    rotationX = 0.0;
    rotationZ = PI + HALF_PI;
  }

  boolean isGone(World world) {
    return position.x + length * size < 0;
  }

  void move() {
    moveBeam();
    length = min((origin.x - position.x) / size + 1, BEAM_MAX_LENGTH);
  }
}
