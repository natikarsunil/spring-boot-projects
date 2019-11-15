package com.sunil.demo.griddemo.processors;

import com.sunil.demo.griddemo.model.Coordinate;
import com.sunil.demo.griddemo.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GridProcessor {

    private int rows;
    private int columns;
    private int noOfNodes;

    @Autowired
    private NodeProcessor nodeProcessor;

    public Result doProcessing(int rows, int columns, int noOfNodes) {

        Result result = new Result();

        this.rows = rows;
        this.columns = columns;
        this.noOfNodes = noOfNodes;

        List<Coordinate> intersections = getIntersections();
        System.out.println("Intersections (x, y): ");
        intersections.forEach(System.out::println);

        List<Coordinate> nodes = nodeProcessor.generateNodes(intersections, noOfNodes);
        System.out.println("\n" + noOfNodes + " Nodes (x, y) : ");

        result.setIntersections(intersections);
        result.setNodes(nodes);

        nodes.forEach(node -> {
            int distanceRight = getDistanceFromRightNode(nodes, node);
            int distanceDown = getDistanceFromDownNode(nodes, node);
            System.out.println(node + " -> distance from adjacent right node : "
                    + distanceRight + " , distance from adjacent down node : " + distanceDown);
            result.getDistanceFromAdjacentNodeRight().put(node, distanceRight);
            result.getDistanceFromAdjacentNodeDown().put(node, distanceDown);
        });

        return result;
    }

    private List<Coordinate> getIntersections() {
        List<Coordinate> intersections = new ArrayList<>();
        for (int i = 1; i <= columns; i++) {
            for (int j = 1; j <= rows; j++) {
                intersections.add(new Coordinate(i, j));
            }
        }
        return intersections;
    }

    private int getDistanceFromRightNode(List<Coordinate> nodes, Coordinate currentNode) {
        int distance = 0;
        for (int i = currentNode.getX(); i <= rows; i++) {
            Coordinate node = new Coordinate(i + 1, currentNode.getY());
            distance++;
            if (nodes.contains(node)) {
                return distance;
            }
        }
        return 0;
    }


    private int getDistanceFromDownNode(List<Coordinate> nodes, Coordinate currentNode) {
        int distance = 0;
        for (int i = currentNode.getY(); i > 0; i--) {
            Coordinate node = new Coordinate(currentNode.getX(), i - 1);
            distance++;
            if (nodes.contains(node)) {
                return distance;
            }
        }
        return 0;
    }

}
