import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comparable is usually implemented on the class with a type of its ownself:
 * i.e. Comparable<Type> here if class is Student then Type in
 * Comparable<Student> will be student, else suppose you put 
 * class Student impelemnts Comparable<Teacher> then no compilation error will come
 * but if you try to call Collections.sort(studentList) then compilation error will come.
 * Bcz it needs Comparable of same type.
 * 
 * Suppose we want to sort one list of Student objects by name and other list
 * say list2 by age then Comparator is the best choice. Since we can make two
 * comparators.
 */

// Here we cant use Comparable bcz we cant use this pointer here.
class ComparatorByName implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        // Comparing code using name/string
        return 0;
    }
}

// Here we cant use Comparable bcz we cant use this pointer here.
class ComparatorByAge implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        // Comparing students by age.
        return 0;
    }

}

class Teacher implements Comparable<Teacher>{

    int age;
    String name;

    public Teacher(int age, String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Teacher o) {
        return this.age - o.age;
    }

}

class Student implements Comparable<Student> {

    int age;
    Double marks;
    String name;

    @Override
    public int compareTo(Student o) {

        if (this.marks > o.marks)
            return 1;
        else if (this.marks < o.marks)
            return -1;

        else {

            return (this.name.compareTo(o.name));

        }
    }

    public Student(int age, Double marks, String name) {

        this.age = age;
        this.marks = marks;
        this.name = name;
    }

}

public class Sample {

    static void isProcessed(String name) {

        System.out.println(name);
        name = new String("krish");
        System.out.println(name);
    }

    public static void main(String[] args) {

        List<Teacher> list = new ArrayList<>();

        list.add(new Teacher(65,"Charan"));
        list.add(new Teacher(45,"Mohnish "));
        list.add(new Teacher(78,"Tushar"));
        list.add(new Teacher(55,"Chandak"));
        list.add(new Teacher(45, "Mohit "));
        list.add(new Teacher(55,  "Wilson "));
        list.add(new Teacher(25, "tarun"));

        Collections.sort(list, (s1, s2) -> {

            return s1.age - s2.age;

        });

        Collections.sort(list);

        list.forEach(s -> System.out.println(s.age + " " + s.name));

        // StringBuilder s = new StringBuilder("tushar");
        // String a = s.toString();
        // isProcessed(a);
        // System.out.println(a);
    }
}
