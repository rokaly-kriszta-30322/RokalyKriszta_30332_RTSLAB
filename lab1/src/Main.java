import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Complex c=new Complex();
        c.complexMenu();

        Matrix obj = new Matrix();
        Matrix obj2 = new Matrix();
        obj.addMatrix();
        obj.showAddMatrix();
        obj2.multiplyMatrix();
        obj2.showMultiplyMatrix();

        RandomNr r=new RandomNr();
        r.randomArray();
        r.bubbleSort(r.a, 10);
        System.out.println();
        r.sortedA();
    }
    
}