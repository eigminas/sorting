package solution;

/*****************************************************/
/*** Sort class currently contains some initial    ***/
/*** methods for implementing sorting algorithms   ***/
/***                                               ***/
/***     Initial Author: Jason Steggles 20/09/19   ***/
/***     Extended by:   Eigminas Slavinskas        ***/
/*****************************************************/

import java.io.*;
import java.text.*;
import java.util.*;

public class Sort {

/** Size of array **/
private int size;

/** Number of used elements in array **/
private int usedSize;

/** Array of integers **/
private int[] A;

/** Global variables for counting sort comparisons **/
public int compIS; /** Global comparison count for Insertion Sort **/
public int compQS; /** Global comparison count for Quicksort **/
public int compNS; /** Global comparison count for newsort **/

/*****************/
/** Constructor **/
/*****************/
Sort(int max)
{
    /** Initialiase global sort count variables **/
    compIS = 0;
    compQS = 0;
    compNS = 0;
    
    /** Initialise size variables **/
    usedSize = 0;
    size = max;
    
    /** Create Array of Integers **/
    A = new int[size];
}

/**************************************************/
/*** (1) Implements the isnertion sort algorithm***/
/**************************************************/
public void insertion(){
	
	for(int i = 1; i <= usedSize-1; i++){
		
		int key = A[i];
		int j = i;
		
		compIS = compIS + 1; // incrementing comparison counter before while loop
		
		while(j > 0 && key < A[j-1]){
			
			compIS = compIS + 1; // incrementing comparison counter within while loop
			
			A[j] = A[j-1]; // shift
			j = j - 1;
			
		}
		
		A[j] = key; // insert
		
	}
}

/*********************************************************/
/*** (2) Implements the quicksort algorithm recursively***/
/*********************************************************/
public void quicksort(int L, int R){
	// make sure pointers do not cross
	if(R > L){
		
		int p = partition(L, R); // partition the array
		quicksort(L, p-1); // left side
		quicksort(p+1, R); // right side
		
	}
	
}
/*********************************************************/
/*** Private method for partitioning the array********/
/*********************************************************/
private int partition(int left, int right){
	
	int v = A[right]; // pivot
	int pL = left; // left pointer
	int pR = right; // right pointer
	// make sure pointers do not cross
	while(pL < pR){
		
		compQS = compQS + 1; // incrementing comparison counter before while loop
		
		while(A[pL] < v){ 
			compQS = compQS + 1; // incrementing comparison counter within while loop
			pL = pL + 1; // left pointer is pointing to the next element ( -> )
		}
		
		compQS = compQS + 1; // incrementing comparison counter before while loop
	
		while(A[pR] >= v && pR > left){
			compQS = compQS + 1; // incrementing comparison counter within while loop
			pR = pR - 1; // right pointer is pointing to the next element ( <- )
		}
		
		if(pL < pR){
			swap(pL, pR); // swap elements in the array
		}
	}
	
	swap(pL, right); // put pivot in the correct position
	return pL; // return left pointer
}

/*********************************************/
/*** Swap two elements in the array************/
/*********************************************/
private void swap(int pL, int pR){
	
	int temp = A[pL];
	A[pL] = A[pR];
	A[pR] = temp;
	
}

/*********************************************/
/*** (3) implementation of newSort algorithm**/
/*********************************************/
public void newSort(){
	int pos = 0; // initial position is the beginning of an array
	
	while(pos < usedSize){
		
		int min = findMinFrom(pos); // finds minimum value from pos
		for(int i = pos; i <= usedSize-1; i++){
			compNS = compNS + 1; // increment comparison counter
			if(A[i] == min){
				swap(i, pos); // swap elements
				pos = pos + 1; // increment the position
			}
			
		}
	}
}


/*********************************************/
/*** findMinFrom is a private method that  ***/
/****is used in newSort method****************/
private int findMinFrom(int pos){
	
	
	int min = A[pos];
	
	for(int i = pos + 1; i <= usedSize - 1; i++){
		compNS = compNS + 1; // increment comparison counter
		if(A[i] < min){
			min = A[i]; // new minimum value
		}
	}
	
	return min; // return minimum value
}



/*********************************************/
/*** Read a file of integers into an array ***/
/*********************************************/
public void readIn(String file)
{
   try
   {
       /** Initialise loop variable **/
       usedSize = 0;
       
       /** Set up file for reading **/
       FileReader reader = new FileReader(file);
       Scanner in = new Scanner(reader);
       
       /** Loop round reading in data while array not full **/
       while(in.hasNextInt() && (usedSize < size))
       {
           A[usedSize] = in.nextInt();
           usedSize++;
       }
       
    }
    catch (IOException e)
    {
       System.out.println("Error processing file " + file);
    }
   
}

/**********************/
/*** Display array  ***/
/**********************/
public void display(int line, String header)
{
    /*** Integer Formatter - three digits ***/
    NumberFormat FI = NumberFormat.getInstance();
    FI.setMinimumIntegerDigits(3);

    /** Print header string **/
    System.out.print("\n"+header);

    /** Display array data **/
    for (int i=0;i<usedSize;i++)
    {
        /** Check if new line is needed **/
        if (i%line == 0) 
        { 
            System.out.println(); 
        }
        
        /** Display an array element **/
        System.out.print(FI.format(A[i])+" ");
    }
}

public int getUsedSize(){
	return usedSize;
}

}  /** End of Sort Class **/
