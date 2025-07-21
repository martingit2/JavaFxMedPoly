# JavaFX Tegneprogram med Polymorfisme

Dette er et enkelt, interaktivt tegneprogram bygget med Java og JavaFX. Prosjektet ble utviklet som en skoleoppgave med hovedfokus på å anvende og demonstrere sentrale objektorienterte designprinsipper, spesielt **polymorfisme**.

---

## Om Prosjektet

Applikasjonen gir et tomt lerret hvor brukeren kan tegne ulike geometriske figurer som linjer, rektangler og sirkler, samt legge til tekst. Hver figur kan tegnes, fargelegges og flyttes rundt på lerretet i sanntid.

### Faglig Fokus & Designvalg

Kjernen i dette prosjektet er ikke selve tegneprogrammet, men **hvordan det er strukturert**.

*   **Abstrakt `Figur`-klasse:** En felles, abstrakt superklasse definerer kontrakten for alle figurer.
*   **Polymorfisme i Praksis:** Hovedapplikasjonen (`TegneGrensesnitt`) jobber kun med `Figur`-objekter. Den trenger ikke å kjenne til de spesifikke detaljene for hvordan en `Sirkel` eller et `Rektangel` oppdaterer seg. Ved å kalle den abstrakte `oppdater()`-metoden, sørger polymorfisme for at riktig implementasjon kjøres for hver subklasse.
*   **Klar ansvarsfordeling:** Hver figurklasse er ansvarlig for sin egen tegne- og flyttelogikk, noe som gjør koden ren og enkel å vedlikeholde og utvide.

### Teknologistack

*   **Språk:** Java
*   **GUI Rammeverk:** JavaFX
*   **Byggesystem:** Apache Maven

---

## Komme i Gang

### Forutsetninger
*   Java JDK 17 eller nyere
*   Apache Maven

### Kjøre Applikasjonen

1.  **Klon repositoriet:**
    ```bash
    git clone https://github.com/dittnavn/JavaFxMedPoly.git
    cd JavaFxMedPoly
    ```

2.  **Kompiler og kjør med Maven:**
    ```bash
    mvn clean javafx:run
    ```

---
**Forfatter:** Martin Pettersen