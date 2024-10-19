package Figurer;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Sirkel extends Figur {
    private Circle sirkel;
    private double senterXOffset, senterYOffset;

    public Sirkel(double senterX, double senterY, double radius, Color linjeFarge, Color fyllFarge) {
        super(linjeFarge, fyllFarge);
        sirkel = new Circle(senterX, senterY, radius);
        sirkel.setStroke(linjeFarge);
        sirkel.setFill(fyllFarge);

        // Legg til dra-funksjonalitet
        sirkel.setOnMousePressed(this::startFlytting);
        sirkel.setOnMouseDragged(this::draFlytting);
    }

    private void startFlytting(MouseEvent e) {
        senterXOffset = e.getX() - sirkel.getCenterX();
        senterYOffset = e.getY() - sirkel.getCenterY();
    }

    private void draFlytting(MouseEvent e) {
        sirkel.setCenterX(e.getX() - senterXOffset);
        sirkel.setCenterY(e.getY() - senterYOffset);
    }

    @Override
    public void oppdater(double x, double y) {
        double radius = Math.sqrt(Math.pow(x - sirkel.getCenterX(), 2) + Math.pow(y - sirkel.getCenterY(), 2));
        sirkel.setRadius(radius);
    }

    @Override
    public Shape getShape() {
        return sirkel;
    }
}
