import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clubdeportivo.ClubException;
import clubdeportivo.Grupo;

public class GrupoTest {
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
    @DisplayName("Constructor con código, actividad, número de plazas, matriculados y tarifa válidos crea el grupo correctamente")
    public void Constructor_DatosValidos_CreaGrupoCorrectamente() throws ClubException {
        // Arrange
        String codigo = "123A";
        String actividad = "Zumba";
        int nplazas = 15;
        int matriculados = 15;
        double tarifa = 25.45;

        // Act
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        // Assert
        assertNotNull(grupo);
        assertEquals(codigo, grupo.getCodigo());
        assertEquals(actividad, grupo.getActividad());
        assertEquals(nplazas, grupo.getPlazas());
        assertEquals(matriculados, grupo.getMatriculados());
        assertEquals(tarifa, grupo.getTarifa());
    }

    @Test
    @DisplayName("Constructor con dato numérico nplazas incorrecto lanza una excepción")
    public void Constructor_DatoNPlazasInvalido_LanzaExcepcion() throws ClubException{
        // Arrange
        String codigo = "123A";
        String actividad = "Zumba";
        int nplazas = -15; // Número de plazas negativo
        int matriculados = 15;
        double tarifa = 25.45;

        // Assert
        assertThrows(ClubException.class, () -> {new Grupo(codigo, actividad, nplazas, matriculados, tarifa);});
    }

    @Test
    @DisplayName("Constructor con dato numérico matriculados incorrecto lanza una excepción")
    public void Constructor_DatoMatriculadosInvalido_LanzaExcepcion() throws ClubException{
        // Arrange
        String codigo = "123A";
        String actividad = "Zumba";
        int nplazas = 15; 
        int matriculados = -15; // Matriculados negativo
        double tarifa = 25.45;

        // Assert
        assertThrows(ClubException.class, () -> {new Grupo(codigo, actividad, nplazas, matriculados, tarifa);});
    }

    @Test
    @DisplayName("Constructor con dato numérico tarifa incorrecto lanza una excepción")
    public void Constructor_DatoTarifaInvalido_LanzaExcepcion() throws ClubException{
        // Arrange
        String codigo = "123A";
        String actividad = "Zumba";
        int nplazas = 15; 
        int matriculados = 15; 
        double tarifa = -25.45; // Tarifa negativa

        // Assert
        assertThrows(ClubException.class, () -> {new Grupo(codigo, actividad, nplazas, matriculados, tarifa);});
    }

    @Test
    @DisplayName("Constructor con número de matriculados mayor que número de plazas lanza una excepción")
    public void Constructor_MatriculadosMayorQuePlazas_LanzaExcepcion() throws ClubException{
        // Arrange
        String codigo = "123A";
        String actividad = "Zumba";
        int nplazas = 15;
        int matriculados = 20; // Matriculados mayor que plazas
        double tarifa = 25.45;

        // Assert
        assertThrows(ClubException.class, () -> {new Grupo(codigo, actividad, nplazas, matriculados, tarifa);});
    }

