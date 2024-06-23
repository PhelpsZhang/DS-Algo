package com.ph.Structure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ph.Entity.Person;

public class GraphAdjList<T extends Comparable<T>> {
    
    // key: vertex
    // value: All adjacency vertex to this vertex.
    private Map<Vertex<T>, List<Vertex<T>>> adjList;

    public GraphAdjList(Vertex<T>[][] edges) {
        this.adjList = new HashMap<>();
        // 添加顶点和边。vertex[][]实际上是所有边的集合。比如vertex[x][0]和vertex[x][1]分别存了两个vertex，那么它们俩中间有一条edge。
        // 类似adjMatrix那里，edges[][]也只是储存了有边的对儿，即(v1, v2)表示v1和v2间有条边。
        for (Vertex<T>[] edge : edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    public int size() {
        return adjList.size();
    }

    public void addEdge(Vertex<T> vet1, Vertex<T> vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2) 
            throw new IllegalArgumentException();
        adjList.get(vet1).add(vet2);
        adjList.get(vet2).add(vet1);
    }

    public void addVertex(Vertex<T> vet) {
        if (adjList.containsKey(vet)) 
            return;
        adjList.put(vet, new ArrayList<>());
    }

    public void removeEdge(Vertex<T> vet1, Vertex<T> vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2)
            throw new IllegalArgumentException();
        adjList.get(vet1).remove(vet2);
        adjList.get(vet2).remove(vet1);
    }
    
    public void removeVertex(Vertex<T> vet) {
        if (!adjList.containsKey(vet))
            throw new IllegalArgumentException();
        adjList.remove(vet);
        for (List<Vertex<T>> list : adjList.values()) {
            list.remove(vet);
        }
    }

    public void print() {
        System.out.println("print the adjList");
        for (Map.Entry<Vertex<T>, List<Vertex<T>> > pair : adjList.entrySet()) {
            List<T> tmp = new ArrayList<>();
            for (Vertex<T> vertex : pair.getValue()) 
                tmp.add(vertex.value);
            System.out.println(pair.getKey().value + ": " + tmp + ",");
        }
    }

}
