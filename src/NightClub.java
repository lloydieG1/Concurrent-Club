/**
 * The primary class for your NightClub, which will have a name, capacity and
 * a manager. People can arrive and leave your club asynchronously once it is
 * opened. Once the manager tries to close the club, the entrance should be
 * closed, then close the exit once everyone has left.
 */
public class NightClub {
    // TODO You need a variable for counting people named: peopleCount
    // you need a variable here for the capacity of the club: capacity
    public String name = "default";
    public boolean isOpen = false;
    private Entrance entrance;
    private Exit exit;
    private Manager manager;
    // TODO Appropriate Thread attributes here
    
    NightClub (String name, int capacity, Manager manager){
        this.name = name;
        this.capacity = capacity;
        this.manager = manager;
        manager.acceptJob(this);
    }
    public void start(){
        System.out.println("We are starting club:" + name);
        // TODO Create a thread for the manager
        this.entrance = new Entrance(this);
        this.exit = new Exit(this);
        // TODO Start a thread for the manager
    }
    public void end() throws InterruptedException{
        this.close();
        // kill the thread that is running the manager
        System.out.println("The simulation has ended.");
    }
    public void open() throws InterruptedException{
        if (!isOpen){
            isOpen = true;
            // TODO Create threads so that users can access or leave the club.
            // Should you also start these threads here?
        }
        else{
            System.out.println("The club is already open!");
        }
    }
    public void close() throws InterruptedException{
        if (isOpen){
            isOpen = false;
            System.out.println("Closing the entrance.");
            // TODO kill the threads that are facilitating accessing and
			// leaving feature. 
            while(getPeopleCount()>0){
                leave();
                System.out.println("People are leaving:" + getPeopleCount());
            }
            System.out.println("Everyone has left the club.");
        }
        else{
            System.out.println("The club is already closed!");
        }
    }
    public int getPeopleCount() {
        return peopleCount;
    }
    public void enter() {
        // TODO Must wait for space in a while loop for space in the club.
        peopleCount++;
    }
    public int leave(){
        // TODO Must have enough people to allow this feature. Is there a 
		// condition to check?
        peopleCount--; 
        return peopleCount;
    }
}
