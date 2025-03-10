import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clubdeportivo.ClubDeportivo;
import clubdeportivo.ClubDeportivoAltoRendimiento;
import clubdeportivo.ClubException;

public class ClubDeportivoAltoRendimientoTest {
    /*
     * Para nombrar un test, debo seguir la estructura
     * [MethodUnderTest]_[Scenario]_[ExpectedResult]
     * Scenario es la condición sobre la que se prueba
     * Ejemplo para la suma de dos números en el método Suma: public void
     * Sum_TwoNumbers_ReturnsSum()
     * 
     * Los tests deben seguir el patron AAA:
     * Arrange: preparar el estado del código a probar
     * · Crear objetos
     * · Inicializar variables
     * Act: ejecutar la lógica del sistema a probar
     * Assert: ejecutar las pruebas y comprobar si hay errores
     * 
     * En el assert, primero aparece el valor esperado por la prueba, después el
     * calculado por el código;
     * si todo va bien deben ser iguales
     * 
     */

    // CONSTRUCTOR
    @Test
    @DisplayName("Constructor con valores válidos se crea correctamente")
    public void Constructor_ValoresValidos_CreaClubDeportivoAltoRendimientoCorrectamente() throws ClubException {
        // Arrange
        String nombre = "UMA";
        int maximo = 1;
        double incremento = 10.0;

        // Act
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);

        // Assert
        assertNotNull(club);
        assertEquals(0, club.ingresos());
    }

    @Test
    @DisplayName("Constructor con valor maximo negativo lanza excepción")
    public void Constructor_MaximoNegativo_LanzaExcepcion() {
        // Arrange
        String nombre = "UMA";
        int maximo = -1;
        double incremento = 10.0;

        // Assert
        assertThrows(ClubException.class, () -> {new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);});        
    }

    @Test
    @DisplayName("Constructor con valor incremento negativo lanza excepción")
    public void Constructor_IncrementoNegativo_LanzaExcepcion() {
        // Arrange
        String nombre = "UMA";
        int maximo = 1;
        double incremento = -10.0;

        // Assert
        assertThrows(ClubException.class, () -> {new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);});        
    }

    // FALTA A PARTIR DEL PRIMER CONSTRUCTOR
}
