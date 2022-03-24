/**
 * The primary class for your NightClub, which will have a name, capacity and
 * a manager. People can arrive and leave your club asynchronously once it is
 * opened. Once the manager tries to close the club, the entrance should be
 * closed, then close the exit once everyone has left.
 */
public class NightClub {
    public String name = "default";
    public boolean isOpen = false;
    
    private Entrance entrance;
    private Exit exit;
    private Manager manager;
    private int capacity;
    private int peopleCount;

    private Thread entranceThread;
    private Thread exitThread;
    private Thread managerThread;

    
    NightClub (String name, int capacity, Manager manager){
        this.name = name;
        this.capacity = capacity;
        this.manager = manager;
        manager.acceptJob(this);
    }
    
    public void start(){
        System.out.println("We are starting club:" + name);

        this.entrance = new Entrance(this);
        this.exit = new Exit(this);
        
        //create and start manager thread
        this.managerThread = new Thread(manager);
        this.managerThread.start();
    }
    
    public void end() throws InterruptedException{
    	//end manager thread
    	this.managerThread.interrupt();
    	this.managerThread.join();
        this.close();

        System.out.println("The simulation has ended.");
    }
    
    public synchronized void open() throws InterruptedException{
        if (!isOpen){
            isOpen = true;
            //start entrance and exit threads
            entranceThread = new Thread(entrance);
            exitThread = new Thread(exit);
            entranceThread.start();
            exitThread.start();      
        }
        else{
            System.out.println("The club is already open!");
        }
    }
    
    public void close() throws InterruptedException{
        if (isOpen){
            isOpen = false;
            
            //end entrance and exit threads
            entranceThread.interrupt();
            exitThread.interrupt();

            while(getPeopleCount() > 0){
                leave();
                System.out.println("People are leaving:" + getPeopleCount());
            }
            System.out.println("Everyone has left the club.");
            
            entranceThread.join();
            exitThread.join();
        }
        else{
            System.out.println("The club is already closed!");
        }
    }
    
    public int getPeopleCount() {
        return peopleCount;
    }
    
    public synchronized void enter() throws InterruptedException {
        // wait for space in club before incrementing amount of people in club
    	while (getPeopleCount() == capacity) {
            wait();
    	}
    	peopleCount++;
    	notifyAll();
    }
    
    public synchronized int leave() throws InterruptedException{
    	//will decrement the count of people in the club only if there is at least one
    	while (getPeopleCount() == 0) {
            wait();
    	}
        peopleCount--; 
    	notifyAll();
    	
        return peopleCount;
    }
}
