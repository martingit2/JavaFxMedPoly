import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import Figurer.*;

public class TegneGrensesnitt extends Application {

    private Pane tegneflate;
    private ColorPicker linjeFargePicker;
    private ColorPicker fyllFargePicker;
    private Figur aktivFigur;
    private ToggleButton flytteModusKnapp;

    @Override
    public void start(Stage primaryStage) {
        // Opprett hovedlayout
        BorderPane root = new BorderPane();

        // Opprett tegneflaten
        tegneflate = new Pane();
        tegneflate.setStyle("-fx-background-color: white;");
        root.setCenter(tegneflate);

        // Opprett kontrollpanel for figurvalg
        VBox figurKontroller = new VBox();
        figurKontroller.setSpacing(10);

        Label figurLabel = new Label("Velg figur:");
        ToggleGroup figurGruppe = new ToggleGroup();

        RadioButton linjeKnapp = new RadioButton("Linje");
        linjeKnapp.setToggleGroup(figurGruppe);
        linjeKnapp.setSelected(true); // Standardvalg

        RadioButton rektangelKnapp = new RadioButton("Rektangel");
        rektangelKnapp.setToggleGroup(figurGruppe);

        RadioButton sirkelKnapp = new RadioButton("Sirkel");
        sirkelKnapp.setToggleGroup(figurGruppe);

        RadioButton tekstKnapp = new RadioButton("Tekst");
        tekstKnapp.setToggleGroup(figurGruppe);

        figurKontroller.getChildren().addAll(figurLabel, linjeKnapp, rektangelKnapp, sirkelKnapp, tekstKnapp);

        // Fargevelgere
        linjeFargePicker = new ColorPicker(Color.BLACK);
        fyllFargePicker = new ColorPicker(Color.TRANSPARENT);

        Label linjeFargeLabel = new Label("Linjefarge:");
        Label fyllFargeLabel = new Label("Fyllfarge:");

        figurKontroller.getChildren().addAll(linjeFargeLabel, linjeFargePicker, fyllFargeLabel, fyllFargePicker);

        // Legg til en ToggleButton for å veksle mellom tegning og flytting
        flytteModusKnapp = new ToggleButton("Flyttemodus");
        figurKontroller.getChildren().add(flytteModusKnapp);

        // Legg til Reset-knappen
        Button resetButton = new Button("Reset");
        figurKontroller.getChildren().add(resetButton);

        root.setLeft(figurKontroller);

        // Sett opp Scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Tegneprogram");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Lytt til valg av figurtype
        linjeKnapp.setOnAction(e -> valgtFigurType = "Linje");
        rektangelKnapp.setOnAction(e -> valgtFigurType = "Rektangel");
        sirkelKnapp.setOnAction(e -> valgtFigurType = "Sirkel");
        tekstKnapp.setOnAction(e -> valgtFigurType = "Tekst");

        // Lytt til tegning med musen (kun i tegne-modus)
        tegneflate.setOnMousePressed(e -> {
            if (!flytteModusKnapp.isSelected()) {
                startTegning(e.getX(), e.getY());
            }
        });
        tegneflate.setOnMouseDragged(e -> {
            if (!flytteModusKnapp.isSelected()) {
                fortsettTegning(e.getX(), e.getY());
            }
        });
        tegneflate.setOnMouseReleased(e -> {
            if (!flytteModusKnapp.isSelected()) {
                avsluttTegning();
            }
        });

        // Lytt til Reset-knappen
        resetButton.setOnAction(e -> resetTegneflate());
    }

    private String valgtFigurType = "Linje"; // Standard figurtype

    /**
     * Starter tegning basert på valgt figurtype
     * @param x Startkoordinat X
     * @param y Startkoordinat Y
     */
    private void startTegning(double x, double y) {
        Color linjeFarge = linjeFargePicker.getValue();
        Color fyllFarge = fyllFargePicker.getValue();

        switch (valgtFigurType) {
            case "Linje":
                aktivFigur = new Linje(x, y, x, y, linjeFarge);
                tegneflate.getChildren().add(aktivFigur.getShape());
                leggTilFlyttefunksjon(aktivFigur.getShape());
                break;
            case "Rektangel":
                aktivFigur = new Rektangel(x, y, 0, 0, linjeFarge, fyllFarge);
                tegneflate.getChildren().add(aktivFigur.getShape());
                leggTilFlyttefunksjon(aktivFigur.getShape());
                break;
            case "Sirkel":
                aktivFigur = new Sirkel(x, y, 0, linjeFarge, fyllFarge);
                tegneflate.getChildren().add(aktivFigur.getShape());
                leggTilFlyttefunksjon(aktivFigur.getShape());
                break;
            case "Tekst":
                // Dialog for å la brukeren skrive inn tekst
                TextInputDialog dialog = new TextInputDialog("Skriv tekst her");
                dialog.setTitle("Skriv inn tekst");
                dialog.setHeaderText("Legg til tekst");
                dialog.setContentText("Tekst:");

                dialog.showAndWait().ifPresent(innhold -> {
                    aktivFigur = new TekstFigur(x, y, innhold, linjeFarge);
                    tegneflate.getChildren().add(((TekstFigur) aktivFigur).getTekst());
                    leggTilFlyttefunksjon(((TekstFigur) aktivFigur).getTekst());
                });
                break;
        }
    }

    /**
     * Oppdaterer den aktive figuren mens musen dras
     * @param x Gjeldende koordinat X
     * @param y Gjeldende koordinat Y
     */
    private void fortsettTegning(double x, double y) {
        if (aktivFigur != null) {
            aktivFigur.oppdater(x, y); // Bruk polymorfi
        }
    }

    /**
     * Avslutter tegning av figuren når musen slippes
     */
    private void avsluttTegning() {
        aktivFigur = null;
    }

    /**
     * Tilbakestiller tegneflaten og fjerner alle figurer
     */
    private void resetTegneflate() {
        tegneflate.getChildren().clear(); // Fjerner alle figurer fra tegneflaten
    }

    /**
     * Legger til flyttefunksjon for en figur
     * @param figur Den grafiske figuren (Node)
     */
    private void leggTilFlyttefunksjon(javafx.scene.Node figur) {
        figur.setOnMousePressed(e -> {
            if (flytteModusKnapp.isSelected()) {
                figur.setTranslateX(e.getSceneX() - figur.getLayoutX());
                figur.setTranslateY(e.getSceneY() - figur.getLayoutY());
            }
        });

        figur.setOnMouseDragged(e -> {
            if (flytteModusKnapp.isSelected()) {
                figur.setLayoutX(e.getSceneX() - figur.getTranslateX());
                figur.setLayoutY(e.getSceneY() - figur.getTranslateY());
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
