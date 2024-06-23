package com.ph.Structure.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjMatrix<T extends Comparable<T>> {
    
    private List<T> vertices;
    // 顶点列表。列表元素是【顶点的值T】，索引是顶点索引。
    private List<List<Integer>> adjMatrix;
    // 邻接矩阵，这里的值代表【边的权重/值】，行列索引对应顶点索引。

    // 注意！
    // 这里的int[][] edges，以及adjlist中的edges，其格式都存在边的两个顶点对。比如这里，edges[x][0]与edges[x][1]。表示这两个点中间有一条边。
    public GraphAdjMatrix(T[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>();
        this.adjMatrix = new ArrayList<>();
        for (T v : vertices) {
            addVertex(v);
        }
        for (int[] e : edges) {
            addEdge(e[0], e[1]);
        }

    }

    public int size() {
        return this.vertices.size();
    }

    public void addVertex(T val) {
        // add vertex. add column and row, and assign them a value of 0.
        int n = size();
        vertices.add(val);
        List<Integer> newRow = new ArrayList<>(n);
        // 邻接矩阵下面多添一行0
        for (int i = 0; i < n; i++) {
            newRow.add(0);
        }
        adjMatrix.add(newRow);
        // 邻接矩阵右边多添一列0 （也即每个List<Integer>都在后面多加一个0。）
        for(List<Integer> row : adjMatrix) {
            row.add(0);
        }
    }

    public void addEdge(int i, int j) {
        // add edge
        if (i < 0 || j < 0 || i >= size() || j >= size())
            throw new IndexOutOfBoundsException();
        // in Undirected Graph, vertex are symmetrical about the diagonal.
        // Here, the element is Integer, the weight of edge, which default value is 0(no edge).
        adjMatrix.get(i).set(j, 1);
        adjMatrix.get(j).set(i, 1);
    }

    // 根据索引，删除某个顶点
    // 顶点删除，必然带着要删除相关联的边。注意和removeEdge的区别，这里是直接不存在了，而removeEdge里是仅将边weight置为0.
    public void removeVertex(int index) {
        // remove the vertex in vertices[index].
        if (index >= size())
            throw new IndexOutOfBoundsException();
        vertices.remove(index);
        adjMatrix.remove(index);
        for (List<Integer> list : adjMatrix) {
            list.remove(index);
        }
    }

    // 根据索引，删除某条边
    public void removeEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size())
            throw new IndexOutOfBoundsException();
        adjMatrix.get(i).set(i, 0);
        adjMatrix.get(j).set(i, 0);
    }

    public void print() {
        System.out.println("Vertex List:");
        System.out.println(vertices);
        System.out.println("Adjacency Matrix:");
        for (List<Integer> am : adjMatrix) {
            System.out.println(am);
        }
    }
}
