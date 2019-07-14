#1

SELECT klient_id
FROM statuses AS main
WHERE klient_id IN (SELECT 
			klient_id
			FROM statuses
			GROUP BY klient_id 
			HAVING COUNT(klient_id)  > 2) 
AND kontakt_ts = (  SELECT MAX(helper.kontakt_ts)
					FROM statuses AS helper
                    WHERE helper.klient_id = main.klient_id);


SELECT klient_id, status
FROM statuses AS main
WHERE ( SELECT 
		COUNT(*)
		FROM statuses
        WHERE statuses.klient_id = main.klient_id) > 2
AND kontakt_ts = (  SELECT MAX(helper.kontakt_ts)
					FROM statuses AS helper
                    WHERE helper.klient_id = main.klient_id
                    ORDER BY helper.kontakt_id DESC LIMIT 1);
                    

#2
INSERT INTO f_docieralnosc 
SELECT  helper.data, 
        (CASE helper.status WHEN "zainteresowany" THEN helper.count ELSE 0 END) AS sukcesy, 
        (CASE helper.status WHEN "niezainteresowany" THEN helper.count ELSE 0 END) AS utraty,
        (CASE WHEN helper.status IN ("poczta_glosowa", "nie_ma_w_domu") THEN helper.count ELSE 0 END) AS do_ponowienia,
        0,
        0
        FROM (SELECT status, CAST(kontakt_ts AS DATE) as data, COUNT(*) as count FROM statuses GROUP BY status, data) 
		AS helper 
        GROUP BY helper.data;