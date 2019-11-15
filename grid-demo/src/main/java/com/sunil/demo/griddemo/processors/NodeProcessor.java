package com.sunil.demo.griddemo.processors;

import com.sunil.demo.griddemo.model.Coordinate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NodeProcessor {

    public List<Coordinate> generateNodes(List<Coordinate> intersections, int noOfNodes){
        Set<Coordinate> nodes = new HashSet<>();
        Random rand = new Random();
        int noOfIntersections = intersections.size();
        for (int i = 0; i < noOfNodes; i++) {
            int randomIndex = rand.nextInt(noOfIntersections);
            nodes.add(intersections.get(randomIndex));
        }
        return new ArrayList<>(nodes);
    }

}
