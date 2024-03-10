import java.util.Scanner;

public class Complex {
    int real;
    int image;

    public Complex() {

    }

    public Complex(int r, int i)
    {
        this.real = r;
        this.image = i;
    }

    public int getReal() {
        return real;
    }

    public int getImage() {
        return image;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public String toString() {
        return real + " + " + image + "i";
    }
    public static Complex addNumbers(Complex complex1, Complex complex2){
        int newI = complex1.getImage()+ complex2.getImage();
        int newR = complex1.getImage()+ complex2.getImage();
        Complex compAdd = new Complex(newR,newI);
        return compAdd;
    }
    public static Complex subsNumbers(Complex complex1, Complex complex2){
        int newI = complex1.getImage()- complex2.getImage();
        int newR = complex1.getImage()- complex2.getImage();
        Complex compSub = new Complex(newR,newI);
        return compSub;
    }
    public static Complex multiplyNumbers(Complex complex1, Complex complex2){
        int newR = complex1.getReal()* complex2.getReal()-complex1.getImage()*complex2.getImage();
        int newI = complex1.getImage()* complex1.getReal()+ complex2.getImage()* complex2.getReal();
        Complex compMul = new Complex(newR,newI);
        return compMul;
    }

    public void complexMenu(){
        System.out.println("Hello! Type in the number for the operation you would like to do!");
        System.out.println("1 - addition");
        System.out.println("2 - subtraction");
        System.out.println("3 - multiplication");

        Scanner s = new Scanner(System.in);
        int operation = s.nextInt();

        System.out.println("Type in the two complex numbers!");
        Scanner nr = new Scanner(System.in);
        int r1 = nr.nextInt();
        Scanner nr1 = new Scanner(System.in);
        int i1 = nr1.nextInt();
        Complex complex1 = new Complex(r1,i1);
        Scanner nr2 = new Scanner(System.in);
        int r2 = nr2.nextInt();
        Scanner nr3 = new Scanner(System.in);
        int i2 = nr3.nextInt();
        Complex complex2 = new Complex(r2,i2);

        System.out.println("Complex Number 1: " + complex1.toString());
        System.out.println("Complex Number 2: " + complex2.toString());

        switch(operation) {
            case 1:
                Complex sum = Complex.addNumbers(complex1, complex2);
                System.out.println("Sum: " + sum.toString());
                break;
            case 2:
                Complex diff = Complex.subsNumbers(complex1, complex2);
                System.out.println("Sum: " + diff.toString());
                break;
            case 3:
                Complex prod = Complex.multiplyNumbers(complex1, complex2);
                System.out.println("Sum: " + prod.toString());
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

}
