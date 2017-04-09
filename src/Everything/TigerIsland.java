package Everything;

import Everything.game.action.MapUpdater.Updater;
import Everything.game.action.ai.AIBot;
import Everything.models.Map;
import Everything.models.Player;
import Everything.models.Team;

public class TigerIsland {

    //-----------
    // Attributes

    private String gameId;
    private String friendlyPID;

    private Map map;

    private Player friendly;
    private Player enemy;

    private AIBot friendlyAI;
    private Updater enemyMoveUpdater;

    //-------------
    // Constructors

    public TigerIsland(final String gameId, final String friendlyPID) {
        this.gameId = gameId;
        this.friendlyPID = friendlyPID;

        this.map = new Map();

        friendly = new Player(Team.FRIENDLY);
        enemy = new Player(Team.ENEMY);

//        AIBot ai = new AIBot();
//        this.enemyMoveUpdater = new Updater();
    }

    //--------
    // Methods

    public void doFriendlyMoveAndUpdateMap() {
        // Call OUR AI
        // Get the MapObject from our AI
        // Push it to the ConnectionClient Queue
    }

    public void getEnemyMoveAndUpdateMap() {
        // Pop an Enemy move from the buffer
        // Call Updater with enemy Move
    }
}