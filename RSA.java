import java.math.BigInteger;
import java.util.Scanner;

public class RSA {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Take input for prime numbers
        System.out.print("Enter first prime number (p): ");
        int p = scanner.nextInt();

        System.out.print("Enter second prime number (q): ");
        int q = scanner.nextInt();

        // Step 2: Compute n and φ(n)
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        System.out.println("The value of φ(n) = " + phi);

        // Step 3: Input public exponent 'e'
        int e;
        while (true) {
            System.out.print("Enter the public exponent (e): ");
            e = scanner.nextInt();
            if (gcd(e, phi) == 1) {
                break; // Valid e
            } else {
                System.out.println("Invalid e! It must be coprime with " + phi);
            }
        }
        System.out.println("The public exponent (e) = " + e);

        // Step 4: Compute private exponent 'd'
        int d = computeD(e, phi);
        System.out.println("The private exponent (d) = " + d);

        // Print the public and private keys
        System.out.println("Public Key: (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + d + ", " + n + ")");

        // Step 5: Encrypt & decrypt a number
        System.out.print("Enter a number to encrypt: ");
        int num = scanner.nextInt();
        BigInteger encryptedNum = encryptNumber(num, e, n);
        System.out.println("Encrypted number: " + encryptedNum);

        int decryptedNum = decryptNumber(encryptedNum, d, n);
        System.out.println("Decrypted number: " + decryptedNum);

        scanner.close();
    }

    // Function to compute gcd
    static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    // Compute private exponent 'd' using Extended Euclidean Algorithm
    static int computeD(int e, int phi) {
        int d = 0, x1 = 0, x2 = 1, y1 = 1, tempPhi = phi;

        while (e > 0) {
            int q = tempPhi / e;
            int r = tempPhi - q * e;
            tempPhi = e;
            e = r;

            int x = x2 - q * x1;
            x2 = x1;
            x1 = x;

            int y = d - q * y1;
            d = y1;
            y1 = y;
        }

        if (tempPhi == 1) {
            return (d + phi) % phi; // Ensure d is positive
        }
        throw new RuntimeException("No valid private exponent found.");
    }

    // Encrypt a single number
    static BigInteger encryptNumber(int num, int e, int n) {
        return BigInteger.valueOf(num).modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));
    }

    // Decrypt a single number
    static int decryptNumber(BigInteger encryptedNum, int d, int n) {
        return encryptedNum.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n)).intValue();
    }
}
