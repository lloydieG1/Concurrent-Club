/**
 * The manager is responsible for opening and closing the nightclub.
 */
public class Manager implements Runnable{
    private String name;
    private NightClub nightClub;
    private int sleepScaler = 10000;
    
    Manager(String name){
        this.name = name;
    }
    
    Manager(String name, NightClub nightClub){
        this.name = name;
        this.nightClub = nightClub;
    }
    
    public void acceptJob(NightClub nightClub){
        this.nightClub = nightClub;
    }
    
    public void leaveJob(){
        this.nightClub = null;
    }
    
    public void run() {
    	try {
    		//manager opens club
			this.nightClub.open();
			//some time passes before manager tries to close club
			Thread.sleep(sleepScaler);
			this.nightClub.close();
			
		} catch (InterruptedException e) {
			return;
		}
    	
    }
}
