USE labor_sql;
-- 1 
SELECT * 
FROM income
WHERE point = 1
ORDER BY inc;

-- 2 
SELECT SUBSTRING_INDEX(name, ' ', -1) AS surname
FROM labor_sql.passenger
WHERE name NOT RLIKE '[[:space:]]J[[:alpha:]]';

-- 3 
SELECT DISTINCT name, date
FROM battles
JOIN outcomes ON battle = name
WHERE result = 'OK';

-- 4 
SELECT DISTINCT model, price
FROM laptop
WHERE price > ALL (SELECT price FROM pc);

-- 5 
SELECT DISTINCT maker 
FROM labor_sql.product, pc
WHERE product.type = 'PC' AND pc.speed >= 750
AND maker IN (SELECT maker FROM laptop, product WHERE product.type = 'Laptop' AND laptop.speed >= 750);

-- 6 
SELECT CONCAT('code: ', code) AS code,
CONCAT('model: ', model) AS model,
CONCAT('color: ', color) AS color,
CONCAT('type: ', type) AS type,
CONCAT('price: ', price) AS price
FROM printer;

-- 7 
SELECT date, COUNT(trip_to_moscow) trip_to_Moscow
FROM (SELECT DISTINCT town_to AS trip_to_moscow FROM trip WHERE town_to = 'Moscow') tr, labor_sql.pass_in_trip
LEFT JOIN trip ON pass_in_trip.trip_no = trip.town_to
GROUP BY date;

-- 8 
SELECT maker, COUNT(pc_count) AS pc, COUNT(laptop_count) AS laptop, COUNT(printer_count) AS printer FROM product 
LEFT JOIN (SELECT model AS printer_count, model FROM printer) pr USING(model)
LEFT JOIN (select model AS laptop_count, model FROM laptop) lp USING(model) 
LEFT JOIN (select model AS pc_count, model FROM pc) pc USING(model) 
GROUP BY maker;

-- 9 
SELECT outcome.point, outcome.date, 
CASE 
	WHEN SUM(outcome.out) > IFNULL(outcome_o.out, 0) THEN 'more than once a day'
    WHEN SUM(outcome.out) < outcome_o.out THEN 'once a day'
    ELSE 'both'
END AS Winner
FROM labor_sql.outcome
LEFT JOIN labor_sql.outcome_o ON outcome.point = outcome_o.point AND outcome.date = outcome_o.date 
GROUP BY point, date;

-- 10 
SELECT name
FROM labor_sql.ships
WHERE launched < 1942
UNION 
SELECT ship 
FROM labor_sql.outcomes
JOIN battles ON battle = battles.name
WHERE EXTRACT(YEAR FROM date) < 1942;