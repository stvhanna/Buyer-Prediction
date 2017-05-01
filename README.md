Problem :
	Given a sequence of click events performed by some user during a typical session in an e-commerce website, the goal is to predict whether the user is going to buy something or not, and if he is buying, what would be the items he is going to buy. The task could therefore be divided into two sub goals:

	1 . Is the user going to buy items in this session? Yes|No
	2 . If yes, what are the items that are going to be bought?


The training data comprises two different files:

yoochoose-clicks.dat - Click events. Each record/line in the file has the following fields:
	Session ID – the id of the session. In one session there are one or many clicks.
	Timestamp – the time when the click occurred.
	Item ID – the unique identifier of the item.
	Category – the category of the item.
yoochoose-buys.dat - Buy events. Each record/line in the file has the following fields:
	Session ID - the id of the session. In one session there are one or many buying events.
	Timestamp - the time when the buy occurred.
	Item ID – the unique identifier of item.
	Price – the price of the item.
	Quantity – how many of this item were bought.
The Session ID in yoochoose-buys.dat will always exist in the yoochoose-clicks.dat file – the records with the same Session ID together form the sequence of click events of a certain user during the session. The session could be short (few minutes) or very long (few hours), it could have one click or hundreds of clicks. All depends on the activity of the user.


data_extraction:
	We used Hadoop-MapReduce to get following information from the data .
	Some of the Features extracted are 
	1. buy/click ratio of the session
	2. number of clicks in the session
	3. day of the week of the session first click
	4. hour of the day of the session first click
	5. number of clicks made on the maximum clicked item of the session
	6. max time between two consecutive clicks of the session
	7. number of popular items clicked in the session 
	8. time spent in sec
	9. month of the year of the session first click
	10. Average time of the session (total time spent / number of clicks)

Plots :
	Plots for above features are put in this folder . Importance of feature can be described here .


buy-not buy classification model :
	We used XGboost trees to predict wheather the user is going to buy some thing or not.
	Accuracy : ~0.98



Item-to-item matrix factorization
	
	(code in matrix-construction folder)

	(i,j)th entry of the matrix stores the (number of sessions in which j is bought among the sessions in which (i, j) are clicked)/(number of sessions in which i and j are clicked together)

	Score of  i-item in a session = sum of (j , i) score for all j clicked in the session 

	Popularity of items based on buy/click ratio in the whole data and used the data to predict item .







