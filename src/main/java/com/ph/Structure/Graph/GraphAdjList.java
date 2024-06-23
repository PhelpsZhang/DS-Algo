package com.ph.Structure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

    public List<Vertex<T>> graphBFS(Vertex<T> startVet) {
        // Vertex traversal
        List<Vertex<T>> res = new ArrayList<>();
        // Hash Set, record the visited vertex
        Set<Vertex<T>> visited = new HashSet<>();
        visited.add(startVet);
        // Queue to implement BFS
        Queue<Vertex<T>> que = new LinkedList<>();
        que.offer(startVet);
        // 以startVet为起点
        while (!que.isEmpty()) {
            // 队首顶点出队
            Vertex<T> vet = que.poll();
            // 记录访问顶点
            res.add(vet);
            // 遍历该顶点的所有adjacency vertex
            for (Vertex<T> adjVet : this.adjList.get(vet)) {
                // 如果该顶点已被访问，就跳过
                if (visited.contains(adjVet))
                    continue;
                // 未被访问过的顶点，入队
                que.offer(adjVet);
                // 标记该顶点为已访问。
                visited.add(adjVet);
            }
        }
        return res;
    }

    private void dfs(Set<Vertex<T>> visited, List<Vertex<T>> res, Vertex<T> vet) {
        // 记录访问顶点
        res.add(vet);
        // 标记该顶点已被访问
        visited.add(vet);
        // 遍历该顶点所有adjacency顶点
        for (Vertex<T> adjVet : this.adjList.get(vet)) {
            if (visited.contains(adjVet))
                continue;
            dfs(visited, res, adjVet);
        }
    }

    public List<Vertex<T>> graphDFS(Vertex<T> startVet) {
        // 顶点遍历序列
        List<Vertex<T>> res = new ArrayList<>();
        // 哈希集合，记录已被访问过的顶点
        Set<Vertex<T>> visited = new HashSet<>();
        dfs(visited, res, startVet);
        return res;
    }

}
