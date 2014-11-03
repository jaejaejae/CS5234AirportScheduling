SELECT *
FROM flight
WHERE cancelled = 0 AND dep_delay > 0

SELECT fl_date, fl_month, fl_year, COUNT(*) as total_delay
FROM flight
WHERE cancelled = 0 AND dep_delay > 0
GROUP BY fl_date, fl_month, fl_year
ORDER BY total_delay DESC
LIMIT 10;


SELECT f1.fl_date, f1.fl_month, f1.fl_year, 1.0*f2.total_delay/f1.total_flight as percentage
FROM
	(SELECT fl_date, fl_month, fl_year, COUNT(*) as total_flight
	FROM flight
	WHERE origin_airport_id = 11298
	GROUP BY fl_date, fl_month, fl_year) f1,
	(SELECT fl_date, fl_month, fl_year, SUM(dep_delay) as total_delay
	FROM flight
	WHERE origin_airport_id = 11298
		AND cancelled = 0
		AND dep_delay > 0
	GROUP BY fl_date, fl_month, fl_year ) f2
WHERE f1.fl_date = f2.fl_date
	AND f1.fl_month = f2.fl_month
	AND f1.fl_year = f2.fl_year
ORDER BY percentage DESC
LIMIT 10;