package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomSort {
	private ArrayList<Integer> numberList;
	private int counter;
	private long time;
	private String startArray;

	public void sort(int size){
		numberList = new ArrayList<Integer>();
		
		for(int i=0; i<size; i++){
			numberList.add(randInt(1, 100));
		}
		startArray = numberList.toString();
		
		counter = 0;
		long startClock = System.nanoTime();
		while(!isSorted(numberList)){
			Collections.shuffle(numberList);
			counter++;
		}
		long stopClock = System.nanoTime();
		time = TimeUnit.MILLISECONDS.convert(stopClock-startClock, TimeUnit.NANOSECONDS);
	}
	
	private int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	private boolean isSorted(ArrayList<Integer> list)
	{
	    boolean sorted = true;        
	    for (int i = 1; i < list.size(); i++) {
	        if (list.get(i-1).compareTo(list.get(i)) > 0) sorted = false;
	    }
	    return sorted;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setStartArray(String startArray) {
		this.startArray = startArray;
	}

	public int getCounter() {
		return counter;
	}

	public long getTime() {
		return time;
	}

	public String getStartArray() {
		return startArray;
	}
}

