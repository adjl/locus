enum BeamType {

  SLOW(1.0f, 0.1f, 3.0f, 5.0f),
  NORMAL(2.0f, 0.2f, 6.0f, 7.0f),
  FAST(3.0f, 0.3f, 9.0f, 9.0f);

  final float velocity;
  final float acceleration;
  final float terminalVelocity;
  final float size;

  BeamType(float velocity, float acceleration, float terminalVelocity, float size) {
    this.velocity = velocity;
    this.acceleration = acceleration;
    this.terminalVelocity = terminalVelocity;
    this.size = size;
  }

  float velocity() {
    return velocity;
  }

  float acceleration() {
    return acceleration;
  }

  float terminalVelocity() {
    return terminalVelocity;
  }

  float size() {
    return size;
  }
}