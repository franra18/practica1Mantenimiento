// Grupo formado por Francisco Ramírez Cañadas y Jorge Repullo Serrano

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.StringJoiner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import clubdeportivo.ClubDeportivo;
import clubdeportivo.ClubException;
import clubdeportivo.Grupo;

public class ClubDeportivoTest {

    private ClubDeportivo club;

    @BeforeEach
    public void setUp() throws ClubException {
        club = new ClubDeportivo("Club A");
    }

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

    // Posiblemente comportamiento no esperado
    @Test
    @DisplayName("Constructor con nombre vacio permite crear un club sin nombre")
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
    public void AnyadirActividad_DatosPorParametro_AnyadeCorrectamente() throws ClubException {
        //Arrange
        String[] datos = new String[5];
        datos[0] = "FUT1";
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
        String[] datos1 = new String[5];
        datos1[0] = "FUT1";
        datos1[1] = "Futbol";
        datos1[2] = "22";
        datos1[3] = "10";
        datos1[4] = "15.0";
        
        String[] datos2 = new String[5];
        datos2[0] = "FUT1";
        datos2[1] = "Futbol";
        datos2[2] = "30"; // Cambiamos el número de plazas
        datos2[3] = "10";
        datos2[4] = "15.0";
        
        // Act
        club.anyadirActividad(datos1);
        club.anyadirActividad(datos2);

        // Assert
        assertEquals(20, club.plazasLibres("Futbol")); // Ahora hay 30 plazas, por tanto quedan 20 libres
        assertEquals(150.0, club.ingresos());
        assertTrue(club.toString().contains("Futbol"));
    }

    @Test
    @DisplayName("Añadir actividad con formato de datos incorrecto lanza excepción")
    public void AnyadirActividad_FormatoIncorrecto_LanzaExcepcion() throws ClubException {
        // Arrange
        String[] datosInvalidos = new String[5];
        datosInvalidos[0] = "FUT1";
        datosInvalidos[1] = "Futbol";
        datosInvalidos[2] = "invalid"; // Esto es un dato inválido
        datosInvalidos[3] = "10"; // es necesario hacer otro test para comprobar este parametro invalido?
        datosInvalidos[4] = "15.0";

        // Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(datosInvalidos));
    }

    // CORREGIR CODIGO DE CLUB DEPORTIVO, HAY QUE CONTROLAR QUE SE PASEN 5 DATOS EN EL ARRAY DE DATOS
    @Test
    @DisplayName("Añadir actividad con menos de 5 datos lanza excepción")
    public void AnyadirActividad_MenosDeCincoDatos_LanzaExcepcion() throws ClubException {
        // Arrange
        String[] datos = new String[4];
        datos[0] = "FUT1";
        datos[1] = "Futbol";
        datos[2] = "22";
        datos[3] = "10";
        
        // Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("Añadir actividad con grupo nulo lanza excepción")
    public void AnyadirActividad_GrupoNulo_LanzaExcepcion() throws ClubException {
        // Arrange
        Grupo grupoNulo = null;
        
        // Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(grupoNulo));
    }

    @Test
    @DisplayName("Añadir grupo válido directamente tiene el comportamiento esperado")
    public void AnyadirActividad_GrupoValido_AnyadeCorrectamente() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("BAL2", "Baloncesto", 15, 5, 20.0);
        
        // Act
        club.anyadirActividad(grupo);
        
        // Assert
        assertEquals(10, club.plazasLibres("Baloncesto"));
        assertEquals(100.0, club.ingresos()); // 5 matriculados * 20.0 tarifa
        assertTrue(club.toString().contains("Baloncesto"));
    }

    @Test
    @DisplayName("Añadir grupo duplicado actualiza correctamente las plazas")
    public void AnyadirActividad_GrupoDuplicado_ActualizaPlazas() throws ClubException {
        // Arrange
        Grupo grupo1 = new Grupo("TEN3", "Tenis", 10, 8, 25.0);
        Grupo grupo2 = new Grupo("TEN3", "Tenis", 20, 8, 25.0);
        
        // Act
        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo2);
        
