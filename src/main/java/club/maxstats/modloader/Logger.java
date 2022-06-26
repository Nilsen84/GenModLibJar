package club.maxstats.modloader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Logger {
    public static void info(String log) {
        log(String.format("[INFO] %s", log));
    }

    public static void error(String log, Throwable ex) {
        if (ex != null) {
            StringWriter exception = new StringWriter();
            ex.printStackTrace(new PrintWriter(exception));
            error(String.format("%s - %s", log, exception));
        } else {
            error(log);
        }
    }

    public static void error(String log) { log(String.format("[ERROR] %s", log)); }

    public static void severe(String log, Throwable ex) {
        if (ex != null) {
            StringWriter exception = new StringWriter();
            ex.printStackTrace(new PrintWriter(exception));
            severe(String.format("%s - %s", log, exception));
        } else {
            severe(log);
        }
    }

    public static void severe(String log) { log(String.format("[SEVERE] %s", log)); }

    private static void log(String log) {
        try {
            File logFile = new File("");
            if (!logFile.getParentFile().exists()) {
                logFile.getParentFile().mkdirs();
                logFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(logFile, true);

            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("h:mm:ss a", Locale.ENGLISH);
            format.setTimeZone(TimeZone.getDefault());
            String formattedDate = format.format(date);

            fileWriter.write(String.format("[%s] %s\n", formattedDate, log));
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
