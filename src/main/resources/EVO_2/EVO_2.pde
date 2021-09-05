import org.evolib2.controller.*;
import java.util.List;

Simulation simulation=new Simulation();
Visualizer visualizer=simulation.getVisualizer();

color backgroundColor=color(
  visualizer.BACKGROUND_RED,
  visualizer.BACKGROUND_GREEN,
  visualizer.BACKGROUND_BLUE);

int wdth=visualizer.getWorldWidth();
int hght=visualizer.getWorldHeight();

boolean buttonPressed=false;

void settings() {
  size(wdth, hght);
}

void setup() {
  simulation.start();
  //frameRate(10);
  colorMode(HSB, 360, 100, 100, 255);
}

void draw() {
  background(backgroundColor,25);
  //fill(backgroundColor, 50);
  //rect(0, 0, wdth, hght);
  if (buttonPressed) {
    simulation.doTick();
    visualizer.doTick();
    List<ShapeDto> shapeDtos=visualizer.getShapeDtos();
    for (ShapeDto shapeDto : shapeDtos) {
      show(shapeDto);
    }
  }
}

void mousePressed() {
  buttonPressed=true;
}

void show(ShapeDto shapeDto) {
  if (shapeDto.isNoStroke) {
    noStroke();
  }

  if (shapeDto instanceof LineDto) {
    stroke(getColorFrom(shapeDto.colorDto));
    LineDto line=(LineDto)shapeDto;
    strokeWeight(line.strokeWeight);
    line(
      line.pointA.x,
      line.pointA.y,
      line.pointB.x,
      line.pointB.y);
  }

  if (shapeDto instanceof CircleDto) {
    CircleDto circle=(CircleDto)shapeDto;
    fill(getColorFrom(circle.colorDto));
    circle(
      circle.center.x,
      circle.center.y,
      circle.diameter
      );
  }
}

color getColorFrom(ColorDto colorDto) {
  return color(
    colorDto.hue,
    colorDto.saturation,
    colorDto.brightness,
    colorDto.alpha);
}
