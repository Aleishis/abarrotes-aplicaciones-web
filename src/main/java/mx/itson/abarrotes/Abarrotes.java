package mx.itson.abarrotes;

import javax.swing.SwingUtilities;
import mx.itson.abarrotes.vistas.VentanaPrincipal;

/**
 * Punto de inicio de la aplicacion.
 * Antes de ejecutar, la API de Django debe estar corriendo en http://localhost:8000
 *
 * @author alelo
 */
public class Abarrotes {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
