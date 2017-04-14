
import java.util.*;

public class Divide_Conquer {

	public static void main(String[] args) {
		long time;
		int high = 10000, low = 1000, step = 100;
		long num = 1;
		long[][] myArray = new long[high][high];
		for(int i =0; i<high; i++){
			for(int j=0; j<high; j++){
				myArray[i][j] = num++;
			}
		}
		
		for(int l = low; l <= high; l+=step){
			Random r = new Random();
			long key = high;
			time = System.nanoTime();
			contains(myArray, 0, myArray.length-1, 0, myArray[0].length-1, key);
			time = System.nanoTime() - time;
			System.out.println(time);
		}

	}
	
	public static boolean contains(long[][] arr, int start, int end, int left, int right,  long key){
		boolean upperLeft = false, upperRight = false, lowerLeft = false, lowerRight = false;
		boolean result = false;
		int middle = (start+end)/2;
		int across = (left+right)/2;
		
		// Base Case
		if(start >= end || left >= right){
			return false; 
		}
		
		// Divide 
		if(arr[middle][left] <= key){
			if(arr[middle][right] <= key)
				upperLeft = contains(arr, start, middle, left, across, key);
			else
				upperRight = contains(arr, start, middle, across+1, right, key);
		}else{
			if(arr[end][right] <= key)
				lowerLeft = contains(arr, middle+1, end, left, across, key);
			else
				lowerRight = contains(arr, middle+1, end, across+1, right, key);
		}
		
		
		// Conquer
		if(upperLeft || upperRight || lowerLeft || lowerRight){
			result = true;
		}else{
			for(int i =start; i< end; i++){
				for(int j=left; j< right; j++){
					if(arr[i][j] == key){
						//System.out.println("tesing " + arr[i][j]);
						result = true;
					}
				}
			}
		}
		
		return result;
	}

}
