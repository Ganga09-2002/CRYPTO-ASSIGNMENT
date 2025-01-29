import java.util.Scanner;

public class ElGamal {
    // Function for modular exponentiation
    public static long modExp(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;

        while (exp > 0) {
            if (exp % 2 == 1) { // If exp is odd
                result = (result * base) % mod;
            }
            exp = exp >> 1; // Divide exp by 2
            base = (base * base) % mod;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long p; // Prime number
        long e1, e2, r, d; // Public and private keys
        long m; // Message
        long c1, c2, decryptedMessage;

        // Input values
        System.out.print("Enter a prime number (p): ");
        p = sc.nextLong();

        System.out.print("Enter e1 (primitive root modulo p): ");
        e1 = sc.nextLong();

        System.out.print("Enter d (private key): ");
        d = sc.nextLong();

        e2 = modExp(e1, d, p);
        System.out.println("Value of e2 (public key e2 = e1^d mod p): " + e2);

        System.out.print("Enter r (random number): ");
        r = sc.nextLong();

        System.out.print("\nEnter the message to encrypt (m): ");
        m = sc.nextLong();

        // Encryption
        c1 = modExp(e1, r, p);
        c2 = (modExp(e2, r, p) * m) % p;

        System.out.println("\nEncrypted values:");
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);

        // Decryption
        long temp = modExp(c1, d, p); // c1^d mod p
        long tempInverse = modExp(temp, p - 2, p); // temp^(p-2) mod p (modular inverse)
        decryptedMessage = (c2 * tempInverse) % p;

        System.out.println("\nDecrypted message: " + decryptedMessage);

        sc.close();
    }
}
