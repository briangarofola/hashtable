import java.io.IOException;


public class HashTableMain {

	public static void main(String[] args) throws IOException {
        HashTable table = new HashTable(3000);
        table.add("a", Math.abs(Math.random()));
        table.add("b", Math.abs(Math.random()));
        table.add("c", Math.abs(Math.random()));
        table.add("d", Math.abs(Math.random()));
        table.add("e", Math.abs(Math.random()));
        table.add("f", Math.abs(Math.random()));
        table.add("g", Math.abs(Math.random()));
       
        System.out.println("Count: " + table.getCount());
        for(Object key : table.keys()){
        	System.out.println(String.format("%s: %s", key, table.get(key)));
        }
        
        table.set("g", Math.abs(Math.random()));
        System.out.println("---");
        System.out.println(String.format("%s: %s", "g", table.get("g")));
        
        table.remove("g");
        
        System.out.println("---");
        System.out.println("Count: " + table.getCount());
        for(Object key : table.keys()){
        	System.out.println(String.format("%s: %s", key, table.get(key)));
        }
    }
	
}
