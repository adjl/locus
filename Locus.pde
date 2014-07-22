final int WIDTH = 1366;
final int HEIGHT = 768;
final int BEAM_CHANCE_OF_FIRING = 1; // 1 in 1 (always)
final int COLOURS_COUNT = 6; // Number of colours
final float WORLD_WIDTH = 2000.0;
final float WORLD_HEIGHT = 2000.0;
final float WORLD_DEPTH = 2000.0;
final float CAMERA_HEIGHT = 50.0;
final float CAMERA_SPEED = 3.0;
final float BEAM_MAX_LENGTH = 25.0;
final color[] COLOURS = {
  #FF0000, #00FF00, #0000FF, #00FFFF, #FF00FF, #FFFF00
};

World world;
LocusBeams beams;
Camera camera;
boolean running;

void setup() {
  size(WIDTH, HEIGHT, P3D);
  noCursor();
  world = new World(WORLD_WIDTH, WORLD_HEIGHT, WORLD_DEPTH);
  beams = new LocusBeams(world);
  camera = new Camera(CAMERA_HEIGHT, CAMERA_SPEED);
  running = true;
}

void draw() {
  background(#000000);
  world.draw();
  if (running) {
    beams.update();
  }
  beams.draw();
  camera.set();
}

void keyPressed() {
  camera.moveDirection(world, key);
}
