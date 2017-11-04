package models;

import java.util.ArrayList;
import java.util.List;

public class Logger implements Loggable {
    private List<String> results;

    public Logger() {
        results = new ArrayList<>();
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
        results.add(msg);
    }

    @Override
    public void printFormatted(String format, Object... args) {
        System.out.println(String.format(format, args));
        results.add(String.format(format, args));
    }

    @Override
    public List<String> getResults() {
        return results;
    }
}
