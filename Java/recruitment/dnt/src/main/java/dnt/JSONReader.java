package dnt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Timestamp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Responsible for reading from JSON-formatted file
 */
public class JSONReader {
    
    /**
     * Reads JSON file and extracts records 
     * 
     * @param filePath - path of readable JSON file
     * @return list of read records
     */
    public List<Record> parseFile(String filePath) {
        Gson gson = new Gson();
        List<Record> records = new ArrayList<Record>();

        JsonReader reader;
        try {
            JsonObject jsonObject = gson.fromJson(new FileReader(filePath), JsonObject.class);

            JsonArray array = jsonObject.getAsJsonArray("records");
            for (JsonElement record : array) {
                Record toAdd = createRecord( (JsonObject) record );
                if (toAdd != null)
                    records.add(toAdd);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return records;
    }
    
    /**
     * maps JSON object to Record iff Timestamp value is proper (not before 2017-07-01).
     * @param record - record to create as Record object
     * @return Record object
     */
    public Record createRecord(JsonObject record) {
        Timestamp comparisonTimestamp = Timestamp.valueOf("2017-07-01 00:00:00");
        Timestamp recordTimestamp = Timestamp.valueOf(record.get("kontakt_ts").getAsString());

        if (recordTimestamp.before(comparisonTimestamp))
            return null;
        return new Record(record.get("kontakt_id").getAsInt(),
                        record.get("klient_id").getAsInt(),
                        record.get("pracownik_id").getAsInt(),
                        Statuses.valueOf(record.get("status").getAsString()),
                        Timestamp.valueOf(record.get("kontakt_ts").getAsString())
                    );
    }

    /**
     * Sorts a list by klient_id and kontakt_ts using Comparator interface
     * @param list - records list to sort
     * @return sorted list of records
     */
    public List sort (List<Record> list){
        Comparator<Record> comparator = Comparator.comparing(Record::getKlient_id);
        comparator = comparator.thenComparing(Record::getKontakt_ts);

        Stream<Record> personStream = list.stream().sorted(comparator);
        List<Record> sortedList = personStream.collect(Collectors.toList());
        return sortedList;
    }
}