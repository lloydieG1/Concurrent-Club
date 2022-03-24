/**
 * The class which will act as the entrance for your nightclub, modelling
 * people arriving at and entering your nightclub. 
 */
public class Entrance implements Runnable{
    private NightClub nightClub;
    private int sleepScaler = 10;
    private String name = "Entrance";
    
    Entrance (NightClub nightClub){
        this.nightClub = nightClub;
    }
    
    Entrance (NightClub nightClub, String name){
        this.nightClub = nightClub;
        this.name = name;
    }
    
    public void run() {
    	while (true) {
	        try {
	        	double rand = Math.random();
	        	//wait a random amount of time for someone to enter
	        	Thread.sleep((long) (sleepScaler * rand));
				nightClub.enter();
				System.out.println("Arrival: " + nightClub.getPeopleCount());
			} catch (InterruptedException e) {
				return;
			}
    	}
    }

}
