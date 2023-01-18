/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	
 *	@since	
 */
public class City implements Comparable<City> {
	
	// fields
	private String cityName;
	private String stateName;
	private String type;
	private int population;
	
	// constructor
	
	public City(String in1, String in2, String in3, int in4){
		stateName = in1;
		cityName = in2;
		type = in3;
		population = in4;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other){
		if(this.population != other.getPop()) 
			return this.population - other.getPop();
		else if(!this.stateName.equals(other.getStateName()))
				return this.stateName.compareTo(other.getStateName());
		else return this.cityName.compareTo(other.getCityName());
	}
	
	/**	
	 * 	 Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other){
		return this.cityName.equals(other.getCityName())
		&& this.stateName.equals(other.getStateName());
	}
	
	/**
	 * 	City name accessor method
	 *  @return city name
	 */
	public String getCityName(){
		return cityName;
	}
	/**
	 * 	State name accessor method
	 * 	@return state name
	 */
	public String getStateName(){
		return stateName;
	}
	/**
	 * 	City type accessor method
	 * 	@return type
	 */
	public String getType(){
		return type;
	}
	/**
	 *  Population accessor method
	 *  @return population
	 */
	public int getPop(){
		return population;
	}
	
	/**	
	 *	Converts this object to a String.
	 * 	@return this object in String format
	 */ 
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, type,
						population);
	}
		
}
