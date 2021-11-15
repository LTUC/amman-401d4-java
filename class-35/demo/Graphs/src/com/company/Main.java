package com.company;

public class Main {
    public static void main(String[] args) {


        Graph graph = new Graph();

        graph.addVertex("Bob");
        graph.addVertex("Mary");
        graph.addVertex("Alice");
        graph.addVertex("Carl");

        graph.addEdge("Alice", "Carl");
        graph.addEdge("Bob", "Mary");
        graph.addEdge("Bob", "Alice");

        System.out.println("the graph => " + graph.printGraph());
        System.out.println("the size => " + graph.size());

//        graph.removeEdge("Carl", "Alice");
//        graph.removeVertex("Bob");
//        System.out.println();

//        System.out.println("the graph => " + graph.printGraph());
//        System.out.println("the size => " + graph.size());

        System.out.println();

//        System.out.println(graph.bft());
        System.out.println(graph.dft(graph, "Alice"));
        System.out.println(graph.bft(graph, "Alice"));
    }
}
