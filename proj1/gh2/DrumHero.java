package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class DrumHero {
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        /* create 37 guitar strings*/
        Drum[] stringArray = new Drum[37];
        for (int i = 0; i < 37; i++) {
            stringArray[i] = new Drum(CONCERT_A * Math.pow(2, (i-24) / 12.0));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();

                // Need to check whether an available key is typed
                int idx = keyboard.indexOf(key);
                if(idx == -1){
                    continue;
                }

                stringArray[idx].pluck();

            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < stringArray.length; i++) {
                sample += stringArray[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < stringArray.length; i++) {
                stringArray[i].tic();
            }
        }
    }
}

