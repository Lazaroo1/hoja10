import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ciudades con datos reales (distancias en km según google maps):
        // A: Ciudad de Guatemala
        // B: Zacapa (144 km desde A)
        // C: Chiquimula (23 km desde B)
        // D: Quetzaltenango (200 km desde A)
        // E: Cobán (219 km desde A)
        List<Character> cities = List.of('A', 'B', 'C', 'D', 'E');
        Graph graph = new Graph(cities);

        // Conexiones reales (dirigidas):
        graph.addEdge('A', 'B', 144);    // Guatemala -> zacapa
        graph.addEdge('B', 'A', 144);    // Zacapa -> guatemala (bidireccional)
        graph.addEdge('B', 'C', 23);     // zacapa -> Chiquimula
        graph.addEdge('C', 'B', 23);     // Chiquimula -> Zacapa
        graph.addEdge('A', 'D', 200);    // Guatemala -> quetzaltenango
        graph.addEdge('D', 'A', 200);    // quetzaltenango -> Guatemala
        graph.addEdge('A', 'E', 219);    // guatemala -> cobán
        graph.addEdge('E', 'A', 219);    // Cobán -> Guatemala
        graph.addEdge('D', 'E', 160);    // Quetzaltenango -> coban (ruta falsa)

        int[][] dist = graph.floydWarshall();
        System.out.println("\nMatriz de distancias mínimas:");
        for (int[] row : dist) {
            System.out.println(Arrays.toString(row));
        }

        char center = graph.findCenter();
        System.out.println("La ciudad central es: " + center); 
    }
}