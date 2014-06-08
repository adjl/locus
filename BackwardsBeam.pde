class BackwardsBeam extends Beam {

  BackwardsBeam(BeamType beamType, World world) {
    super(beamType);
    origin = new PVector(int(random(world.width())), -int(random(world.height())), 0);
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(0, 0, beamType.velocity());
    acceleration = new PVector(0, 0, beamType.acceleration());
    rotationX = PI + HALF_PI;
    rotationZ = 0.0f;
  }

  boolean isGone(World world) {
    return position.z - length * size >= world.depth();
  }

  void move() {
    moveBeam();
    length = min((position.z - origin.z) / size + 1, maxLength);
  }
}