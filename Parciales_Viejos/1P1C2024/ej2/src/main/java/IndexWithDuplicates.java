import java.util.Arrays;

import static java.lang.System.arraycopy;

public class IndexWithDuplicates {

	final static private int chunksize= 5;

	private int[] indexedData;
	private int cantElems;
	
	
	public IndexWithDuplicates() {
		indexedData= new int[chunksize];
		cantElems= 0;
	}

	public void initialize(int[] unsortedElements) {

		if (unsortedElements == null)
			throw new RuntimeException("Problem: null data collection");

		indexedData= unsortedElements;
		Arrays.sort(indexedData);
		cantElems= indexedData.length;
	}



	public int[] getIndexedData() {
		return indexedData;
	}

	public void print() {
		System.out.print("[");
		for (int i : indexedData)
			System.out.print(i + " ") ;
		System.out.println("]");
		
	}

	public void merge(IndexWithDuplicates other) {
		int[] ans = new int[cantElems+other.cantElems];
		int i = 0, j = 0, k=0;
		while(i < cantElems && j < other.cantElems) {
			if(indexedData[i] <= other.indexedData[j]) {
				ans[k++] = (indexedData[i++]);
			}
			else{
				ans[k++] = (other.indexedData[j++]);
			}
		}
		while(i < cantElems) {
			ans[k++] = (indexedData[i++]);
		}
		while(j < other.cantElems) {
			ans[k++] = (other.indexedData[j++]);
		}
		this.indexedData = ans;
		this.cantElems = ans.length;
	}

	public void dump() {
		System.out.print("[");
		for (int i = 0; i < cantElems; i++) {
			System.out.print(indexedData[i] + " ");
		}
		System.out.println("]");
	}

}

