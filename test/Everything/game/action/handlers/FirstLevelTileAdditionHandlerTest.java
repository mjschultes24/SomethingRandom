package Everything.game.action.handlers;

import Everything.models.Hexagon;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Terrain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FirstLevelTileAdditionHandlerTest {

    private Map map;
    private Hexagon h1;
    private Hexagon h2;
    private Hexagon h3;

    private MapSpot m1;
    private MapSpot m2;
    private MapSpot m3;

    private FirstLevelTileAdditionHandler handler;

    @Before
    public void initializeBeforeFirstTileTests(){
        map = new Map();
        handler = new FirstLevelTileAdditionHandler();
    }



    private boolean hexIsEqual(Hexagon hex1, Hexagon hex2) {
        return (hex1.getLevel() == hex2.getLevel()
                && hex1.getOccupiedBy() == hex2.getOccupiedBy()
                && hex1.getTerrainType() == hex2.getTerrainType()
                && hex1.getNumberOfMeeples() == hex2.getNumberOfMeeples()
                && hex1.isHasTiger() == hex2.isHasTiger()
                && hex1.isHasTotoro() == hex2.isHasTotoro()
                && hex1.getTileId() == hex2.getTileId()
                && hex1.isEmpty() == hex2.isEmpty());
    }

    @Test
    public void testAddValidFirstTileToMap(){

        handler.addFirstTileToMap(map);

        MapSpot middle = map.getMiddleHexagonMapSpot();

        Assert.assertTrue(map.getHexagon(middle.topLeft()).getTerrainType() == Terrain.JUNGLE
                && map.getHexagon(middle.topRight()).getTerrainType() == Terrain.LAKE
                && map.getHexagon(middle).getTerrainType() == Terrain.VOLCANO
                && map.getHexagon(middle.bottomLeft()).getTerrainType() == Terrain.ROCK
                && map.getHexagon(middle.bottomRight()).getTerrainType() == Terrain.GRASS);

    }

    @Test
    public void testAddDuplicateTerrainFirstTileToMap(){

        h2 = new Hexagon(Terrain.LAKE,1,0);

        try {
            handler.addFirstTileToMap(map);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage().equals("Bad First Tile to be Placed"));
        }

    }

    @Test
    public void testAddBadPlacementFirstTileToMap(){

        handler.addFirstTileToMap(map);

        try {
            handler.addFirstTileToMap(map);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage().equals("Bad First Tile Placement"));
        }

    }

    private void initTile(){
        MapSpot middle = map.getMiddleHexagonMapSpot().topLeft().left();
        handler.addFirstTileToMap(map);
        m1 = middle;
        m2 = m1.topLeft();
        m3 = m1.left();
        h1 = new Hexagon(Terrain.JUNGLE, 1, 1);
        h2 = new Hexagon(Terrain.VOLCANO, 1, 1);
        h3 = new Hexagon(Terrain.LAKE, 1, 1);
    }

    @Test
    public void testValidTilePlacement() throws CannotPerformActionException {
        initTile();

        handler.addTileToMap(h1,h2,h3,m1,m2,m3, map);

        Assert.assertTrue(hexIsEqual(map.getHexagon(m1), h1)
                && hexIsEqual(map.getHexagon(m2), h2)
                && hexIsEqual(map.getHexagon(m3), h3));

    }

    @Test
    public void testInvalidTilePlacement(){
        initTile();

        m3 = m1.bottomLeft();

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3, map);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage().equals("Bad Tile Placement"));
        } catch (CannotPerformActionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTooManyVolcanos(){
        initTile();

        h1 = new Hexagon(Terrain.VOLCANO, 1, 1);

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3, map);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage().equals("Bad Tile to be placed"));
        } catch (CannotPerformActionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNoVolcanos(){
        initTile();

        h2 = new Hexagon(Terrain.ROCK, 1, 1);

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3, map);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage().equals("Bad Tile to be placed"));
        } catch (CannotPerformActionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDifferentTileID(){
        initTile();

        h1 = new Hexagon(Terrain.GRASS, 1, 2);
        h3 = new Hexagon(Terrain.LAKE, 1, 3);

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3, map);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage().equals("Bad Tile to be placed"));
        } catch (CannotPerformActionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPlaceOnExistingHexagon(){
        initTile();

        m3 = m1.topRight();

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3, map);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage().equals("Bad Tile Placement"));
        } catch (CannotPerformActionException e) {
            e.printStackTrace();
        }
    }

}