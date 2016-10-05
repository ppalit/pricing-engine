# pricing-engine
An online retail company conducts market research to competitively price their products.
Surveyed data contains Product code, Competitor and Price.
 
The retail company uses a Pricing engine which recommends most frequently occurring price. If multiple prices occur frequently, the least amongst them is chosen.
 
Products are classified based on parameters like Supply, Demand. Possible values are Low (L), High (H)
 
If Supply is High and Demand is High, Product is sold at same price as chosen price.
If Supply is Low and Demand is Low, Product is sold at 10 % more than chosen price.
If Supply is Low and Demand is High, Product is sold at 5 % more than chosen price.
If Supply is High and Demand is Low, Product is sold at 5 % less than chosen price.
 
Prices less than 50% of average price are treated as promotion and not considered.
Prices more than 50% of average price are treated as data errors and not considered.

Input consists of number of products, followed by each Product's supply and demand parameters.
followed by number of surveyed prices, followed by competitor prices.
 
Output must be recommended price for each product.
 
Input 1:
2
flashdrive H H
ssd L H
5
flashdrive X 1.0
ssd X 10.0
flashdrive Y 0.9
flashdrive Z 1.1
ssd Y 12.5
 
Output 1:
A 0.9
B 10.5
 
Input 2:
2
mp3player H H
ssd L L
8
ssd W 11.0
ssd X 12.0
mp3player X 60.0
mp3player Y 20.0
mp3player Z 50.0
ssd V 10.0
ssd Y 11.0
ssd Z 12.0
 
Output 2:
A 50.0
B 12.1

#Build and execute instructions:
clone or download the project and run:   
mvn clean install		
 jar is created in /target folder of the project
and then run java -jar pricing-engine.jar

