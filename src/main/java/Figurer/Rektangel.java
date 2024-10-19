package Figurer;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Rektangel extends Figur {
    private Rectangle rektangel;
    private double startXOffset, startYOffset;

    public Rektangel(double startX, double startY, double bredde, double høyde, Color linjeFarge, Color fyllFarge) {
        super(linjeFarge, fyllFarge);
        rektangel = new Rectangle(startX, startY, bredde, høyde);
        rektangel.setStroke(linjeFarge);
        rektangel.setFill(fyllFarge);

        // Legg til dra-funksjonalitet
        rektangel.setOnMousePressed(this::startFlytting);
        rektangel.setOnMouseDragged(this::draFlytting);
    }

    private void startFlytting(MouseEvent e) {
        startXOffset = e.getX() - rektangel.getX();
        startYOffset = e.getY() - rektangel.getY();
    }

    private void draFlytting(MouseEvent e) {
        rektangel.setX(e.getX() - startXOffset);
        rektangel.setY(e.getY() - startYOffset);
    }

    @Override
    public void oppdater(double x, double y) {
        rektangel.setWidth(Math.abs(x - rektangel.getX()));
        rektangel.setHeight(Math.abs(y - rektangel.getY()));
    }

    @Override
    public Shape getShape() {
        return rektangel;
    }
}
