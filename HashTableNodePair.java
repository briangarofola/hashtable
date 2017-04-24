public class HashTableNodePair {
	
	public HashTableNodePair(){
		
	}
	
	public HashTableNodePair(Object key, Object value){
		this.key = key;
		this.value = value;
	}

	private Object key;
	private Object value;
	
	public Object getKey() {
		return key;
	}
	public void setKey(Object key) {
		this.key = key;
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
