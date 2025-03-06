import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clubdeportivo.ClubDeportivo;
import clubdeportivo.ClubException;
import clubdeportivo.Grupo;

public class ClubDeportivoTest {

    // Hay que cambiar los nombres y los display a la forma que quiere el profesor

    @Test
    @DisplayName("Test 01: ClubDeportivo constructor nombre")
    public void ClubDeportivo_ValidName_ClubSuccessfullyCreated() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("Club A");        
        //Assert
        assertNotNull(club);
    }

    @Test
    @DisplayName("Test 02: ClubDeportivo constructor nombre y tamaño")
    public void testClubDeportivoConstructor2() throws ClubException {      
        //Assert
        assertThrows(ClubException.class, () -> {new ClubDeportivo("Club A", 0);});
    }

    @Test
    @DisplayName("Test 03: metodo agregar con grupo")
    public void testAnyadirActividadGrupo() throws ClubException { // COMPLETAR
        //Arrange
        ClubDeportivo club = new ClubDeportivo("Club A");
        Grupo grupo = new Grupo("123A","Pilates",10,10,25.0);
        //Act
        club.anyadirActividad(grupo);
        //Assert

    }

    @Test
    @DisplayName("Test 04: metodo agregar") // EMPEZAR 
    public void testAnyadirActividadDatos() throws ClubException{
        
    }
}
