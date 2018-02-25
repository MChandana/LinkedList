/************************************************************************************
 * @file BSA2.java
 *
 * @author  John Miller
 * @version 1.0, Wed Sep 27 15:16:52 EDT 2017
 */

import static java.lang.Math.*;
import static java.lang.System.out;

/****************************************************************************************
 * Binary Search Analysis: Expected and Worst Cases
 */
public class BSA2
{
    private static double sumup (int n, int h)
    {
        double sum = 0.0;
        for (int i = 1; i <= h; i++) sum += i * pow (2, i-1);
        sum += (h + 1) * (n + 1 - pow (2 , h));
        return sum / (double) n;

    } // sumup

    private static double log2 (double x) { return  log (x) / log (2); }

    public static void main (String [] args)
    {
        int    h;   // tree height
        double l;   // log n (base 2)
        double e;   // expected number of probes (Knuth - Sorting and Searching, p.  413)
        double f;   // expected number of probes (using sumup)
        double w;   // worst-case number of probes - floor (log2 (n)) + 1
        double v;   // worst-case number of probes - ceil (log2 (n+1))

        String ls;
        String es;
        String fs;
        String ws;
        String vs;

        out.println ("n \th \tlog n \texp \texp2 \tworst \tworst2");

        for (int n = 1; n <= 100; n++) {
            l = log2 (n);
            h = (int) floor (l);
            e = h + 1 - ((2 * pow (2, h) - h - 2) / (double) n);
            f = sumup (n, h);
            w = h + 1;
            v = (int) ceil (log2 (n + 1));

            ls = Double.toString (l);
            ls = ls.substring (0, min (7, ls.length ()));
            es = Double.toString (e);
            es = es.substring (0, min (7, es.length ()));
            fs = Double.toString (f);
            fs = fs.substring (0, min (7, fs.length ()));
            ws = Double.toString (w);
            ws = ws.substring (0, min (7, ws.length ()));
            vs = Double.toString (v);
            vs = vs.substring (0, min (7, vs.length ()));

            out.println (n + "\t" + h + "\t" + ls + "\t" + es + "\t" + fs + "\t" + ws + "\t" + vs);
//          out.println ("e = " + e + "\tf = " + f + "\tf- e = " + (f - e));
        } // for

    } // main

} // BSA2 class
