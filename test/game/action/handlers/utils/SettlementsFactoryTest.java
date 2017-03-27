package game.action.handlers.utils;

import models.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class SettlementsFactoryTest {

    @Test
    public void generateSettlementsTest() {
        Map map = new Map();
        MapSpot curr = map.getMiddleHexagonMapSpot();

        map.addHexagon(curr, new Hexagon(Terrain.GRASSLAND, 1, 1));
        map.addHexagon(curr.topLeft(), new Hexagon(Terrain.GRASSLAND, 1, 1));
        map.addHexagon(curr.topRight(), new Hexagon(Terrain.LAKE, 1, 1));

        SettlementsFactory settlementsFactory = new SettlementsFactory(map);

        assertEquals(1, settlementsFactory.generateSettlements(Team.UNKNOWN).size());
        assertEquals(2, settlementsFactory.generateSettlements(Team.UNKNOWN).size());
    }
}