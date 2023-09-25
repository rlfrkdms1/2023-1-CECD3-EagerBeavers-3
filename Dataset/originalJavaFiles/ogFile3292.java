import java.util.Arrays;
import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        long fib[] = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, 2971215073L, 4807526976L, 7778742049L, 12586269025L, 20365011074L, 32951280099L, 53316291173L, 86267571272L, 139583862445L, 225851433717L, 365435296162L, 591286729879L, 956722026041L, 1548008755920L, 2504730781961L, 4052739537881L, 6557470319842L, 10610209857723L };
        int i = Arrays.binarySearch(fib, new Scanner(System.in).nextLong());
        if (i < 4)
            if (i == 3)
                System.out.println("0 1 1");
            else if (fib[i] == 1)
                System.out.println("0 0 1");
            else
                System.out.println("0 0 0");
        else
            System.out.println(fib[i - 4] + " " + fib[i - 3] + " " + fib[i - 1]);
    }

}
