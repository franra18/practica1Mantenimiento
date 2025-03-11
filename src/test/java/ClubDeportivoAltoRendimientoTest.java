// Grupo formado por Francisco Ramírez Cañadas y Jorge Repullo Serrano

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clubdeportivo.ClubDeportivoAltoRendimiento;
import clubdeportivo.ClubException;

public class ClubDeportivoAltoRendimientoTest {

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
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        });
    }

    @Test
    @DisplayName("Constructor con valor incremento negativo lanza excepción")
    public void Constructor_IncrementoNegativo_LanzaExcepcion() {
        // Arrange
        String nombre = "UMA";
        int maximo = 1;
        double incremento = -10.0;

        // Assert
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        });
    }

    @Test
    @DisplayName("Constructor con valor tamaño y resto de datos válidos se crea correctamente")
    public void Constructor_TamanoYValoresValidos_CreaClubDeportivoAltoRendimientoCorrectamente() throws ClubException {
        // Arrange
        String nombre = "UMA";
        int tam = 1;
        int maximo = 1;
        double incremento = 10.0;

        // Act
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);

        // Assert
        assertNotNull(club);
        assertEquals(0, club.ingresos());
    }

    @Test
    @DisplayName("Constructor con valor tamaño y maximo negativo lanza excepción")
    public void Constructor_TamanoYMaximoNegativo_LanzaExcepcion() {
        // Arrange
        String nombre = "UMA";
        int tam = 1;
        int maximo = -1;
        double incremento = 10.0;

        // Assert
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        });
    }

    @Test
    @DisplayName("Constructor con valor tamaño e incremento negativo lanza excepción")
    public void Constructor_TamanoEIncrementoNegativo_LanzaExcepcion() {
        // Arrange
        String nombre = "UMA";
        int tam = 1;
        int maximo = 1;
        double incremento = -10.0;

        // Assert
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        });
    }

    // AÑADIR ACTIVIDAD
    @Test
    @DisplayName("Añadir actividad con datos válidos se añade correctamente")
    public void AnyadirActividad_DatosValidos_AniadeActividadCorrectamente() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("UMA", 5, 10.0);
        String[] datos = { "123A", "Natacion", "5", "3", "30.0" };

        // Act
        club.anyadirActividad(datos);

        // Assert
        assertEquals(2, club.plazasLibres("Natacion"));
        assertEquals(99, club.ingresos());
        assertTrue(club.toString().contains("Natacion"));
    }

    @Test
    @DisplayName("Añadir actividad con un array de datos con una longitud inferior a 5 lanza excepción")
    public void AnyadirActividad_ArrayDatosLongitudInferior5_LanzaExcepcion() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("UMA", 5, 10.0);
        String[] datos = { "123A", "Natacion", "5", "3" };

        // Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    @Test
    @DisplayName("Añadir actividad con numero de plazas mayor que el maximo permitido se establece al maximo permitido")
    public void AnyadirActividad_PlazasMayorQueMaximo_EstablecePlazasAlMaximo() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("UMA", 5, 10.0);
        String[] datos = { "123A", "Natacion", "6", "3", "30.0" };

        // Act
        club.anyadirActividad(datos);

        // Assert
        assertEquals(2, club.plazasLibres("Natacion"));
    }

    @Test
    @DisplayName("Añadir actividad con formato de número incorrecto lanza excepción")
    public void AnyadirActividad_FormatoNumeroIncorrecto_LanzaExcepcion() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("UMA", 5, 10.0);
        String[] datos = { "123A", "Natacion", "Hola", "3", "30.0" };

        // Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    // INGRESOS
    @Test
    @DisplayName("ingresos devuelve el total de ingresos del club correctamente")
    public void Ingresos_InvocarMetodo_DevuelveTotalIngresosCorrecto() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("UMA", 5, 10.0);
        String[] datos = { "123A", "Natacion", "5", "3", "30.0" };
        club.anyadirActividad(datos);

        // Act
        double ingresos = club.ingresos();

        // Assert
        assertEquals(99, ingresos);
    }
}
