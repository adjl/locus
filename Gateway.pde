final int width = 1366;
final int height = 768;
final float cameraHeight = 50;
final float roomWidth = 200;
final float roomHeight = 100;
final float roomDepth = 200;

Mouse mouse;
Camera camera;
Room room;

void setup() {
  size(width, height, P3D);
  noCursor();
  mouse = new Mouse();
  camera = new Camera(mouse, cameraHeight);
  room = new Room(roomWidth, roomHeight, roomDepth);
}

void draw() {
  background(#000000);
  if (mouse.centred()) mouse.move();
  else mouse.centre();
  camera.set();
  room.draw();
}

void keyPressed() {
  switch (key) {
    case 'w': // Move forward
      if (room.contains(camera.forwardPosition())) {
        camera.moveForward();
      }
      break;
    case 'a': // Strafe left
      if (room.contains(camera.leftPosition())) {
        camera.strafeLeft();
      }
      break;
    case 's': // Move backward
      if (room.contains(camera.backwardPosition())) {
        camera.moveBackward();
      }
      break;
    case 'd': // Strafe right
      if (room.contains(camera.rightPosition())) {
        camera.strafeRight();
      }
      break;
    case 'q': // Quit
      exit();
      break;
  }
}