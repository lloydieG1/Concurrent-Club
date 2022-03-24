/**
 * The class which will act as the exiting for your nightclub, modelling
 * people leaving your nightclub. 
 */
public class Exit implements Runnable {
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
    
    public void run() {
    	while (true) {
	        try {
	        	double rand = Math.random();
	        	//wait a random amount of time before having someone leave
	        	Thread.sleep((long) (sleepScaler * rand));
				nightClub.leave();
				System.out.println("Exit: " + nightClub.getPeopleCount());
			} catch (InterruptedException e) {
				return;
			}
    	}
    }

}
