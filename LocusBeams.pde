class LocusBeams {

  final int chanceOfFiring = 1; // Always

  World world;
  ArrayList<Beam> beams;
  IntList colours;

  LocusBeams(World world) {
    this.world = world;
    beams = new ArrayList<Beam>();
    colours = new IntList();
    for (int i = 0; i < 6; i++) { // Number of colours
      colours.append(i);
    }
  }

  void update() {
    if (int(random(chanceOfFiring)) == 0) beams.add(newBeam());
    for (int i = beams.size() - 1; i >= 0; i--) {
      beams.get(i).move();
      if (beams.get(i).isGone(world)) beams.remove(i);
    }
  }

  void draw() {
    pushMatrix();
    translate(world.width() / 2, 0, world.depth() / 2);
    rotateY(PI);
    for (Beam beam : beams) {
      beam.draw();
    }
    popMatrix();
  }

  Beam newBeam() {
    Beam beam = null;
    int direction = int(random(6)); // Number of directions
    switch (direction) {
      case 0: // Up
        beam = new UpwardsBeam(randomBeamType(), world);
        break;
      case 1: // Down
        beam = new DownwardsBeam(randomBeamType(), world);
        break;
      case 2: // Left
        beam = new LeftwardsBeam(randomBeamType(), world);
        break;
      case 3: // Right
        beam = new RightwardsBeam(randomBeamType(), world);
        break;
      case 4: // Forward
        beam = new ForwardsBeam(randomBeamType(), world);
        break;
      case 5: // Backward
        beam = new BackwardsBeam(randomBeamType(), world);
        break;
    }
    return beam;
  }

  BeamType randomBeamType() {
    return BeamType.values()[int(random(BeamType.values().length))];
  }
}