import java.util.ArrayList;
import java.util.List;


public class HashTable {
	
	private final double fillFactor = 0.75;
	private int maxItemsAtCurrentSize;
	private HashTableArrayNode[] storage;
	private int count;
	private int currentCapacity;

	public HashTable(){
		this(1024);
	}
	
	public HashTable(int initialCapacity){
		//create array and pre-allocate  buckets
		storage = getInitializedArray(initialCapacity);
		//currentCapacity and maxItemsAtCurrentSize are here to make it easier to determine when to expand the array
		currentCapacity = initialCapacity;
		maxItemsAtCurrentSize = (int)(currentCapacity * fillFactor) + 1;
	}
	
	private int getIndex(Object key){
        return Math.abs(key.hashCode() % storage.length);
    }
	
	private HashTableArrayNode[] getInitializedArray(int capacity){
		HashTableArrayNode[] array = new HashTableArrayNode[capacity];
		for (int i = 0; i < capacity; i++){
            array[i] = new HashTableArrayNode();
        }
		return array;
	}
	
	public int getCount() {
		return count;
	}
	
	public void add(Object key, Object value){
		if(count >= maxItemsAtCurrentSize){
			//our array needs to be expanded, create a new one 
			currentCapacity = currentCapacity * 2;
			HashTableArrayNode[] newStorage = getInitializedArray(currentCapacity);
			//now get each node in the current array and add it to the new array, getting a new index for it
			//	we have to first iterate the nodes, then iterate the pairs, dealing with collisions
			for(HashTableArrayNode node : storage){
				for(HashTableNodePair pair : node){
					//now we can add it to the new array
					int index = Math.abs( pair.getKey().hashCode() % newStorage.length );
					newStorage[ index ].add(pair.getKey(), pair.getValue());
				}
			}
			storage = newStorage;
			maxItemsAtCurrentSize = (int)(currentCapacity * fillFactor) + 1;
			System.out.println("!!!Resized storage to " + currentCapacity );
		}
		
		//add the value to the linked list at the index determined based on the hash of the key
		storage[getIndex(key)].add(key, value);
		count++;
	}
	
	public boolean set(Object key, Object value){
		boolean updated = false;
		for(HashTableArrayNode node : storage){
			updated = node.update(key, value);
			if( updated ){
				break;
			}
		}
		return updated;
	}

	public Object get(Object key){
		HashTableNodePair item = null;
		for(HashTableArrayNode node : storage){
			item = node.get(key);
			if( item != null){
				break;
			}
		}
		return item != null ? item.getValue() : null;
	}
		
	public boolean remove(Object key){
		boolean removed = false;
		for(HashTableArrayNode node : storage){
			removed = node.remove(key);
			if( removed ){
				count--;
				break;
			}
		}
		return removed;
	}
	
	public List<Object> keys(){
		ArrayList<Object> list = new ArrayList<Object>();
		for(HashTableArrayNode node : storage){
			for(HashTableNodePair pair : node){
				list.add(pair.getKey());
			}
		}
		return list;
	}

}
