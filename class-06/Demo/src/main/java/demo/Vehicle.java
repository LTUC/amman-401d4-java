package demo;

public interface Vehicle {
    
    // this is ok
    // never specify implementation
    void honkHorn();
    
    // cannot have this in interface
//    void revEngine() {
//        System.out.println("Cannot have implementation");
//    }
}
