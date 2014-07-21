class DownwardsBeam extends Beam {

  DownwardsBeam(BeamType beamType, World world) {
    super(beamType);
    origin = new PVector(int(random(world.width())), 1 - world.height(), int(random(world.depth())));
    position = new PVector(origin.x, origin.y, origin.z);
    velocity = new PVector(0, beamType.velocity(), 0);
    acceleration = new PVector(0, beamType.acceleration(), 0);
    rotationX = 0.0;
    rotationZ = PI;
  }

  boolean isGone(World world) {
    return position.y - length * size > 0;
  }

  void move() {
    moveBeam();
    length = min((position.y - origin.y) / size + 1, BEAM_MAX_LENGTH);
  }
}
