import java.util.*;

/**
 * Representa un grafo dirigido con funcionalidad para calcular caminos más cortos
 * y encontrar el centro del grafo usando el algoritmo de Floyd-Warshall
 */
public class Graph {
    private int[][] adjacencyMatrix;
    private Map<Character, Integer> nodeIndex;
    private char[] nodes;
    private static final int INF = Integer.MAX_VALUE / 2; // Valor grande para infinito

    /**
     * Construye un grafo a partir de una lista de nodos
     * @param vertices Lista de ciudades 
     */
    public Graph(List<Character> vertices) {
        int size = vertices.size();
        adjacencyMatrix = new int[size][size];
        nodeIndex = new HashMap<>();
        nodes = new char[size];
        
        // Inicializa la matriz y el mapeo de nodos
        for (int i = 0; i < size; i++) {
            nodes[i] = vertices.get(i);
            nodeIndex.put(nodes[i], i);
            Arrays.fill(adjacencyMatrix[i], INF);
            adjacencyMatrix[i][i] = 0;
        }
    }

    /**
     * Añade una conexión entre dos ciudades con una distancia (peso)
     * @param source ciudad de origen 
     * @param destination Ciudad del destino 
     * @param weight distancia en kilómetros
     */
    public void addEdge(char source, char destination, int weight) {
        int i = nodeIndex.get(source);
        int j = nodeIndex.get(destination);
        adjacencyMatrix[i][j] = weight;
    }

    /**
     * Elimina una conexión entre dos ciudades
     * @param source ciudad de origen
     * @param destination Ciudad del destino 
     */
    public void removeEdge(char source, char destination) {
        int i = nodeIndex.get(source);
        int j = nodeIndex.get(destination);
        adjacencyMatrix[i][j] = INF;
    }

    /**
     * Calcula las distancias más cortas entre todas las ciudades
     * @return Matriz con las distancias mínimas
     */
    public int[][] floydWarshall() {
        int n = nodes.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            dist[i] = Arrays.copyOf(adjacencyMatrix[i], n);
        }

        // algoritmo Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

    /**
     * Encuentra la ciudad que está más cerca de la ciudad más lejana
     * @return letra de la ciudad central
     */
    public char findCenter() {
        int[][] dist = floydWarshall();
        int minEccentricity = INF;
        char center = nodes[0];

        // Calcula la excentricidad de cada columna
        for (int j = 0; j < nodes.length; j++) {
            int max = 0;
            for (int i = 0; i < nodes.length; i++) {
                if (dist[i][j] > max) max = dist[i][j];
            }
            if (max < minEccentricity) {
                minEccentricity = max;
                center = nodes[j];
            }
        }
        return center;
    }
}