import java.util.Scanner;

public class Fibnacci {

    public static void main(String args[]) {

        var scanner = new Scanner(System.in);

        System.out.println("Informe a sequência de Fibonacci que deseja saber:");
        int input = scanner.nextInt();

        int result = calculateFibonacci(input);

        System.out.println("A seqência fibonacci é: " + result);

        scanner.close();
    }

    private static int calculateFibonacci(int input) {
        int numeros[] = new int[input + 1];
        numeros[0]= 0;

        int i = 0;
        while(i< numeros.length){
            if(input == 1){
                return 1;
            }
            if(i <= 1){
                numeros[i] = i;
            }
            else
            {
                numeros[i] = numeros[i-1] + numeros[i-2];
            }
            i++;
        }
       
        return numeros[i-1];

    }
    

}