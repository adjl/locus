class World {

  float width;
  float height;
  float depth;

  World(float width, float height, float depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
  }

  float width() {
    return width;
  }

  float height() {
    return height;
  }

  float depth() {
    return depth;
  }

  boolean contains(PVector position) {
    // Boundaries seem to be uneven, hence the magic numbers
    return (position.x >= -(width - 2) / 2) && (position.x < (width - 1) / 2) &&
           (position.y >= 0) && (position.y < height) &&
           (position.z >= -depth / 2) && (position.z < (depth - 3) / 2);
  }

  void draw() {
    pushMatrix();
    rectMode(CENTER);
    stroke(#FFFFFF);
    strokeWeight(2);
    noFill();
    rotateX(HALF_PI);
    rect(0, 0, width, depth);
    popMatrix();
  }
}