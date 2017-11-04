package models;

import java.util.List;

public interface Loggable {
    void print(String msg);
    void printFormatted(String format, Object... args);
    List<String> getResults();
}
