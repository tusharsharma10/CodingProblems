package ibit;

import java.util.ArrayList;
import java.util.List;

public class Common {
    
    public static <T>  void printArray(T arr[]){

        for(T x:arr){
            System.out.print(x+" ");
        }
    }


    public  static <T> List<T> arrToList(T arr[]){

        List<T> l = new ArrayList<>();

        for(T x:arr){
            l.add(x);
        }

        return l;
    }


}
