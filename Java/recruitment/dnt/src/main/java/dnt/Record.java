package dnt;

import java.sql.Timestamp;

/**
 * Holds all information of a record from statuses.json file
 */
public class Record {
    private static final String formatted = "kontakt_id: %d\nklient_id: %d\npracownik_id: %d\nstatus: %s\ntimestamp: %s\n";
    private int kontakt_id;
    private int klient_id;
    private int pracownik_id;
    private Statuses status;
    private Timestamp kontakt_ts;

    public Record(int kontakt_id, int klient_id, int pracownik_id, Statuses status, Timestamp kontakt_ts) {
        this.kontakt_id = kontakt_id;
        this.klient_id = klient_id;
        this.pracownik_id = pracownik_id;
        this.status = status;
        this.kontakt_ts = kontakt_ts;
    }

    @Override
    public String toString() {
        return String.format(formatted, kontakt_id, klient_id,
                pracownik_id, status, kontakt_ts);
    }

    public int getKontakt_id() {
        return kontakt_id;
    }
    
    public int getKlient_id() {
        return klient_id;
    }

    public int getPracownik_id() {
        return pracownik_id;
    }

    public Statuses getStatus() {
        return status;
    }

    public Timestamp getKontakt_ts() {
        return kontakt_ts;
    }

}