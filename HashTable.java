
public class HashTable {

	int numberOfEntries;
	int numberOfSlots;
	String[] hashingArray;
	int hashIndex;
	
	//constructor
	HashTable(int size){
		numberOfEntries = 0;
		numberOfSlots = size;
		hashingArray = new String[numberOfSlots + 1];//adding one because we don't use the first slot in index 0 b/c of mod.
	}
	
	//hashing function with quadratic probing
	//the type of ascii hashing function that is passed to the second parameter will decide what type of hash it will be
	public void hash(String newEntry, int ascii)
	{
		hashIndex = ascii % numberOfSlots;
		int n = 1;
		while((hashingArray[hashIndex] != null) && (n <= (numberOfSlots + 1)/2) )
		{
				hashIndex = hashIndex + n^2;
				hashIndex = hashIndex % numberOfSlots;
				n++;
		}
		
		hashingArray[hashIndex] = newEntry;
	}
	
	public void delete(int ascii)
	{
		hashingArray[ascii] = "DELETED";
	}
	
	//the delete function utilizes the lazy deletion method
	public void delete(String entry)
	{
		int ascii;
		ascii = findBasic(entry); //find should return an index
		delete(ascii);
	}
	
	public int findBasic(String entry)
	{
		int tempKey = basicAscii(entry);
		while (hashingArray[tempKey] != null)
		{
			if (hashingArray[tempKey] != entry)
			{
				tempKey++;
				tempKey = tempKey % numberOfEntries;
			}
			else break;
			
		}
		return tempKey;
	}
	
	public int findShifted(String entry, int shift)
	{
		int tempKey = shiftedAscii(entry, shift);
		while (hashingArray[tempKey] != null)
		{
			if (hashingArray[tempKey] != entry)
			{
				tempKey++;
				tempKey = tempKey % numberOfEntries;
			}
			else break;
			
		}
		return tempKey;
	}
	
	public int findPoly(String entry)
	{
		int tempKey = polynomialAscii(entry);
		while (hashingArray[tempKey] != null)
		{
			if (hashingArray[tempKey] != entry)
			{
				tempKey++;
				tempKey = tempKey % numberOfEntries;
			}
			else break;
			
		}
		return tempKey;
	}
	
	/**
	 *As far as I can tell, it is not reasonable to have all three types of hash function in one hash table. Therefore,
	 *I have written three different functions to show them, but if this program were in real life,
	 *my team would choose a method and take out the functions that are not needed.
	 *
	 *For this reason, there is also three different find() methods, which correspond to each hashing method. Again, the 
	 *appropriate one would be chosen either by the team, or by the user when the hash table object is created.
	 *
	 * @param newEntry	is the one-word string that is to be hashed into the table.
	 * @return
	 */
	public int basicAscii(String newEntry)
	{
		int ascii = 0;
		for(int i = 0; i < newEntry.length(); i++)
		{
			
			ascii += newEntry.codePointAt(i);
			
		}
		return ascii;
	}
	
	//This method has two parameters, so the user can specify the size of the shift.
	public int shiftedAscii(String newEntry, int sizeOfShift)
	{
		int ascii = 0;
		int tempAscii;
		
		for(int i = 0; i < newEntry.length(); i++)
		{
			
			tempAscii = newEntry.codePointAt(i);
			tempAscii = Integer.rotateRight(tempAscii, sizeOfShift);
			ascii += tempAscii;
		}
		return ascii;
	}
	
	public int polynomialAscii(String newEntry){
		int ascii = 0;
		int tempAscii;
		
		for(int i = 0; i < newEntry.length(); i++){
			tempAscii = newEntry.codePointAt(i);
			tempAscii = (int) (tempAscii * Math.pow(2,  newEntry.length()-i -1));
			ascii += tempAscii;
		}
		return ascii;
	}
}
