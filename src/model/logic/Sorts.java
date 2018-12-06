package model.logic;

import java.util.Comparator;

public class Sorts <T extends Comparable <T>>
{
	//------------------------------
	// QuickSort
	//------------------------------

	private int partition(T arr[], int inicio, int fin) 
	{ 
		T pivot = arr[fin];  
		int i = (inicio-1);
		for (int j=inicio; j < fin; j++) 
		{  
			if (arr[j].compareTo(pivot) < 0 ) 
			{ 
				i++; 
				T temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
			} 
		} 
		T temp = arr[i+1]; 
		arr[i+1] = arr[fin]; 
		arr[fin] = temp; 

		return i+1; 
	} 

	public long quickSort(T arr[], int inicio, int fin) 
	{ 
		if (inicio < fin) 
		{ 
			int p = partition (arr, inicio, fin); 

			quickSort(arr, inicio, p-1);
			quickSort(arr, p+1, fin);
		}
		return System.currentTimeMillis();
	}
}