package example3;

import java.util.Objects;

public class Main {

    public static int variable = 0;

    public static void main(String[] args){

        int num1=50001;
        int num2=20001;

        JoinTestThread w1 = new JoinTestThread("Thread 1",null,50001);
        JoinTestThread w2 = new JoinTestThread("Thread 2",w1,20001);

        w1.start();
        w2.start();

    }

}
