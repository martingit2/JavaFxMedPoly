package Figurer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Figur {
    protected Color linjeFarge;
    protected Color fyllFarge;

    public Figur(Color linjeFarge, Color fyllFarge) {
        this.linjeFarge = linjeFarge;
        this.fyllFarge = fyllFarge;
    }

    // Felles metode for alle figurer som oppdaterer størrelsen eller formen
    public abstract void oppdater(double x, double y);

    // Returner figuren som Shape
    public abstract Shape getShape();
}
