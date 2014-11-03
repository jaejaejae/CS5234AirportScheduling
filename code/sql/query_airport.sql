--OUTGOING FLIGHTS
SELECT origin_airport_id, COUNT(*) as origin_total_flights
FROM flight
GROUP BY origin_airport_id
ORDER BY origin_total_flights desc 
LIMIT 10;
--INCOMING FLIGHTS
SELECT dest_airport_id, COUNT(*) as dest_total_flights
FROM flight
GROUP BY dest_airport_id
ORDER BY dest_total_flights desc 
LIMIT 10;
--FLIGHTS
SELECT airport_id, COUNT(*) as total_flights
FROM flight,
        (
        SELECT origin_airport_id as airport_id
        FROM flight
        UNION
        SELECT dest_airport_id as airport_id
        FROM flight)
WHERE dest_airport_id = airport_id OR origin_airport_id = airport_id
GROUP BY airport_id
LIMIT 10;


SELECT origin_airport_id
FROM flight
UNION
SELECT dest_airport_id
FROM flight;


--DATE
SELECT fl_date, fl_month, fl_year, COUNT(*) as date_total_flights
FROM flight
WHERE origin_airport_id = 11298 OR dest_airport_id = 11298
GROUP BY fl_date, fl_month, fl_year
ORDER BY date_total_flights desc
LIMIT 10;


