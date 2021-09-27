import java.util.ArrayList;

public class ArrayListTesting {

  public static void main(String[] args) {
  
      int[] arr=new int[5];  
    int arrayLength=arr.length;  
    
    System.out.println("the size of the arr is: " + arrayLength);
  
    
    ArrayList<String> arrayList = new ArrayList<>();
    
    arrayList.add("one"); // index 0
    
    arrayList.add("two"); // index 1
    
    arrayList.add("three"); // index 2
    
    arrayList.add(0, "zero"); // what will this add??
    
    arrayList.add("four"); // index 3
    
    arrayList.add("five"); // index 4
    
    arrayList.add("six"); //index 5
    
    ArrayList<Integer>
                arrlist2 = new ArrayList<Integer>();
            arrayList.add("one"); // index 0
    
    arrayList.add("two"); // index 1
    
    arrayList.add("three"); // index 2
    
    arrayList.removeAll(arrlist2);
    
    for (String elt : arrayList) {
    
      System.out.println(elt);
      
    }
    
    System.out.println("the size of the array list is: " + arrayList.size());
    
    /*
    arrayList.add("seven");
    
    
    for (String elt : arrayList) {
    
      System.out.println(elt);
      
    }
    
    */
    
  }
}
