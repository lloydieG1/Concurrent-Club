/**
 * The class which will act as the exiting for your nightclub, modelling
 * people leaving your nightclub. 
 */
public class Exit{
    private NightClub nightClub;
    private int sleepScaler = 10;
    private String name = "Exit";
    Exit (NightClub nightClub){
        this.nightClub = nightClub;
    }
    Exit (NightClub nightClub, String name){
        this.nightClub = nightClub;
        this.name = name;
    }
    // TODO Does this need an additional method here? Does the class perform a 
	// similar role to the Entrance class?

}
