package ru.progwards.java2.lessons.graph;

import java.util.*;
import java.util.function.Consumer;

public class Boruvka {
    public static List<Edge> edgesResult = new ArrayList<>();

    public static void boruvka(Graph graph, Consumer<Node> onVisitedNode, ConsumerWithException<Edge> onVisitedEdge, ConsumerWithException<Edge> onFoundEdge, Boolean isCanceled) throws Exception {

        DisjointSets sets = new DisjointSets(graph.getNodes());

        // sort all edges
        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        java.util.Collections.sort(sortedEdges, (e1, e2) -> Integer.compare(e1.getCost(), e2.getCost()));

        while (sets.size() > 1) {
            Iterator<DisjointSets.DisjointSet> setsIterator = sets.get().iterator();
            while (setsIterator.hasNext()) {

                TreeSet<Edge> edges = new TreeSet<>();
                DisjointSets.DisjointSet set = setsIterator.next();

                for (Node node : set.getNodes()) {
                    Optional<Edge> closestEdgeForNode = node.getEdges()
                            .stream()
                            .filter(edge -> !set.isNodeInSet(edge.getDestination()))
                            .min((e1, e2) -> Integer.compare(e1.getCost(), e2.getCost()));
                    if (closestEdgeForNode.isPresent()) {
                        onVisitedEdge.accept(closestEdgeForNode.get());
                        edges.add(closestEdgeForNode.get());
                        edgesResult.add(closestEdgeForNode.get());
                    }
                }

                Iterator<Edge> edgeIterator = edges.iterator();
                if (edgeIterator.hasNext()) {
                    Edge closestEdge = edgeIterator.next();
                    onFoundEdge.accept(closestEdge);
                    sets.merge(closestEdge.getSource(), closestEdge.getDestination(), false);
                    setsIterator.remove();
                }

                if (isCanceled) {
                    return;
                }
            }
        }
    }

    public static List<Edge> minTree(){
        return edgesResult;
    }

    public static void main(String[] args) throws Exception {
        Boruvka mst = new Boruvka();
        GraphUtils graphutils = new GraphUtils();
        AdjacencyListGraph graph = new AdjacencyListGraph(graphutils.createRandomGraph(4, 5, 15, false));
        Consumer<Node> onVisitedNode = node -> {};
        ConsumerWithException<Edge> onVisitedEdge = edge -> {};
        ConsumerWithException<Edge> onFoundEdge = edge -> {};
        mst.boruvka(graph, onVisitedNode, onVisitedEdge, onFoundEdge, false);
        System.out.println(mst.minTree().toString());
    }
}