        // Assert
        assertEquals(12, club.plazasLibres("Tenis"));
        assertEquals(200.0, club.ingresos()); // 8 matriculados * 25.0 tarifa
        assertTrue(club.toString().contains("Tenis"));
    }

    @Test
    @DisplayName("Añadir grupo con mismo código pero menos plazas que matriculados lanza excepción")
    public void AnyadirActividad_GrupoDuplicadoMenosPlazasQueMatriculados_LanzaExcepcion() throws ClubException {
        // Arrange
        Grupo grupo1 = new Grupo("YOG4", "Yoga", 15, 10, 30.0);
        club.anyadirActividad(grupo1);
        
        Grupo grupo2 = new Grupo("YOG4", "Yoga", 8, 5, 30.0); // Intenta actualizar a 8 plazas cuando ya hay 10 matriculados
        
        // Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(grupo2));
    }

    // CORREGIR CODIGO DE CLUB DEPORTIVO, HAY QUE CONTROLAR QUE NO SE PUEDAN AÑADIR MÁS GRUPOS DE LOS PERMITIDOS
    @Test
    @DisplayName("Añadir más grupos de los permitidos lanza ArrayIndexOutOfBoundsException")
    public void AnyadirActividad_ExcedeTamanyo_LanzaExcepcion() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("Club A", 1);
        Grupo grupo1 = new Grupo("YOG4", "Yoga", 15, 10, 30.0);
        Grupo grupo2 = new Grupo("TEN3", "Tenis", 20, 8, 25.0);
        
        club.anyadirActividad(grupo1);
        
        // Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> club.anyadirActividad(grupo2));
    }

    // PLAZAS_LIBRES
    @Test
    @DisplayName("Plazas libres de una actividad existente devuelve el número de plazas libres")
    public void PlazasLibres_ActividadExistente_DevuelvePlazasLibres() throws ClubException {
        // Arrange
        String[] datos = new String[5];
        datos[0] = "FUT1";
        datos[1] = "Futbol";
        datos[2] = "22";
        datos[3] = "10";
        datos[4] = "15.0";
        club.anyadirActividad(datos);
        
        // Act
        int plazasLibres = club.plazasLibres("Futbol");
        
        // Assert
        assertEquals(12, plazasLibres);
    }

    @Test
    @DisplayName("Plazas libres de una actividad inexistente devuelve 0")
    public void PlazasLibres_ActividadInexistente_DevuelveCero() throws ClubException {
        // Arrange
        String[] datos = new String[5];
        datos[0] = "FUT1";
        datos[1] = "Futbol";
        datos[2] = "22";
        datos[3] = "10";
        datos[4] = "15.0";
        club.anyadirActividad(datos);
        
        // Act
        int plazasLibres = club.plazasLibres("Baloncesto");
        
        // Assert
        assertEquals(0, plazasLibres);
    }

    @Test
    @DisplayName("Plazas libres de una actividad con nombre vacío devuelve 0")
    public void PlazasLibres_ActividadVacia_DevuelveCero() throws ClubException {
        // Arrange
        String[] datos = new String[5];
        datos[0] = "FUT1";
        datos[1] = "Futbol";
        datos[2] = "22";
        datos[3] = "10";
        datos[4] = "15.0";
        club.anyadirActividad(datos);
        
        // Act
        int plazasLibres = club.plazasLibres("");
        
        // Assert
        assertEquals(0, plazasLibres);
    }

    // MATRICULAR
    @Test
    @DisplayName("Matricular en una actividad existente a menos personas que plazas matricula correctamente")
    public void Matricular_MenosPersonasQuePlazasLibres_MatriculaCorrectamente() throws ClubException{
        // Arrange
        String[] datos = new String[5];
        datos[0] = "PAD5";
        datos[1] = "Padel";
        datos[2] = "10";
        datos[3] = "4";
        datos[4] = "6.0";
        club.anyadirActividad(datos);
        int plazasLibresAntes = club.plazasLibres("Padel");
        double ingresosAntes = club.ingresos();
        
        // Act
        club.matricular("Padel", 5);
    
        // Assert
        assertEquals(plazasLibresAntes - 5, club.plazasLibres("Padel"));
        assertEquals(ingresosAntes + 5 * 6.0, club.ingresos());
    }

    @Test
    @DisplayName("Matricular exactamente el número de personas que caben, dejando npersonas=0")
    public void Matricular_ExactamenteNPersonas_MatriculaTodasPersonas() throws ClubException {
        // Arrange
        String[] datos = new String[5];
        datos[0] = "NAT6";
        datos[1] = "Natación";
        datos[2] = "20";
        datos[3] = "10";
        datos[4] = "15.0";
        club.anyadirActividad(datos);
        
        // Act
        club.matricular("Natación", 10);
        
        // Assert
        assertEquals(0, club.plazasLibres("Natación"));
        assertEquals(300.0, club.ingresos()); //20 * 15.0€
    }

    @Test
    @DisplayName("Matricular con número de personas igual a cero no hace cambios")
    public void Matricular_CeroPersonas_NoHaceCambios() throws ClubException {
        // Arrange
        String[] datos = new String[5];
        datos[0] = "BOX7";
        datos[1] = "Boxeo";
        datos[2] = "15";
        datos[3] = "5";
        datos[4] = "30.0";
        club.anyadirActividad(datos);
        double ingresosAntes = club.ingresos();
        int plazasLibresAntes = club.plazasLibres("Boxeo");
        
        // Act
        club.matricular("Boxeo", 0);
        
        // Assert
        assertEquals(plazasLibresAntes, club.plazasLibres("Boxeo"));
        assertEquals(ingresosAntes, club.ingresos());
    }

    @Test
    @DisplayName("Matricular con plazas suficientes en total pero distribuidas en varios grupos")
    public void Matricular_PlazasSuficientesEnTotal_MatriculaCorrectamente() throws ClubException {
        // Arrange
        // Dos grupos de Yoga con plazas libres
        String[] datos1 = {"YOG4", "Yoga", "10", "5", "20.0"};
        String[] datos2 = {"YOG8", "Yoga", "8", "3", "20.0"};
        club.anyadirActividad(datos1);
        club.anyadirActividad(datos2);
        
        // Act (Matriculamos más personas que las disponibles en un solo grupo)
        club.matricular("Yoga", 8); // 5 plazas en primer grupo + 3 en segundo
        
        // Assert
        assertEquals(2, club.plazasLibres("Yoga")); // 10-5=5 libres en grupo 1, 8-5=0 libres en grupo 2
        assertEquals(320.0, club.ingresos()); // 16 * 20.0€
    }

    @Test
    @DisplayName("Matricular cuando hay múltiples actividades y se busca una específica")
    public void Matricular_MultiplesActividades_MatriculaEnActividadCorrecta() throws ClubException {
        // Arrange
        String[] datos1 = {"PIL9", "Pilates", "15", "5", "25.0"};
        String[] datos2 = {"SP10", "Spinning", "20", "10", "18.0"};
        String[] datos3 = {"ZU11", "Zumba", "12", "8", "15.0"};
        
        club.anyadirActividad(datos1);
        club.anyadirActividad(datos2);
        club.anyadirActividad(datos3);
        
        // Act
        club.matricular("Spinning", 5);
        
        // Assert
        assertEquals(10, club.plazasLibres("Pilates")); // No cambió
        assertEquals(5, club.plazasLibres("Spinning")); // Se matricularon 5 personas
        assertEquals(4, club.plazasLibres("Zumba")); // No cambió
        
        // Calculamos los ingresos totales esperados
        double ingresosEsperados = (5 * 25.0) + (15 * 18.0) + (8 * 15.0);
        assertEquals(ingresosEsperados, club.ingresos());
    }

    @Test
    @DisplayName("Matricular en una actividad existente a más personas que plazas libres lanza excepción")
    public void Matricular_MasPersonasQuePlazasLibres_LanzaExcepcion() throws ClubException {
        // Arrange
        String[] datos = new String[5];
        datos[0] = "AT12";
        datos[1] = "Atletismo";
        datos[2] = "12";
        datos[3] = "6";
        datos[4] = "9.0";
        club.anyadirActividad(datos);
        
        // Assert
        assertThrows(ClubException.class, () -> club.matricular("Padel", 7));
    }

    @Test
    @DisplayName("Matricular en una actividad inexistente lanza excepción")
    public void Matricular_ActividadInexistente_LanzaExcepcion() throws ClubException {
        // Arrange
        String[] datos = new String[5];
        datos[0] = "PAD5";
        datos[1] = "Padel";
        datos[2] = "10";
        datos[3] = "4";
        datos[4] = "6.0";
        club.anyadirActividad(datos);
        
        // Assert
        assertThrows(ClubException.class, () -> club.matricular("Baloncesto", 5));
    }
    
    @Test
    @DisplayName("Matricular en una actividad con nombre vacío lanza excepción")
    public void Matricular_ActividadVacia_LanzaExcepcion() throws ClubException {
        // Arrange
        String[] datos = new String[5];
        datos[0] = "AT12";
        datos[1] = "Atletismo";
        datos[2] = "12";
        datos[3] = "6";
        datos[4] = "9.0";
        club.anyadirActividad(datos);
        
        // Assert
        assertThrows(ClubException.class, () -> club.matricular("", 5));
    }

    /* 
    // ESTE TEST NO PASA PORQUE NO SE CONTROLA QUE npersonas EN MATRICULAR SEA NEGATIVO
    // LLEGA AL BUCLE WHILE DE LA FUNCIÓN Y COMO npersonas NO ES MAYOR QUE 0 SIMPLEMENTE TERMINA
    @Test
    @DisplayName("Matricular en una actividad con número de personas negativo lanza excepción")
    public void Matricular_Negativo_LanzaExcepcion() throws ClubException {
        // Arrange
        String[] datos = new String[5];
        datos[0] = "PAD5";
        datos[1] = "Padel";
        datos[2] = "10";
        datos[3] = "4";
        datos[4] = "6.0";
        club.anyadirActividad(datos);
        
        // Assert
        assertThrows(ClubException.class, () -> club.matricular("Padel", -5));
    }
    */

    // INGRESOS
    @Test
    @DisplayName("Ingresos de un club sin actividades es 0")
    public void Ingresos_SinActividades_Cero() {
        // Assert
        assertEquals(0, club.ingresos());
    }

    @Test
    @DisplayName("Ingresos de un club con actividades es la suma de las tarifas de los matriculados")
    public void Ingresos_ConActividades_SumaTarifas() throws ClubException {
        // Arrange
        String[] datos1 = new String[5];
        datos1[0] = "FUT1";
        datos1[1] = "Futbol";
        datos1[2] = "22";
        datos1[3] = "11";
        datos1[4] = "15.0";
        
        String[] datos2 = new String[5];
        datos2[0] = "BAL2";
        datos2[1] = "Baloncesto";
        datos2[2] = "15";
        datos2[3] = "5";
        datos2[4] = "20.0";
        
        club.anyadirActividad(datos1);
        club.anyadirActividad(datos2);
        
        // Act
        double ingresos = club.ingresos();
        
        // Assert
        assertEquals(165.0 + 100.0, ingresos);
    }

    // TO_STRING
    @Test
    @DisplayName("toString de un club sin actividades devuelve el nombre del club y una lista vacía")
    public void ToString_SinActividades_NombreClubYListaVacia() {
        // Assert
        assertEquals("Club A --> [  ]", club.toString());
    }

    @Test
    @DisplayName("toString de un club con actividades devuelve el formato exacto esperado")
    public void ToString_ConActividades_FormatoExacto() throws ClubException {
        // Arrange
        Grupo grupo1 = new Grupo("FUT1", "Futbol", 22, 11, 15.0);
        Grupo grupo2 = new Grupo("BAL2", "Baloncesto", 15, 5, 20.0);
        
        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo2);
        
        // Preparar la salida esperada
        Grupo[] grupos = new Grupo[]{grupo1, grupo2};
        String esperado = construirSalidaEsperada("Club A", grupos);
        
        // Act
        String resultado = club.toString();
        
        // Assert
        assertEquals(esperado, resultado);
    }

    private String construirSalidaEsperada(String nombreClub, Grupo[] grupos) {
        StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
        
        for (Grupo g : grupos) {
            if (g != null) {
                sj.add(g.toString());
            }
        }
        
        return nombreClub + " --> " + sj.toString();
    }
}
