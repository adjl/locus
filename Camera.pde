import java.awt.AWTException;
import java.awt.Robot;

class Camera {

  Mouse mouse;
  PVector eye;
  PVector centre;
  PVector up;
  PVector angle;
  float minY;
  float speed;
  float fovy;
  float aspect;
  float zNear;
  float zFar;

  Camera(float minY, float speed) {
    mouse = new Mouse();
    eye = new PVector(0, minY, 0);
    centre = new PVector(0, minY, -1);
    up = new PVector(0, 1, 0);
    angle = new PVector();
    this.minY = minY;
    this.speed = speed;
    fovy = HALF_PI * 3 / 4;
    aspect = 4 / 3.075;
    zNear = 0.1;
    zFar = 10000;
    perspective(fovy, aspect, zNear, zFar);
  }

  boolean aboveHeight(PVector position) {
    return position.y >= minY;
  }

  PVector position(PVector direction) {
    PVector position = eye.get();
    position.add(direction);
    return position;
  }

  void move(PVector direction) {
    eye.add(direction);
    centre.add(direction);
  }

  PVector forward() {
    return new PVector(-sin(angle.x) * speed, 0, cos(angle.x) * speed);
  }

  PVector backward() {
    return new PVector(sin(angle.x) * speed, 0, -cos(angle.x) * speed);
  }

  PVector left() {
    return new PVector(sin(angle.x + HALF_PI) * speed, 0, -cos(angle.x + HALF_PI) * speed);
  }

  PVector right() {
    return new PVector(-sin(angle.x + HALF_PI) * speed, 0, cos(angle.x + HALF_PI) * speed);
  }

  PVector up() {
    return new PVector(0, speed, 0);
  }

  PVector down() {
    return new PVector(0, -speed, 0);
  }

  PVector forwardPosition() {
    return position(forward());
  }

  PVector backwardPosition() {
    return position(backward());
  }

  PVector leftPosition() {
    return position(left());
  }

  PVector rightPosition() {
    return position(right());
  }

  PVector upPosition() {
    return position(up());
  }

  PVector downPosition() {
    return position(down());
  }

  void moveForward() {
    move(forward());
  }

  void moveBackward() {
    move(backward());
  }

  void strafeLeft() {
    move(left());
  }

  void strafeRight() {
    move(right());
  }

  void flyUp() {
    move(up());
  }

  void flyDown() {
    move(down());
  }

  void set() {
    if (mouse.centred()) {
      mouse.move();
    } else {
      mouse.centre();
    }
    angle.x = mouse.x() * TAU / (width - 1);
    angle.y = mouse.y() * QUARTER_PI * 3 / (height - 1);
    beginCamera();
    camera(eye.x, eye.y, eye.z, centre.x, centre.y, centre.z, up.x, up.y, up.z);
    translate(eye.x, eye.y, eye.z);
    rotateX(angle.y);
    rotateY(angle.x);
    translate(centre.x, centre.y, centre.z);
    endCamera();
  }

  void moveDirection(World world, char key) {
    switch (key) {
      case 'w': // Move forward
        if (world.contains(forwardPosition())) {
          moveForward();
        }
        break;
      case 'a': // Strafe left
        if (world.contains(leftPosition())) {
          strafeLeft();
        }
        break;
      case 's': // Move backward
        if (world.contains(backwardPosition())) {
          moveBackward();
        }
        break;
      case 'd': // Strafe right
        if (world.contains(rightPosition())) {
          strafeRight();
        }
        break;
      case 'r': // Fly up
        if (world.contains(upPosition())) {
          flyUp();
        }
        break;
      case 'f': // Fly down
        PVector position = downPosition();
        if (world.contains(position) && aboveHeight(position)) {
          flyDown();
        }
        break;
      case 'q': // Quit
        exit();
        break;
    }
  }

  class Mouse {

    Robot robot;
    boolean centred;
    boolean wrapped;
    int attempt;

    Mouse() {
      try {
        robot = new Robot();
      } catch (AWTException e) {
        e.printStackTrace();
        exit();
      }
      centred = false;
      wrapped = false;
      attempt = 3;
    }

    boolean centred() {
      return centred;
    }

    int x() {
      return mouseX;
    }

    int y() {
      return (height / 2) - mouseY;
    }

    void centre() {
      robot.mouseMove(width / 2, height / 2);
      if (--attempt == 0) { // Cursor centering works on the third centre() call
        centred = true;
      }
    }

    void move() {
      if (wrapped && mouseX > 0 && mouseX < width - 1) { // Wrap cursor horizontally
        wrapped = false;
      } else if (!wrapped && mouseX == 0) {
        wrapped = true;
        robot.mouseMove(width - 1, mouseY);
      } else if (!wrapped && mouseX == width - 1) {
        wrapped = true;
        robot.mouseMove(0, mouseY);
      }
    }
  }
}
