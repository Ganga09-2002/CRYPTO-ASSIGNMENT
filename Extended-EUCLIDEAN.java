import java.util.Scanner;

public class ExtendedEuclidean {

    // Function to compute GCD and find modular inverse
    public static void findGCDAndInverse(int a, int m) {
        int r1 = m, r2 = a; // r1 = modulus, r2 = number whose inverse is needed
        int t1 = 0, t2 = 1; // t1 and t2 for tracking coefficients
        
        System.out.println("Step-by-step computation:");
        System.out.println("q\tr1\tr2\tr\tt1\tt2\tt");

        while (r2 > 0) {
            int q = r1 / r2;
            int r = r1 - q * r2;
            r1 = r2;
            r2 = r;

            int t = t1 - q * t2;
            t1 = t2;
            t2 = t;

            System.out.println(q + "\t" + r1 + "\t" + r2 + "\t" + r + "\t" + t1 + "\t" + t2 + "\t" + t);
        }

        System.out.println("\nGCD(" + a + ", " + m + ") = " + r1);

        // If GCD is not 1, inverse does not exist
        if (r1 != 1) {
            System.out.println("Since GCD ≠ 1, modular inverse does not exist.");
            return;
        }

        // Ensure the inverse is positive
        int inverse = (t1 % m + m) % m;
        System.out.println("The modular inverse of " + a + " under modulo " + m + " is: " + inverse);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number and modulus
        System.out.print("Enter number (a): ");
        int a = scanner.nextInt();

        System.out.print("Enter modulus (m): ");
        int m = scanner.nextInt();

        // Compute and display results
        findGCDAndInverse(a, m);

        scanner.close();
    }
}
