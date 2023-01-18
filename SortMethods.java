import java.util.ArrayList;
/**
 *	SortMethods - Sorts an arrays. Made specifically for Population
 *
 *	@author Edward Yao
 *	@since	01/17/23
 */
public class SortMethods {
	
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(ArrayList<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection sort algorithm, sorts in ascending order by population
	 *	@param arr		array of City  objects to sort
	 */
	public void ascPop(ArrayList<City> arr) {
		int n = arr.size();
		for (int i = 0; i < n-1; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (arr.get(j).compareTo(arr.get(min)) < 0) min = j;
			}
			swap(arr, min, i);
		}
	}
	
	/**
	 *	Insertion Sort algorithm,  sorting name in ascending order 
	 *	@param arr		array of Integer objects to sort
	 */
	public void ascName(ArrayList<City> arr) {
		int n = arr.size();
        for (int i = 1; i < n; ++i) {
            City cur = arr.get(i);
            int j = i - 1;
			while (j >= 0 && arr.get(j).getCityName().compareTo(cur.getCityName()) > 0) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, cur);
        }
	}
	
	/**
	 *	Merge Sort algorithm, for the populations - in descending order 
	 *	@param arr		array of Integer objects to sort
	 */
	public void dscPop(ArrayList<City> arr, int l, int r) {
		if (l < r) {
			int m = (l+r) / 2;
			dscPop(arr, l, m);
			dscPop(arr, m + 1, r);
			merge1(arr, l, m, r);
        }
	}
	/**
	 *	Merge Sort algorithm, for the city names - in descending order 
	 *	@param arr		array of Integer objects to sort
	 */
	public void dscName(ArrayList<City> arr, int l, int r) {
		if (l < r) {
			int m = (l+r) / 2;
			dscName(arr, l, m);
			dscName(arr, m + 1, r);
			merge2(arr, l, m, r);
        }
	}
	
	/**
	 * 	Splits the array into two arrays
	 * 	@param arr 	the array to be sorted 
	 *			l the left index
	 *			m the middle, where the two ArrayLists split
     *			r the right index
	 */
	public void merge1(ArrayList<City> arr, int l, int m, int r) {
		int size1 = m - l + 1;
		int size2 = r - m;
		ArrayList<City> left = new ArrayList<City>();
		ArrayList<City> right = new ArrayList<City>();
		for (int i = 0; i < size1; i++) left.add(arr.get(l + i));
		for (int i = 0; i < size2; i++) right.add(arr.get(m + i + 1));
		int i = 0, j = 0;
		int k = l;
		while (i < size1 && j < size2) {
			if (left.get(i).compareTo(right.get(j)) > 0) {
				arr.set(k, left.get(i));
				i++;
			}
			else {
				arr.set(k, right.get(j));
				j++;
			}
			k++;
		}
		while (i < size1) {
			arr.set(k, left.get(i));
			i++;
			k++;
		}
		while (j < size2) {
			arr.set(k, right.get(j));
			j++;
			k++;
		}
    }
    
	/**
	 * 	Splits the array into two arrays
	 * 	@param arr 	the array to be sorted 
	 */
	public void merge2(ArrayList<City> arr, int l, int m, int r) {
		int size1 = m - l + 1;
		int size2 = r - m;
		ArrayList<City> left = new ArrayList<City>();
		ArrayList<City> right = new ArrayList<City>();
		for (int i = 0; i < size1; i++) left.add(arr.get(l + i));
		for (int i = 0; i < size2; i++) right.add(arr.get(m + i + 1));
		int i = 0, j = 0;
		int k = l;
		while (i < size1 && j < size2) {
			if (left.get(i).getCityName().compareTo(right.get(j).getCityName()) > 0) {
				arr.set(k, left.get(i));
				i++;
			}
			else {
				arr.set(k, right.get(j));
				j++;
			}
			k++;
		}
		while (i < size1) {
			arr.set(k, left.get(i));
			i++;
			k++;
		}
		while (j < size2) {
			arr.set(k, right.get(j));
			j++;
			k++;
		}
    }
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(ArrayList<City> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %4d", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr.get(a));
			else System.out.printf(", %4d", arr.get(a));
		}
		System.out.println(" )");
	}
	/*
	public void run() {
		int[] arr = new int[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();


		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr, 0, 9);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}
	*/
}
