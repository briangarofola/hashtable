import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class HashTableArrayNode implements Iterable<HashTableNodePair> {
	
	private LinkedList<HashTableNodePair> items;
	
	public HashTableArrayNode(){
		items = new LinkedList<HashTableNodePair>();
	}
	
	public void add(Object key, Object value){
		for(HashTableNodePair pair : items){
            if (pair.getKey().equals(key)){
            	throw new IllegalArgumentException("The collection already contains the key");
            }
        }
		
		items.addFirst(new HashTableNodePair(key, value));
	}
	
	public boolean remove(Object key){
		//iterate the linked list of items and remove the item if we find the key
		boolean removed = false;
		ListIterator<HashTableNodePair> iterator = items.listIterator();
		while(iterator.hasNext()){
			if(iterator.next().getKey().equals(key)){
				iterator.remove();
				removed = true;
			}
		}
		return removed;
	}
	
	public boolean update(Object key, Object value){
		//iterate the linked list and set the item if we find the key
		boolean updated = false;
		ListIterator<HashTableNodePair> iterator = items.listIterator();
		while(iterator.hasNext()){
			if(iterator.next().getKey().equals(key)){
				iterator.set(new HashTableNodePair(key, value));
				updated = true;
			}
		}
		return updated;
	}

	public HashTableNodePair get(Object key){
		HashTableNodePair item = null;
		ListIterator<HashTableNodePair> iterator = items.listIterator();
		while(iterator.hasNext()){
			item = iterator.next();
			if(item.getKey().equals(key)){
				return item;
			}
		}
		return null;
	}
	
	@Override
	public Iterator<HashTableNodePair> iterator() {
		return items.iterator();
	}

}
