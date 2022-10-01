# lab 2
Option 78.
1. DB "Firm priy. recycled materials". Display information about reception
money at the reception point No. 1 of the Income table. Output data according to
forge by growth by column inc.

2. DB "Airport". Display the last name of the passengers (the second word in the column
name) that do not begin with the letter 'J'.

3. Database "Ships". For the ships that survived the battles, display the names and
the dates of the battles in which they took part.

4. Database "Comp. firm". Find laptop models and prices that are affordable
higher than the cost of any PC. Display: model, price.

5. Database "Comp. firm". Find manufacturers that released at the same time
PCs and laptops with a speed of 750 MHz and higher. Enter: maker.

6. Database "Comp. firm". For the Printer table, display all information from
comments in each cell, for example 'model: 1276', 'price:
400.00', etc.

7. DB "Airport". Determine the number of flights to the city 'Moscow' for
of each date of the Pass_in_trip table. Output: date, number of flights.

8. Database "Comp. firm". Get the summary set for the Product table
in the form of a table with columns maker, pc, laptop and printer, in which for
each manufacturer must specify the number of different products that
it is produced, that is, products with different (unique) numbers
models in the tables, respectively, PC, Laptop and Printer. (Hint:
use subqueries as calculated columns)

9. DB "Firm priy. recycled materials". Determine the leader by the amount of payments
in a competition between each pair of points with the same numbers out of two
of different tables – Outcome and Outcome_o – for each day when
reception of secondary raw materials was carried out at least on one of them.
Output: Item number, date, text: – 'once a day', if there is a payment amount
a larger company with reporting once a day; – 'more than once a
day', if the company reports several times a day; – 'both' if
the amount of payments is the same. (Hint: to connect tables use
to establish a full external connection, and to check the conditions the operator
CASE; for items that do not issue money on certain days - necessary
handle NULL values by checking the IS [NOT] condition
NULL)

10. DB "Ships". Find the names of all the ships in the database that you can
unequivocally say that they were launched before 1942.
you: the name of the ships. (Hint: use the UNION operator)
