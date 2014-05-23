class NexusBeams {

  final int chanceOfFiring = 20; // 1 in 20

  ArrayList<Beam> beams;
  IntList colours;

  NexusBeams() {
    beams = new ArrayList<Beam>();
    colours = new IntList();
    for (int i = 0; i < 4; i++) { // Number of colours
      colours.append(i);
    }
  }

  void update() {
    if (int(random(chanceOfFiring)) == 0) {
      beams.add(newBeam());
    }
    for (int i = beams.size() - 1; i >= 0; i--) {
      beams.get(i).move();
      if (beams.get(i).isGone()) beams.remove(i);
    }
  }

  void draw() {
    for (Beam beam : beams) {
      beam.draw();
    }
  }

  void createTouchBeams(int touchX, int touchY) {
    Beam[] touchBeams = newTouchBeams(touchX, touchY);
    for (int i = 0; i < touchBeams.length; i++) {
      beams.add(touchBeams[i]);
    }
  }

  Beam newBeam() {
    Beam beam = null;
    int direction = int(random(4)); // Number of directions
    switch (direction) {
      case 0: // Up
        beam = new UpwardsBeam(randomBeamType());
        break;
      case 1: // Down
        beam = new DownwardsBeam(randomBeamType());
        break;
      case 2: // Left
        beam = new LeftwardsBeam(randomBeamType());
        break;
      case 3: // Right
        beam = new RightwardsBeam(randomBeamType());
        break;
    }
    return beam;
  }

  Beam[] newTouchBeams(int touchX, int touchY) {
    BeamType beamtype = randomBeamType();
    colours.shuffle();
    return new Beam[] {
      new UpwardsBeam(beamtype, touchX, touchY, colours.get(0)),
      new DownwardsBeam(beamtype, touchX, touchY, colours.get(1)),
      new LeftwardsBeam(beamtype, touchX, touchY, colours.get(2)),
      new RightwardsBeam(beamtype, touchX, touchY, colours.get(3))
    };
  }

  BeamType randomBeamType() {
    return BeamType.values()[int(random(BeamType.values().length))];
  }
}