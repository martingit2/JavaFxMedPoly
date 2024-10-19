package Figurer;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class TekstFigur extends Figur {
    private Text tekst;
    private double startXOffset, startYOffset;

    public TekstFigur(double x, double y, String innhold, Color linjeFarge) {
        super(linjeFarge, null);
        tekst = new Text(x, y, innhold);
        tekst.setFill(linjeFarge);

        // Legg til dra-funksjonalitet
        tekst.setOnMousePressed(this::startFlytting);
        tekst.setOnMouseDragged(this::draFlytting);
    }

    private void startFlytting(MouseEvent e) {
        startXOffset = e.getX() - tekst.getX();
        startYOffset = e.getY() - tekst.getY();
    }

    private void draFlytting(MouseEvent e) {
        tekst.setX(e.getX() - startXOffset);
        tekst.setY(e.getY() - startYOffset);
    }

    @Override
    public void oppdater(double x, double y) {
        tekst.setX(x);
        tekst.setY(y);
    }

    @Override
    public Shape getShape() {
        return null; // Tekst er ikke en Shape, så vi returnerer null her
    }

    public Text getTekst() {
        return tekst;
    }
}
