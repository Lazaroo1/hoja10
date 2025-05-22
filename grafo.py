import networkx as nx

# Creamos el grafo dirigido
G = nx.DiGraph()

# Definimos conexiones bidireccionales y distancias en km reales
G.add_weighted_edges_from([
    ('A', 'B', 144),   # Guatemala a Zacapa
    ('B', 'A', 144),   # Zacapa a Guatemala
    ('B', 'C', 23),    # Zacapa a Chiquimula
    ('C', 'B', 23),    # Chiquimula a Zacapa
    ('A', 'D', 200),   # Guatemala a Quetzaltenango
    ('D', 'A', 200),   # Quetzaltenango a Guatemala
    ('A', 'E', 219),   # Guatemala a Cobán
    ('E', 'A', 219),   # Cobán a Guatemala
    ('D', 'E', 160),   # Quetzaltenango a Cobán
])

# Calcular distancias mínimas
predecesores, distancias = nx.floyd_warshall_predecessor_and_distance(G)

excentricidades = {}
for nodo in G.nodes():
    max_dist = max(distancias[origen][nodo] for origen in G.nodes() if origen != nodo)
    excentricidades[nodo] = max_dist

centro = min(excentricidades, key=excentricidades.get)
print("Centro del grafo: " +centro)

print("\nExcentricidades:")
for ciudad, ecc in excentricidades.items():
    print(f"{ciudad}: {ecc} km")