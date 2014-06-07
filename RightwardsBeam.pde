class RightwardsBeam extends Beam {

  RightwardsBeam(BeamType beamType, World world) {
    super(beamType);
    origin = new PVector(0, -int(random(world.height())), int(random(world.depth())));
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(beamType.velocity(), 0, 0);
    acceleration = new PVector(beamType.acceleration(), 0, 0);
    rotationX = 0;
    rotationZ = HALF_PI;
  }

  boolean isGone(World world) {
    return position.x - length * size >= world.width();
  }

  void move() {
    moveBeam();
    length = min((position.x - origin.x) / size + 1, maxLength);
  }
}