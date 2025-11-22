import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 1_000_000;
    static boolean[] isPrime = new boolean[MAX + 1];
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sieve(); // 소수 1번만

        while (true) {
            int N = Integer.parseInt(br.readLine().trim());
            if (N == 0) break;

            boolean found = false;

            for (int p : list) {
                if (p > N / 2) break;      // a는 N/2까지만 보면 됨
                if (isPrime[N - p]) {      // N-p도 소수면 바로 답
                    sb.append(N).append(" = ")
                      .append(p).append(" + ")
                      .append(N - p).append("\n");
                    found = true;
                    break;
                }
            }

            if (!found) {
                sb.append("Goldbach's conjecture is wrong.\n");
            }
        }

        System.out.print(sb.toString());
    }

    private static void sieve() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= MAX; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= MAX; j += i) {
                isPrime[j] = false;
            }
        }

        list.add(2);
        for (int i = 3; i <= MAX; i += 2) {
            if (isPrime[i]) list.add(i);
        }
    }
}
