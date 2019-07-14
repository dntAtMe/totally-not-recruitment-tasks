package dnt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSVHandler {
    
    public void writeCSVFile(String filePath, List<Record> records, char separator) {
        
        try {
            FileWriter outputFile = new FileWriter( new File(filePath) );
            CSVWriter writer = new CSVWriter(outputFile, separator);
            for (Record record : records) {
                writer.writeNext(new String[] {String.valueOf(record.getKontakt_id()), 
                                            String.valueOf(record.getKlient_id()), 
                                            String.valueOf(record.getPracownik_id()),
                                            record.getStatus().name(), 
                                            record.getKontakt_ts().toString()
                                });
            }
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}