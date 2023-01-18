import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Edward Yao
 *	@since	1/9/23
 */
public class Population {
	
	// ArrayList of cities
	public ArrayList<City> cities;
	
	private SortMethods sorter;
	
	private Scanner scan;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	/**
	 * 	Initializes fields
	 */
	public Population(){
		cities = new ArrayList<City>();
		sorter = new SortMethods();
	}
	
	/**
	 *  Main method, runs the whole program
	 */
	public static void main(String[] args){
		Population pop = new Population();
		pop.printIntroduction();
		pop.readData();
		
		int choice = 0;
		
		while(choice != 9){
			pop.printMenu();
			choice = Prompt.getInt("Enter Selection", 1, 9);
			while(choice != 9 && (choice > 6 && choice < 1)){
				choice = Prompt.getInt("Enter Selection", 1, 9);
			}
			
			if(choice == 1){
				pop.leastPop();
			}
			if(choice == 2){
				pop.mostPop();
			}
			if(choice == 3){
				pop.firstName();
			}
			if(choice == 4){
				pop.lastName();
			}
			if(choice == 5){
				String state = Prompt.getString("Please enter state name");
				pop.mostPopInState(state);
			}
			if(choice == 6){
				String city = Prompt.getString("Please enter city name");
				pop.mostPopMatching(city);
			}
		}
		
	}
	
	/**
	 *  Reads in city data from the usPopData2017.txt file
	 * 	and saves it into the cities ArrayList
	 */
	 private void readData(){
		 
		 scan = FileUtils.openToRead(DATA_FILE);
		 scan.useDelimiter("[\t\n]");
		 
		 while(scan.hasNext()){
		 	City city = new City(scan.next(),scan.next(),scan.next(),scan.nextInt()); 
		 	cities.add(city);
		 }
		 
	 }
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("\n");
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	/**
	 *	Prints out the 50 least populated cities
	 */
	private void leastPop(){

		System.out.println("Fifty least populous cities:\n");
		long startMillisec = System.currentTimeMillis();
		ArrayList<City> sorted = cities;
		sorter.ascPop(sorted);
		long endMillisec = System.currentTimeMillis();
		
		for(int i=0; i<50; i++){
			System.out.println(sorted.get(i).toString());
		}
		System.out.println("\nElapsed Time: " + (endMillisec-startMillisec) + " milliseconds");
	}
	/**
	 *	Prints out the 50 most populated cities
	 */
	private void mostPop(){
		System.out.println("Fifty most populous cities:\n");
		long startMillisec = System.currentTimeMillis();
		ArrayList<City> sorted = cities;
		sorter.dscPop(sorted, 0, cities.size()-1);
		long endMillisec = System.currentTimeMillis();
		
		for(int i=0; i<50; i++){
			System.out.println(sorted.get(i).toString());
		}
		System.out.println("\nElapsed Time: " + (endMillisec-startMillisec) + " milliseconds");
	}
	/**
	 *	Prints out the first fifty cities by name
	 */
	private void firstName(){
		System.out.println("First fifty cities sorted by name:\n");
		long startMillisec = System.currentTimeMillis();
		ArrayList<City> sorted = cities;
		sorter.ascName(sorted);
		long endMillisec = System.currentTimeMillis();
		
		for(int i=0; i<50; i++){
			System.out.println(sorted.get(i).toString());
		}
		System.out.println("\nElapsed Time: " + (endMillisec-startMillisec) + " milliseconds");
	}
	/**
	 *	Prints out the last fifty states by name
	 */
	private void lastName(){
		System.out.println("Last fifty cities sorted by name:\n");
		long startMillisec = System.currentTimeMillis();
		ArrayList<City> sorted = cities;
		sorter.dscName(sorted, 0, cities.size()-1);
		long endMillisec = System.currentTimeMillis();
		
		for(int i=0; i<50; i++){
			System.out.println(sorted.get(i).toString());
		}
		System.out.println("\nElapsed Time: " + (endMillisec-startMillisec) + " milliseconds");
	}
	
	/**
	 *	Prints out the 50 most populated cities in a certain state
	 *	@param state, the state to search for the most populous cities in.
	 */
	private void mostPopInState(String state){
	
		System.out.println("Fifty most populous cities in " + state + ":\n");
		long startMillisec = System.currentTimeMillis();
		ArrayList<City> sorted = cities;
		sorter.dscPop(sorted, 0, cities.size()-1);
		long endMillisec = System.currentTimeMillis();
		
		int printed = 0;
		int i = 0;
		while(printed < 50){
			if(sorted.get(i).getStateName().equals(state)){
				System.out.println(sorted.get(i).toString());
				printed++;
			}
			i++;
			if(i == sorted.size())break;
		}
		if(printed == 0)System.out.println("\nThe state " + state + " was not found in the database");
		System.out.println("\nElapsed Time: " + (endMillisec-startMillisec) + " milliseconds");
	}
	
	/**
	 *	Prints out all cities matching a given name, sorted by population
	 */
	private void mostPopMatching(String cityName){
		System.out.println("Most populous cities called " + cityName + ":\n");
		long startMillisec = System.currentTimeMillis();
		ArrayList<City> sorted = cities;
		sorter.dscPop(sorted, 0, cities.size()-1);
		long endMillisec = System.currentTimeMillis();
		
		int printed = 0;
		
		for(int i=0; i<sorted.size(); i++){
			if(sorted.get(i).getCityName().equals(cityName)){
				System.out.println(sorted.get(i).toString());
				printed++;
			}
		}
		if(printed == 0) System.out.println("\nNo cities named " + cityName + " found in the database");
		System.out.println("\nElapsed Time: " + (endMillisec-startMillisec) + " milliseconds");
	}
}
