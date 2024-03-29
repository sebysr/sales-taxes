[![Build Status](https://travis-ci.org/Bhudjo/sales-taxes.svg?branch=master)](https://travis-ci.org/Bhudjo/sales-taxes.svg?branch=master
)

The following exercise is sent to you as an additional tool to be used in your technical interview.
We ask you to solve it using the Java programming language.
This problem requires some kind of input. You are free to implement any mechanism for feeding
input into your solution (for example, using hard coded data within a unit test). You should
provide sufficient evidence that your solution is complete by, as a minimum, indicating that it
works correctly against the supplied test data.


The goal is to provide us with a full understanding of your coding style and skills. We’ll pay
particular attention to:
- the code structure
- the design
- choice of data structures
- how you approach the problem


We kindly ask you to put your code on a public project in github.com under your account, use
Maven (or Gradle or another build tool) as automation tool and link it to a build service likeTravis
CI.


#PROBLEM: SALES TAXES

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax applicable on all imported goods
at a rate of 5%, with no exemptions.


When I purchase items I receive a receipt which lists the name of all the items and their price
(including tax), finishing with the total cost of the items, and the total amounts of sales taxes
paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains
(np/100 rounded up to the nearest 0.05) amount of sales tax.


Write an application that prints out the receipt details for these shopping baskets...


##INPUT:

####Input 1:
- 1 book at 12.49
- 1 music CD at 14.99
- 1 chocolate bar at 0.85

####Input 2:
- 1 imported box of chocolates at 10.00
- 1 imported bottle of perfume at 47.50

####Input 3: 
- 1 imported bottle of perfume at 27.99
- 1 bottle of perfume at 18.99
- 1 packet of headache pills at 9.75
- 1 box of imported chocolates at 11.25

##OUTPUT

####Output 1:
- 1 book : 12.49
- 1 music CD: 16.49
- 1 chocolate bar: 0.85
- Sales Taxes: 1.50
- Total: 29.83

####Output 2:
- 1 imported box of chocolates: 10.50
- 1 imported bottle of perfume: 54.65
- Sales Taxes: 7.65
- Total: 65.15

####Output 3:
- 1 imported bottle of perfume: 32.19
- 1 bottle of perfume: 20.89
- 1 packet of headache pills: 9.75
- 1 imported box of chocolates: 11.85
- Sales Taxes: 6.70
- Total: 74.68


###SOME USEFUL REFERENCE TO HELP YOU WITH THE EXERCISE
- Test­Driven Development: By Example . ​Addison­Wesley. Winner of the Jolt Productivity Award. 2002. (ISBN 978­0321146533)
- Clean Code: A Handbook of Agile Software Craftsmanship. ​Prentice Hall PTR. 2008. (ISBN 0­13­235088­2.)
- Martin Fowler’s web site http://martinfowler.com/
