package dnt;

import java.util.List;
import java.util.ArrayList;

/**
 * Main class, manages both component classes
 */
public class App {

    public static void main(String[] args) {
        JSONReader reader = new JSONReader();
        List<Record> records = reader.parseFile("dnt/src/main/java/dnt/statuses.json");
        records = reader.sort(records);
        new CSVHandler().writeCSVFile("dnt/src/main/java/dnt/out.csv", records, ';' );
    }


}
