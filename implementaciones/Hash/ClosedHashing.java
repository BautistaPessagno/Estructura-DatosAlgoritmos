
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.function.Function;

public class ClosedHashing<K, V> implements IndexParametricService<K, V> {
	final private int initialLookupSize= 10;
	private final double threshold = 0.75;

	// est�tica. No crece. Espacio suficiente...
	@SuppressWarnings({"unchecked"})
	private Slot<K,V>[] lookUp = (Slot<K,V>[]) new Slot[initialLookupSize];
	private int usedKeys = 0;

	private Function<? super K, Integer> prehash;

	public ClosedHashing( Function<? super K, Integer> mappingFn) {
		if (mappingFn == null)
			throw new RuntimeException("fn not provided");

		prehash= mappingFn;
	}

	// ajuste al tama�o de la tabla
	private int hash(K key) {
		if (key == null)
			throw new IllegalArgumentException("key cannot be null");

		return prehash.apply(key) % lookUp.length;
	}

	private void checkData(K key,V data){
		if (key == null || data == null) {
			String msg= String.format("inserting or updating (%s,%s). ", key, data);
			if (key==null)
				msg+= "Key cannot be null. ";

			if (data==null)
				msg+= "Data cannot be null.";

			throw new IllegalArgumentException(msg);
		}
	}

	private void checkSpaceNoColision() {
		if((double) usedKeys/ lookUp.length > threshold) {
			Slot<K, V>[] newLookup = (Slot<K, V>[]) new Slot[lookUp.length * 2];
			for (int rec = 0; rec < lookUp.length; rec++) {
				if (lookUp[rec] != null) {
					int newHash = prehash.apply(lookUp[rec].key) % newLookup.length;
					newLookup[newHash] = lookUp[rec];
				}
			}
			lookUp = newLookup;
		}
	}


	
	public void insertOrUpdate(K key, V data) {
		checkSpaceNoColision();
		checkData(key, data);
		int newKey = hash(key);
		if(lookUp[newKey] != null) {
			if(lookUp[newKey].equals(key)) {
				lookUp[newKey].value = data;
				return;
			}
			else
				throw new IllegalArgumentException("There's another key for slot %d".formatted(newKey % lookUp.length));
		}
		usedKeys++;
		lookUp[newKey] = new Slot<K, V>(key, data);
	}

	@SuppressWarnings("unchecked")
	private void checkSpaceColision(){
		if ((double) usedKeys / lookUp.length > threshold){
			Slot<K, V>[] oldLookup = lookUp;

			lookUp = (Slot<K, V>[]) new Slot[oldLookup.length * 2];

			usedKeys = 0;

			for (Slot<K, V> kvSlot : oldLookup) {
				if (kvSlot != null) insertOrUpdateClosedHashing(kvSlot.key, kvSlot.value);
			}
		}
	}


	public void insertOrUpdateClosedHashing(K key, V data) {
		checkSpaceColision();
		checkData(key, data);
		int newKey = hash(key);
		int count = 0;

		//firstDeleted va a guardar el primer lugar libre lógico que encuentra por si, al finalizar de recorrer,
		//no matcheó ninguna key o no encontró null.
		int firstDeleted = -1;

		while(lookUp[newKey] != null && count < lookUp.length){
			if(lookUp[newKey].key.equals(key)) {
				lookUp[newKey].value = data;
				return;
			}
			if (!(lookUp[newKey] != null) && firstDeleted == -1)
				firstDeleted = newKey % lookUp.length;
			newKey++;
			count++;
		}

		if(firstDeleted != -1)
			lookUp[firstDeleted] = new Slot<>(key,data);
		else
			lookUp[newKey] = new Slot<>(key, data);
		usedKeys++;
	}




	

	
	// find or get
	public V find(K key) {
		if (key == null)
			return null;

		Slot<K, V> entry = lookUp[hash(key)];
		if (entry == null)
			return null;

		return entry.value;
	}

	public boolean remove(K key) {
		if (key == null)
			return false;
		
		// lo encontre?
		if (lookUp[ hash( key) ] == null)
			return false;
		
		lookUp[ hash( key) ] = null;
		return true;
	}

	
	public void dump()  {
		for(int rec = 0; rec < lookUp.length; rec++) {
			if (lookUp[rec] == null)
 				System.out.println(String.format("slot %d is empty", rec));
			else
				System.out.println(String.format("slot %d contains %s",rec, lookUp[rec]));
		}
	}
	

	public int size() {
		return lookUp.length;
	}



	static private final class Slot<K, V>	{
		private final K key;
		private V value;
		
		private Slot(K theKey, V theValue){
			key= theKey;
			value= theValue;
		}

	
		public String toString() {
		 return String.format("(key=%s, value=%s)", key, value );
		}
	}
	
//
	public static void main(String[] args) throws IOException {
		ClosedHashing<Integer, String> myHash= new ClosedHashing<>(f->f);
		myHash.insertOrUpdate(55, "Ana");
		myHash.insertOrUpdate(44, "Juan");
		myHash.insertOrUpdate(18, "Paula");
		myHash.insertOrUpdate(19, "Lucas");
		myHash.insertOrUpdate(21, "Sol");
		myHash.dump();

		System.out.println("STRING COMO CLAVE");
		//ej2
		Function<String, Integer> function = s -> (int)s.charAt(0);

		ClosedHashing<String, String> myHash2= new ClosedHashing<>(function);

		String fileName = "amazon-categories30.txt";
		InputStream is = ClosedHashing.class.getClassLoader().getResourceAsStream(fileName);
		InputStreamReader in = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(in);
		String line;

		while(((line = br.readLine()) != null)){
			Scanner scanner = new Scanner(line).useDelimiter("#");
			String title = scanner.next();
			myHash2.insertOrUpdate(title, line);
		}
		myHash2.dump();


		//ej 3

		System.out.println("SUMA DEL STRING COMO CLAVE");

		function = s -> {
			int sum = 0;
			for (int i = 0; i < s.length(); i++) {
				sum += (int) s.charAt(i); // Sumar el valor ASCII de cada carácter
			}
			return sum;
		};

		ClosedHashing<String, String> myHash3= new ClosedHashing<>(function);

		fileName = "amazon-categories30.txt";
		is = ClosedHashing.class.getClassLoader().getResourceAsStream(fileName);
		in = new InputStreamReader(is);
		br = new BufferedReader(in);

		while(((line = br.readLine()) != null)){
			Scanner scanner = new Scanner(line).useDelimiter("#");
			String title = scanner.next();
			myHash3.insertOrUpdate(title, line);
		}
		myHash3.dump();

	}

//	public static void main(String[] args) {
//		ClosedHashing<Integer, String> myHash= new ClosedHashing<>(f->f);
//		myHash.insertOrUpdate(55, "Ana");
//		myHash.insertOrUpdate(29, "Victor");
//		myHash.insertOrUpdate(25, "Tomas");
//		myHash.insertOrUpdate(19, "Lucas");
//		myHash.insertOrUpdate(21, "Sol");
//		myHash.dump();
//	}

}