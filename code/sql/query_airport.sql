--OUTGOING FLIGHTS
SELECT origin_airport_id, COUNT(*) as total_flights
FROM flight
GROUP BY origin_airport_id
ORDER BY total_flights desc 
LIMIT 10;
--INCOMING FLIGHTS
SELECT dest_airport_id, COUNT(*) as total_flights
FROM flight
GROUP BY dest_airport_id
ORDER BY total_flights desc 
LIMIT 10;

--DATE
SELECT fl_date, fl_month, fl_year, COUNT(*) as total_flights
FROM flight
GROUP BY fl_date, fl_month, fl_year
ORDER BY total_flights desc
LIMIT 10;