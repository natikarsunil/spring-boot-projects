package com.sunil.demo.griddemo.model;

import com.sunil.demo.griddemo.model.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private List<Coordinate> intersections;
    private List<Coordinate> nodes;
    private Map<Coordinate, Integer> distanceFromAdjacentNodeRight;
    private Map<Coordinate, Integer> distanceFromAdjacentNodeDown;

    public Result(){
        intersections = new ArrayList<>();
        nodes = new ArrayList<>();
        distanceFromAdjacentNodeRight = new HashMap<>();
        distanceFromAdjacentNodeDown = new HashMap<>();
    }

    public List<Coordinate> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<Coordinate> intersections) {
        this.intersections = intersections;
    }

    public List<Coordinate> getNodes() {
        return nodes;
    }

    public void setNodes(List<Coordinate> nodes) {
        this.nodes = nodes;
    }

    public Map<Coordinate, Integer> getDistanceFromAdjacentNodeRight() {
        return distanceFromAdjacentNodeRight;
    }

    public void setDistanceFromAdjacentNodeRight(Map<Coordinate, Integer> distanceFromAdjacentNodeRight) {
        this.distanceFromAdjacentNodeRight = distanceFromAdjacentNodeRight;
    }

    public Map<Coordinate, Integer> getDistanceFromAdjacentNodeDown() {
        return distanceFromAdjacentNodeDown;
    }

    public void setDistanceFromAdjacentNodeDown(Map<Coordinate, Integer> distanceFromAdjacentNodeDown) {
        this.distanceFromAdjacentNodeDown = distanceFromAdjacentNodeDown;
    }
}
