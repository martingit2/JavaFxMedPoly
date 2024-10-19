package Figurer;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Linje extends Figur {
    private Line linje;
    private double startXOffset, startYOffset;

    public Linje(double startX, double startY, double endX, double endY, Color linjeFarge) {
        super(linjeFarge, null);
        linje = new Line(startX, startY, endX, endY);
        linje.setStroke(linjeFarge);

        // Legg til dra-funksjonalitet
        linje.setOnMousePressed(this::startFlytting);
        linje.setOnMouseDragged(this::draFlytting);
    }

    private void startFlytting(MouseEvent e) {
        startXOffset = e.getX() - linje.getStartX();
        startYOffset = e.getY() - linje.getStartY();
    }

    private void draFlytting(MouseEvent e) {
        linje.setStartX(e.getX() - startXOffset);
        linje.setStartY(e.getY() - startYOffset);
        linje.setEndX(linje.getStartX() + linje.getEndX() - linje.getStartX());
        linje.setEndY(linje.getStartY() + linje.getEndY() - linje.getStartY());
    }

    @Override
    public void oppdater(double x, double y) {
        linje.setEndX(x);
        linje.setEndY(y);
    }

    @Override
    public Shape getShape() {
        return linje;
    }
}
