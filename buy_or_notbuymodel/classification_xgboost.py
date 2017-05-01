from numpy import loadtxt
import xgboost as xgboost
from xgboost import XGBClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import numpy as np

#dataset=loadtxt('pima-indians-diabetes.csv', delimiter=",")
#x=dataset[:,0:8]
#y=dataset[:,8]
#seed=1
#test_size=0.67
max_length = 9249729
train_length = max_length - 10000

x = []
y = []
for i in range(0 , max_length):
	l = []
	x.append(l)


"""
counter = 0
fle = open("final_data/session_buy_click_ratio.txt" , "r")
for line in fle:
	temp = line.split('  ')
	x[counter].append(float(temp[1].split('\n')[0]))
	counter = counter + 1
	if(counter >= max_length):
		break


counter = 0
fle = open('final_data/session_clicks.txt' , 'r')
for line in fle:
	temp = line.split('\t')
	#print(temp[1].split('\n')[0] , counter)
	#print(int(temp[1].split('\n')[0]))
	x[counter].append(int(temp[1]))
	counter = counter + 1
	if(counter >= max_length):
		break



counter = 0
fle = open('final_data/session_day_of_week.txt' , 'r')
for line in fle:
	temp = line.split('\t')
	#print(temp[1]) , 	
	#print(int(temp[1]))
	x[counter].append(int(temp[1]))
	counter = counter + 1
	if(counter >= max_length):
		break


counter = 0
fle = open('final_data/session_hour.txt' , 'r')
for line in fle:
	temp = line.split('\t')
	#print(temp)
	x[counter].append(int(temp[1]))
	counter = counter + 1
	if(counter >= max_length):
		break





counter = 0
fle = open('final_data/session_max_item_clicks.txt' ,'r')
for line in fle:
	temp = line.split('  ')
	#print(temp)
	x[counter].append(int(temp[1]))
	counter = counter + 1
	if(counter >= max_length):
		break





counter = 0
fle = open('final_data/session_max_time_between_clicks.txt' , 'r')
for line in fle:
	temp= line.split('\t')
	#print(int(temp[1]))
	x[counter].append(int(temp[1]))
	counter = counter + 1
	if(counter >= max_length):
		break




counter = 0
fle = open('final_data/session_popular_clicked_probability.txt' , 'r')
for line in fle:
	temp = line.split(' ')
	x[counter].append(float(temp[1]))
	counter = counter + 1
	if(counter >= max_length):
		break


#print(x)


counter = 0
fle = open('final_data/session_time_spent_in_sec.txt' , 'r')
for line in fle:
	temp = line.split('\t')
	x[counter].append(int(temp[1]))
	counter = counter + 1
	if(counter >= max_length):
		break


#print(x)

"""


counter = 0
fle = open("final_data/session_buy_bool.txt" , "r")
for line in fle:
	temp = line.split(' ')
	y.append(int(temp[1]))
	counter = counter + 1
	if(counter >= max_length):
		break

#print(y)



#x_train,x_test,y_train,y_test=train_test_split(np.asarray(x) , np.asarray(y) ,test_size=test_size,random_state=seed)

x_train = np.asarray(x)[:train_length]
x_test  = np.asarray(x)[train_length:]
y_train = np.asarray(y)[:train_length]
y_test  = np.asarray(y)[train_length:]

#print(x)

#print(x_train , x_test , y_train , y_test)


		

"""

model=xgboost.XGBClassifier()

model.fit(x_train,y_train)

"""


#print(model)

P = 0
for i in y_train:
	if(i == 1):
		P = P + 1
print(P)
P = 0
for i in y_test:
	if(i == 1):
		P = P + 1
print(P)
"""
"""
#y_pred = model.predict(x_test)

#predictions=[round(value) for value in y_pred]

#accuracy = accuracy_score(y_test, predictions)
counter = 0

"""
S = 0
N = 0
O = 0
for i in y_pred:
	if(y_pred[counter] == y_test[counter]):
		S = S + 1	
	else:
		N = N + 1
		if(y_pred[counter] == 1):
			O = O + 1
	counter = counter  + 1

print(O , S ,N)
accuracy = float(S)/float(S + N)

print(accuracy)
"""





