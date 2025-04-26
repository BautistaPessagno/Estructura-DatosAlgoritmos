import java.util.Arrays;

public class IndexWithDuplicates  {

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
		int[] ans = new int[cantElems + other.cantElems];
		int i = 0, j = 0, k = 0;
		while (i < indexedData.length && j < other.indexedData.length) { // complejidad del mas largo (suponemos N) ==> O(N)
			if (indexedData[i] <= other.indexedData[j]) {
				ans[k++] = indexedData[i++];
			}
			else {
				ans[k++] = other.indexedData[j++];
			}
		}
		while (i < indexedData.length) {
			ans[k++] = indexedData[i++];
		}
		while (j < other.indexedData.length) { // compleidad O(M)
			ans[k++] = other.indexedData[j++];
		}

		//complejidad total es O(N+M)
		indexedData = ans;
	}

}

