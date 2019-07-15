package dnt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

/**
 * Responsible for writing to file in CSV format
 */
public class CSVHandler {
    
    /**
     * writes all Record objects to specified output file
     * 
     * @param filePath - path of an output file
     * @param records - list of Record objects to write
     * @param separator - separator for CSV entries
     */
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