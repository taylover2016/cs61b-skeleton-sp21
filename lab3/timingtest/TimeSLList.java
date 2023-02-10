package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // Determine the size range
        int[] range = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        int M = 10000;

        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        for (int i = 0; i < range.length; i++) {
            int N = range[i];

            Ns.addLast(N);
            opCounts.addLast(M);
            SLList<Integer> repo = new SLList<>();

            // Prepare the repo
            for (int j = 0; j < N; j++) {
                repo.addLast(j);
            }

            // Start the timer
            Stopwatch sw = new Stopwatch();
            // Perform M=10000 times getLast()
            for (int j = 0; j < M; j++) {
                repo.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }

        // Print the tab
        printTimingTable(Ns, times, opCounts);
    }

}
