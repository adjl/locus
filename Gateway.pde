final int width = 1366;
final int height = 768;
final float cameraHeight = 50;
final float platformWidth = 200;
final float platformHeight = 100;
final float platformDepth = 200;

Mouse mouse;
Camera camera;
Platform platform;

void setup() {
  size(width, height, P3D);
  noCursor();
  mouse = new Mouse();
  camera = new Camera(mouse, cameraHeight);
  platform = new Platform(platformWidth, platformHeight, platformDepth);
}

void draw() {
  background(#000000);
  if (mouse.centred()) mouse.move();
  else mouse.centre();
  camera.set();
  platform.draw();
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
    case 'q': // Quit
      exit();
      break;
  }
}