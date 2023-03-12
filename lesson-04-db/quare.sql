USE lesson05;

-- ---------------------------------------------------------------------------------------------------------------
-- Ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
-- ---------------------------------------------------------------------------------------------------------------
SELECT f1.name, s.start_film, f1.duration, f2.name, f2.start_film, f2.duration
FROM sessions AS s
INNER JOIN films AS f1
		ON f1.id = s.film_id
CROSS JOIN (SELECT f.id AS id, f.name AS name, s.start_film AS start_film, f.duration AS duration
			FROM sessions AS s
			INNER JOIN films AS f ON f.id = s.film_id) AS f2
WHERE s.start_film < f2.start_film AND ADDTIME(s.start_film, f1.duration) >= f2.start_film
ORDER BY s.start_film;

-- ---------------------------------------------------------------------------------------------------------------
-- Перерывы 30 минут и более между фильмами - выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
-- ---------------------------------------------------------------------------------------------------------------
SELECT f1.name, s.start_film, f1.duration, f2.name, f2.start_film, f2.duration, TIME(SUBTIME(f2.start_film, TIME(ADDTIME(s.start_film, f1.duration)))) AS break_time
FROM sessions AS s
INNER JOIN films AS f1
		ON f1.id = s.film_id
CROSS JOIN (SELECT f.id AS id, f.name AS name, s.start_film AS start_film, f.duration AS duration
			FROM sessions AS s
			INNER JOIN films AS f ON f.id = s.film_id) AS f2
WHERE ADDTIME(s.start_film, ADDTIME(f1.duration, TIME("00:30:00"))) <= f2.start_film
ORDER BY break_time DESC;

-- ---------------------------------------------------------------------------------------------------------------
-- Список фильмов, для каждого - с указанием общего числа посетителей за все время,
-- среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
-- ---------------------------------------------------------------------------------------------------------------
SELECT IF(GROUPING(sa.name), 'Итого', sa.name) AS name, SUM(sa.number_of_visitors) AS total_visitors, AVG(sa.number_of_visitors) AS average_visitors, SUM(sa.box_office) AS total_box_office
FROM (SELECT f.name AS name, COUNT(*) AS number_of_visitors, COUNT(*) * s.ticket_price AS box_office
      FROM sessions AS s
	  INNER JOIN films AS f ON f.id = s.film_id
	  INNER JOIN tickets AS t ON t.session_id = s.id
      GROUP BY s.id) AS sa
GROUP BY sa.name
WITH ROLLUP
ORDER BY total_box_office DESC;

-- ---------------------------------------------------------------------------------------------------------------
-- Число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
-- с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.)
-- ---------------------------------------------------------------------------------------------------------------
SELECT "с 9 до 15 частов" AS start_time, COUNT(*) AS number_of_visitors, sessions.ticket_price * COUNT(*) AS box_office
FROM tickets
INNER JOIN sessions ON sessions.id = tickets.session_id
INNER JOIN films ON films.id = sessions.film_id
WHERE TIME(sessions.start_film) >= "09:00:00" AND TIME(sessions.start_film) < "15:00:00"
UNION
SELECT "с 15 до 18 частов" AS start_time, COUNT(*) AS number_of_visitors, sessions.ticket_price * COUNT(*) AS box_office
FROM tickets
INNER JOIN sessions ON sessions.id = tickets.session_id
INNER JOIN films ON films.id = sessions.film_id
WHERE TIME(sessions.start_film) >= "15:00:00" AND TIME(sessions.start_film) < "18:00:00"
UNION
SELECT "с 18 до 21 частов" AS start_time, COUNT(*) AS number_of_visitors, sessions.ticket_price * COUNT(*) AS box_office
FROM tickets
INNER JOIN sessions ON sessions.id = tickets.session_id
INNER JOIN films ON films.id = sessions.film_id
WHERE TIME(sessions.start_film) >= "18:00:00" AND TIME(sessions.start_film) < "21:00:00"
UNION
SELECT "с 21 до 24 частов" AS start_time, COUNT(*) AS number_of_visitors, sessions.ticket_price * COUNT(*) AS box_office
FROM tickets
INNER JOIN sessions ON sessions.id = tickets.session_id
INNER JOIN films ON films.id = sessions.film_id
WHERE TIME(sessions.start_film) >= "21:00:00" AND TIME(sessions.start_film) < "23:59:59";
