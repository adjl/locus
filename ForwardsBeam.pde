class ForwardsBeam extends Beam {

  ForwardsBeam(BeamType beamType, World world) {
    super(beamType);
    origin = new PVector(int(random(world.width())), -int(random(world.height())), world.depth() - 1);
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(0, 0, -beamType.velocity());
    acceleration = new PVector(0, 0, -beamType.acceleration());
    rotationX = HALF_PI;
    rotationZ = 0;
  }

  boolean isGone(World world) {
    return position.z + length * size < 0;
  }

  void move() {
    moveBeam();
    length = min((origin.z - position.z) / size + 1, maxLength);
  }
}