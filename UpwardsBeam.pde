class UpwardsBeam extends Beam {

  UpwardsBeam(BeamType beamType, World world) {
    super(beamType);
    origin = new PVector(int(random(world.width())), 0, int(random(world.depth())));
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(0, -beamType.velocity(), 0);
    acceleration = new PVector(0, -beamType.acceleration(), 0);
    rotationX = 0;
    rotationZ = 0;
  }

  boolean isGone(World world) {
    return position.y + length * size <= -world.height();
  }

  void move() {
    moveBeam();
    length = min((origin.y - position.y) / size + 1, maxLength);
  }
}