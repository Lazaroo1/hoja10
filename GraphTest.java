import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Verifica que el grafo funcione como se espera
 */
public class GraphTest {
    /**
     * Prueba si al añadir un borde la distancia se actualiza
     */
    @Test
    void testAddEdge() {
        Graph g = new Graph(List.of('A', 'B'));
        g.addEdge('A', 'B', 10);
        assertEquals(10, g.floydWarshall()[0][1]);
    }

    /**
     * Prueba si al eliminar un borde la distancia vuelve a ser infinita
     */
    @Test
    void testRemoveEdge() {
        Graph g = new Graph(List.of('A', 'B'));
        g.addEdge('A', 'B', 10);
        g.removeEdge('A', 'B');
        assertTrue(g.floydWarshall()[0][1] > 1000); // INF es mayor a 1000
    }
}