import java.util.Random;

public class RandomNr {
    Random random = new Random();
    int[] a = new int[10];
    public void randomArray(){
        for(int i = 0; i<10; i++) {
            int randomNr = random.nextInt(100) + 1;
            a[i]=randomNr;
            System.out.print(a[i]+" ");
            System.out.println();
        }
    }

    static void bubbleSort(int[] arr, int n)
    {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public void sortedA(){
        for (int i=0; i<10; i++){
            System.out.println(a[i]);
        }
    }
}
