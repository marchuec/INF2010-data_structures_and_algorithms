package Alphabet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;

public class Graph<ValueType> {
    private HashMap<ValueType, Integer> vertexMap;
    ArrayList<Vertex<ValueType>> vertices;
    private Integer nbVertex;

    Graph() {
        nbVertex = 0;
        vertices = new ArrayList<>();
        vertexMap = new HashMap<>();
    }

    private Vertex<ValueType> createVertex(ValueType value) {
        Integer pos = vertexMap.get(value);

        if (pos == null) {
            pos = nbVertex;
            vertexMap.put(value, pos);
            vertices.add(new Vertex(value));
            nbVertex++;
        }

        return vertices.get(pos);
    }

    /**
     * Creates an oriented edge between two vertex
     * @param src Vertex from which the oriented edge starts
     * @param dst Vertex to which the oriented edge points
     *
     * If the Vertex src or dst are not present in the graph they will be created
     * If an edge already exists between these vertex it will not add a second one
     */
    public void connect(ValueType src, ValueType dst) {
        //Get vertex or create them if they dont exists
        Vertex<ValueType> srcVertex = createVertex(src);
        Vertex<ValueType> dstVertex = createVertex(dst);

        //Connect vertex
        srcVertex.connect(dstVertex);
    }

    /**
     * ->> Fonction ajoutée au fichier
     * Creates a list ordered in topological order of the vertices of
     * the graph. The list contains the labels of the vertices.
     *
     * @return The ordered list of labels
     */
    public ArrayList<ValueType> topSort()
    {
        ArrayList<ValueType> list = new ArrayList<>();  // La liste ordonnée
        Queue<Vertex> q = new LinkedList<>();
        for (Vertex<ValueType> vertice : vertices) {
            if (vertice.indegree == 0)
                q.add(vertice);
        }
        while (!q.isEmpty()) {
            Vertex<ValueType> vertice = q.poll();
            list.add(vertice.label);
            for (Vertex<ValueType> neighbour : vertice.toVertex) {
                if (--neighbour.indegree == 0)    // Décrément après avoir ajouté le sommet précédent à la liste
                    q.add(neighbour);
            }
        }
        return list;
    }
}
