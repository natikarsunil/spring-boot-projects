package com.sunil.demo.griddemo.processors;

import com.sunil.demo.griddemo.model.Coordinate;
import com.sunil.demo.griddemo.model.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

public class GridProcessorTest {

    @InjectMocks
    private GridProcessor gridProcessor;

    @Mock
    NodeProcessor nodeProcessor;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSample(){
        int rows = 3;
        int columns = 4;
        int noOfNodes = 4;

        List<Coordinate> nodes = generateDummyNodes();

        Mockito.when(nodeProcessor.generateNodes(ArgumentMatchers.any(), ArgumentMatchers.anyInt())).thenReturn(nodes);

        Result result = gridProcessor.doProcessing(rows, columns, noOfNodes);

        Assert.assertEquals(Integer.valueOf(1), result.getDistanceFromAdjacentNodeRight().get(nodes.get(0)));
        Assert.assertEquals(Integer.valueOf(0), result.getDistanceFromAdjacentNodeRight().get(nodes.get(1)));
        Assert.assertEquals(Integer.valueOf(2), result.getDistanceFromAdjacentNodeRight().get(nodes.get(2)));
        Assert.assertEquals(Integer.valueOf(0), result.getDistanceFromAdjacentNodeRight().get(nodes.get(3)));

        Assert.assertEquals(Integer.valueOf(0), result.getDistanceFromAdjacentNodeDown().get(nodes.get(0)));
        Assert.assertEquals(Integer.valueOf(1), result.getDistanceFromAdjacentNodeDown().get(nodes.get(1)));
        Assert.assertEquals(Integer.valueOf(0), result.getDistanceFromAdjacentNodeDown().get(nodes.get(2)));
        Assert.assertEquals(Integer.valueOf(0), result.getDistanceFromAdjacentNodeDown().get(nodes.get(3)));
    }

    private List<Coordinate> generateDummyNodes(){
        List<Coordinate> nodes = new ArrayList<>();

        Coordinate node = new Coordinate(3,2);
        nodes.add(node);
        node = new Coordinate(4,3);
        nodes.add(node);
        node = new Coordinate(2,3);
        nodes.add(node);
        node = new Coordinate(4,2);
        nodes.add(node);

        return nodes;
    }
}
