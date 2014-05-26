enum BeamType {

  SLOW(1f, 0.1f, 3f, 0.5f, 5f),
  NORMAL(2f, 0.2f, 6f, 0.7f, 7f),
  FAST(3f, 0.3f, 9f, 0.9f, 9f);

  final float velocity;
  final float acceleration;
  final float terminalVelocity;
  final float opacity;
  final float size;

  BeamType(float velocity, float acceleration, float terminalVelocity, float opacity, float size) {
    this.velocity = velocity;
    this.acceleration = acceleration;
    this.terminalVelocity = terminalVelocity;
    this.opacity = opacity * 255;
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

  float opacity() {
    return opacity;
  }

  float size() {
    return size;
  }
}