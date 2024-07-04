class Singleton{
	
	//Step 1: Create a static instance of the class at the time of class loading
	private static final Singleton obj = new Singleton();
	
	//Step 2: Make the default constructor private to prevent instantiation
	private Singleton(){
	}
	
	//Step 3: Public method to provide access to the instance
	public static Singleton getInstance(){
		return obj;
    }

    public void display(){
        System.out.println("This is Singleton class");
    }

    public static void main(String[] args) { 
        // Get the only instance available 
        Singleton singleton = Singleton.getInstance();
        singleton.display();
    }

}
