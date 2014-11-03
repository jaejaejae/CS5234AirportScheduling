-- 
SELECT origin_airport_id, COUNT(*) AS total_cencelled_flights
FROM flight
WHERE cancelled = 1
GROUP BY origin_airport_id
ORDER BY total_cencelled_flights DESC;

--
SELECT cancelled_flight.origin_airport_id as airport_id, 1.0*cancelled_flight.total_cencelled_flights/all_flight.total_flights as percentage
FROM 
    (
    SELECT origin_airport_id, COUNT(*) AS total_cencelled_flights
    FROM flight
    WHERE cancelled = 1
    GROUP BY origin_airport_id
    ORDER BY total_cencelled_flights DESC
    ) cancelled_flight,
    (
    SELECT origin_airport_id, COUNT(*) AS total_flights
    FROM flight
    GROUP BY origin_airport_id
    ORDER BY total_flights DESC
    ) all_flight
WHERE cancelled_flight.origin_airport_id = all_flight.origin_airport_id
ORDER BY percentage DESC
LIMIT 10;