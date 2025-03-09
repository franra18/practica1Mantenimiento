import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clubdeportivo.ClubDeportivo;
import clubdeportivo.ClubException;

public class ClubDeportivoTest {

    // Hay que cambiar los nombres y los display a la forma que quiere el profesor

    // CONSTRUCTOR
    @Test
    @DisplayName("Constructor con nombre valido crea el club correctamente")
    public void Constructor_NombreValido_CreaClubCorrectamente() throws ClubException {
        // Arrange
        String nombreClub = "Polideportivo Municipal";
        
        // Act
        ClubDeportivo club = new ClubDeportivo(nombreClub);
        
        // Assert
        assertNotNull(club);
        assertEquals(nombreClub + " --> [  ]", club.toString());
        assertEquals(0, club.plazasLibres("Natación"));
        assertEquals(0, club.ingresos());
    }

    @Test
    @DisplayName("Constructor con nombre valido y numero de grupos valido crea el club correctamente")
    public void Constructor_NombreGruposValidos_CreaClubCorrectamente() throws ClubException {
        // Arrange
        String nombreClub = "Polideportivo Municipal";
        int tam = 7;
        
        // Act
        ClubDeportivo club = new ClubDeportivo(nombreClub, tam);
        
        // Assert
        assertNotNull(club);
        assertEquals(nombreClub + " --> [  ]", club.toString());
        assertEquals(0, club.plazasLibres("Natación"));
        assertEquals(0, club.ingresos());
    }

    @Test
    @DisplayName("Constructor con nombre vacio crea el club sin nombre")
    public void Constructor_NombreVacio_CreaClubSinNombre() throws ClubException {
        // Arrange
        String nombreClub = "";
        
        // Act
        ClubDeportivo club = new ClubDeportivo(nombreClub);
        
        // Assert
        assertNotNull(club);
        assertEquals(" --> [  ]", club.toString());
    }

    @Test
    @DisplayName("Constructor con nombre valido y número de grupos 0 lanza excepcion")
    public void Constructor_CeroGrupos_LanzaExcepcion() throws ClubException {
        // Arrange
        String nombreClub = "Club A";
        int tam = 0;
        
        // Assert
        assertThrows(ClubException.class, () -> {new ClubDeportivo(nombreClub, tam);});
    }

    @Test
    @DisplayName("Constructor con nombre valido y número de grupos negativo lanza excepcion")
    public void Constructor_GruposNegativo_LanzaExcepcion() throws ClubException {
        // Arrange
        String nombreClub = "Club A";
        int tam = -3;
        
        // Assert
        assertThrows(ClubException.class, () -> {new ClubDeportivo(nombreClub, tam);});
    }

    // ANYADIR_ACTIVIDAD
    @Test
    @DisplayName("Añadir actividad mediante datos tiene el comportamiento esperado")
    public void AnyadirActividad_DatosPorParametro_AnyadeCorrectamente() throws ClubException { // COMPLETAR
        //Arrange
        ClubDeportivo club = new ClubDeportivo("Club A");
        String[] datos = new String[5];
        datos[0] = "123B";
        datos[1] = "Futbol";
        datos[2] = "22";
        datos[3] = "10";
        datos[4] = "15.0";
        
        //Act
        club.anyadirActividad(datos);

        //Assert
        assertEquals(12, club.plazasLibres("Futbol"));
        assertEquals(150, club.ingresos());
        assertTrue(club.toString().contains("Futbol"));
    }

    @Test
    @DisplayName("Añadir actividad duplicada actualiza correctamente las plazas")
    public void AnyadirActividad_ActividadDuplicada_ActualizaPlazas() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("Club A");
        String[] datos1 = new String[5];
        datos1[0] = "123B";
        datos1[1] = "Futbol";
        datos1[2] = "22";
        datos1[3] = "10";
        datos1[4] = "15.0";
        
        String[] datos2 = new String[5];
        datos2[0] = "123B";
        datos2[1] = "Futbol";
        datos2[2] = "30"; // Cambiamos el número de plazas
        datos2[3] = "10";
        datos2[4] = "15.0";
        
        // Act
        club.anyadirActividad(datos1);
        club.anyadirActividad(datos2);

        // Assert
        assertEquals(20, club.plazasLibres("Futbol"));
        assertEquals(150.0, club.ingresos());
        assertTrue(club.toString().contains("Futbol"));
    }

    @Test
    @DisplayName("Añadir actividad con formato de datos incorrecto lanza excepción")
    public void AnyadirActividad_FormatoIncorrecto_LanzaExcepcion() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("Club A");
        String[] datosInvalidos = new String[5];
        datosInvalidos[0] = "123B";
        datosInvalidos[1] = "Futbol";
        datosInvalidos[2] = "invalid"; // Esto es un dato inválido
        datosInvalidos[3] = "10";
        datosInvalidos[4] = "15.0";

        // Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(datosInvalidos));
    }
}
