package com.sj4j.utils;

/**
 * Java Log implement for android.util.Log.
 * Created by thomas on 2015/8/27.
 */
public class JLog {

    public static boolean PRINTABLE = true;

    /**
     * Priority constant for the println method; use Log.v.
     */
    private static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    private static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    private static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    private static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    private static final int ERROR = 6;

    public static int minLevel = DEBUG;
    private static String Separator = "-";

    public static void v(String tag, String msg) {
        if (PRINTABLE && VERBOSE >= minLevel) {
            System.out.println(buildLevel(VERBOSE) + Separator + buildTag(tag) + Separator + msg);
        }
    }

    public static void d(String tag, String msg) {
        if (PRINTABLE && DEBUG >= minLevel) {
            System.out.println(buildLevel(DEBUG) + Separator + buildTag(tag) + Separator + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (PRINTABLE && INFO >= minLevel) {
            System.out.println(buildLevel(INFO) + Separator + buildTag(tag) + Separator + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (WARN >= minLevel) {
            System.out.println(buildLevel(WARN) + Separator + buildTag(tag) + Separator + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (ERROR >= minLevel) {
            System.out.println(buildLevel(ERROR) + Separator + buildTag(tag) + Separator + msg);
        }
    }

    public static String buildTag(String tag) {
        return "[" + tag + "]";
    }

    private static String buildLevel(int verbose) {
        switch (verbose) {
            case VERBOSE:
                return "[V]";

            case DEBUG:
                return "[D]";

            case INFO:
                return "[I]";
            case WARN:
                return "[W]";
            case ERROR:
                return "[E]";
        }
        return "[X]";
    }

}
