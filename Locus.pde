final int width = 1366;
final int height = 768;
final float cameraHeight = 50;
final float platformWidth = 2000;
final float platformHeight = 2000;
final float platformDepth = 2000;

Camera camera;
Platform platform;
LocusBeams beams;
boolean isRunning;

void setup() {
  size(width, height, P3D);
  noCursor();
  camera = new Camera(cameraHeight);
  platform = new Platform(platformWidth, platformHeight, platformDepth);
  beams = new LocusBeams(platform);
  isRunning = true;
}

void draw() {
  background(#000000);
  camera.set();
  platform.draw();
  if (isRunning) beams.update();
  beams.draw();
}

void keyPressed() {
  switch (key) {
    case 'w': // Move forward
      if (platform.contains(camera.forwardPosition())) {
        camera.moveForward();
      }
      break;
    case 'a': // Strafe left
      if (platform.contains(camera.leftPosition())) {
        camera.strafeLeft();
      }
      break;
    case 's': // Move backward
      if (platform.contains(camera.backwardPosition())) {
        camera.moveBackward();
      }
      break;
    case 'd': // Strafe right
      if (platform.contains(camera.rightPosition())) {
        camera.strafeRight();
      }
      break;
    case 'r': // Fly up
      if (platform.contains(camera.upPosition())) {
        camera.flyUp();
      }
      break;
    case 'f': // Fly down
      PVector position = camera.downPosition();
      if (platform.contains(position) && camera.aboveHeight(position)) {
        camera.flyDown();
      }
      break;
    case 'p': // Pause/resume
      isRunning = !isRunning;
      break;
    case 'q': // Quit
      exit();
      break;
  }
}