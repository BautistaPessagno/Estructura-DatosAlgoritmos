package Test;

import core.DijkstraPath;
import core.GraphFactory;
import core_service.GraphBuilder;
import core_service.GraphService;
import use.EmptyEdgeProp;
import use.WeightedEdge;


public class Test {
        public static void main(String[] args) {
            GraphService<Integer, EmptyEdgeProp> g = new GraphBuilder<Integer,EmptyEdgeProp>().
                    withMultiplicity(GraphService.Multiplicity.SIMPLE).withDirected(GraphService.EdgeMode.DIRECTED).
                    withAcceptSelfLoop(GraphService.SelfLoop.YES).
                    withAcceptWeight(GraphService.Weight.NO).
                    withStorage(GraphService.Storage.SPARSE).build();
            g.addVertex(8);
            g.addVertex(2);
            g.addEdge(2, 9, new EmptyEdgeProp());
            g.addEdge(2, 8, new EmptyEdgeProp());
            g.addEdge(8, 3, new EmptyEdgeProp());
            g.addEdge(9, 4, new EmptyEdgeProp());
            g.addEdge(4, 5, new EmptyEdgeProp());
            g.addEdge(3, 5, new EmptyEdgeProp());
            g.dump();

            System.out.println("BFS: ");
            g.printBFS(2);
            System.out.println("Iterable BFS");
            for(Integer node: g.getBFS(2))
                System.out.print((node + " "));
            System.out.println();

            System.out.println("DFS: ");
            g.printBFS(2);
            System.out.println("Iterable DFS");
            for(Integer node: g.getDFS(2))
                System.out.print((node + " "));
            System.out.println();

            GraphService<Integer, EmptyEdgeProp> g2 = new GraphBuilder<Integer,EmptyEdgeProp>().
                    withMultiplicity(GraphService.Multiplicity.SIMPLE).withDirected(GraphService.EdgeMode.DIRECTED).
                    withAcceptSelfLoop(GraphService.SelfLoop.YES).
                    withAcceptWeight(GraphService.Weight.NO).
                    withStorage(GraphService.Storage.SPARSE).build();
            g2.addVertex(1);
            g2.addVertex(4);
            g2.addEdge(1, 2, new EmptyEdgeProp());
            g2.addEdge(2, 3, new EmptyEdgeProp());
            g2.addEdge(1, 3, new EmptyEdgeProp());
            g2.addEdge(3, 4, new EmptyEdgeProp());
            g2.addEdge(4, 5, new EmptyEdgeProp());
            g2.addEdge(4, 6, new EmptyEdgeProp());
            g2.addEdge(4, 7, new EmptyEdgeProp());
            g2.addEdge(5, 6, new EmptyEdgeProp());
            g2.addEdge(6, 7, new EmptyEdgeProp());
            g2.dump();

            System.out.println("BFS2: ");
            g2.printBFS(1);
            System.out.println("Iterable BFS2");
            for(Integer node: g2.getBFS(1))
                System.out.print((node + " "));
            System.out.println();

            System.out.println("DFS2: ");
            g2.printBFS(1);
            System.out.println("Iterable DFS2");
            for(Integer node: g2.getDFS(1))
                System.out.print((node + " "));
            System.out.println();



            System.out.println("Dijkstra Path:");
            GraphService<Character, WeightedEdge> d =  GraphFactory.create(GraphService.Multiplicity.SIMPLE, GraphService.EdgeMode.UNDIRECTED, GraphService.SelfLoop.NO, GraphService.Weight.YES, GraphService.Storage.SPARSE);
            d.addEdge('S', 'A', new WeightedEdge(5));
            d.addEdge('A', 'G', new WeightedEdge(4));
            d.addEdge('S', 'G', new WeightedEdge(5));
            d.addEdge('S', 'E', new WeightedEdge(4));
            d.addEdge('S', 'B', new WeightedEdge(7));
            d.addEdge('B', 'F', new WeightedEdge(8));
            d.addEdge('E', 'F', new WeightedEdge(8));
            d.addEdge('F', 'C', new WeightedEdge(11));
            d.addEdge('B', 'C', new WeightedEdge(9));
            d.addEdge('C', 'D', new WeightedEdge(7));
            d.addEdge('E', 'D', new WeightedEdge(8));
            d.addEdge('E', 'C', new WeightedEdge(9));
            System.out.println("Dijsktra graph:");
            DijkstraPath<Character, WeightedEdge> pathRta = d.dijsktra('S');
            System.out.println(pathRta);
            System.out.println("All paths from A to D:");
            d.printAllPaths('S', 'D');

        }
    }