    // GETTERS
    @Test
    @DisplayName("getCodigo devuelve el código del grupo correctamente")
    public void getCodigo_InvocarMetodo_DevuelveCodigoCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 15, 25.45);

        // Act
        String codigoObtenido = grupo.getCodigo();

        // Assert
        assertEquals("123A", codigoObtenido);
    }

    @Test
    @DisplayName("getActividad devuelve la actividad del grupo correctamente")
    public void getActividad_InvocarMetodo_DevuelveActividadCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 15, 25.45);

        // Act
        String actividadObtenida = grupo.getActividad();

        // Assert
        assertEquals("Zumba", actividadObtenida);
    }

    @Test
    @DisplayName("getPlazas devuelve el número de plazas del grupo correctamente")
    public void getPlazas_InvocarMetodo_DevuelvePlazasCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 15, 25.45);

        // Act
        int plazasObtenidas = grupo.getPlazas();

        // Assert
        assertEquals(15, plazasObtenidas);
    }

    @Test
    @DisplayName("getMatriculados devuelve el número de matriculados del grupo correctamente")
    public void getMatriculados_InvocarMetodo_DevuelveMatriculadosCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 15, 25.45);

        // Act
        int matriculadosObtenidos = grupo.getMatriculados();

        // Assert
        assertEquals(15, matriculadosObtenidos);
    }

    @Test
    @DisplayName("getTarifa devuelve la tarifa del grupo correctamente")
    public void getTarifa_InvocarMetodo_DevuelveTarifaCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 15, 25.45);

        // Act
        double tarifaObtenida = grupo.getTarifa();

        // Assert
        assertEquals(25.45, tarifaObtenida);
    }

    // PLAZAS LIBRES
    @Test
    @DisplayName("plazasLibres devuelve el número de plazas libres correctamente")
    public void plazasLibres_InvocarMetodo_DevuelvePlazasLibresCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Act
        int plazasLibres = grupo.plazasLibres();

        // Assert
        assertEquals(5, plazasLibres);
    }

    // ACTUALIZAR PLAZAS
    @Test
    @DisplayName("actualizarPlazas con número de plazas válido actualiza el número de plazas correctamente")
    public void actualizarPlazas_NumeroPlazasValido_ActualizaPlazasCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Act
        grupo.actualizarPlazas(20);
        int numeroPlazas = grupo.getPlazas();

        // Assert
        assertEquals(20, numeroPlazas);
    }

    @Test
    @DisplayName("actualizarPlazas con número de plazas negativo lanza una excepción")
    public void actualizarPlazas_NumeroPlazasNegativo_LanzaExcepcion() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Assert
        assertThrows(ClubException.class, () -> {grupo.actualizarPlazas(-20);});
    }

    @Test
    @DisplayName("actualizarPlazas con número de plazas menor que matriculados lanza una excepción")
    public void actualizarPlazas_NumeroPlazasMenorQueMatriculados_LanzaExcepcion() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Assert
        assertThrows(ClubException.class, () -> {grupo.actualizarPlazas(5);});
    }

    // MATRICULAR
    @Test
    @DisplayName("matricular con número de matriculados válido actualiza el número de matriculados correctamente")
    public void matricular_NumeroMatriculadosValido_ActualizaMatriculadosCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Act
        grupo.matricular(5);
        int numeroMatriculados = grupo.getMatriculados();

        // Assert
        assertEquals(15, numeroMatriculados);
    }

    @Test
    @DisplayName("matricular con número de matriculados negativo lanza una excepción")
    public void matricular_NumeroMatriculadosNegativo_LanzaExcepcion() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Assert
        assertThrows(ClubException.class, () -> {grupo.matricular(-5);});
    }

    @Test
    @DisplayName("matricular con número de matriculados mayor que plazas libres lanza una excepción")
    public void matricular_NumeroMatriculadosMayorQuePlazasLibres_LanzaExcepcion() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Assert
        assertThrows(ClubException.class, () -> {grupo.matricular(10);});
    }

    // TO STRING
    @Test
    @DisplayName("toString devuelve la representación del grupo correctamente")
    public void toString_InvocarMetodo_DevuelveRepresentacionCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Act
        String representacion = grupo.toString();

        // Assert
        assertEquals("(123A - Zumba - 25.45 euros - P:15 - M:10)", representacion);
    }

    // EQUALS
    @Test
    @DisplayName("equals con dos grupos iguales devuelve true")
    public void equals_DosGruposIguales_DevuelveTrue() throws ClubException {
        // Arrange
        Grupo grupo1 = new Grupo("123A", "Zumba", 15, 10, 25.45);
        Grupo grupo2 = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Act
        boolean iguales = grupo1.equals(grupo2);

        // Assert
        assertTrue(iguales);
    }

    @Test
    @DisplayName("equals con un objeto que no es Grupo devuelve false")
    public void equals_ObjetoNoGrupo_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);
        Object objeto = new Object();

        // Act
        boolean iguales = grupo.equals(objeto);

        // Assert
        assertFalse(iguales);
    }

    @Test
    @DisplayName("equals entre dos grupos con códigos diferentes devuelve false")
    public void equals_DosGruposConCodigosDiferentes_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo1 = new Grupo("123A", "Zumba", 15, 10, 25.45);
        Grupo grupo2 = new Grupo("456B", "Zumba", 15, 10, 25.45);

        // Act
        boolean iguales = grupo1.equals(grupo2);

        // Assert
        assertFalse(iguales);
    }

    @Test
    @DisplayName("equals entre dos grupos con actividades diferentes devuelve false")
    public void equals_DosGruposConActividadesDiferentes_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo1 = new Grupo("123A", "Zumba", 15, 10, 25.45);
        Grupo grupo2 = new Grupo("123A", "Pilates", 15, 10, 25.45);

        // Act
        boolean iguales = grupo1.equals(grupo2);

        // Assert
        assertFalse(iguales);
    }

    // HASHCODE
    @Test
    @DisplayName("hashCode devuelve el código hash del grupo correctamente")
    public void hashCode_InvocarMetodo_DevuelveCodigoHashCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("123A", "Zumba", 15, 10, 25.45);

        // Act
        int codigoHash = grupo.hashCode();
        int codigoHashEsperado = "123A".toUpperCase().hashCode() + "Zumba".toUpperCase().hashCode();

        // Assert
        assertEquals(codigoHashEsperado, codigoHash);
    }

}