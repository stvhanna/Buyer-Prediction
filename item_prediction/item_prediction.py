import numpy as np
from tables import *

h5file = open_file("click_buy_matrix.h5", "a")
table = h5file.root.detector.readout
# print(table)


popular_items = []
with open("itemid_probabilities.txt") as f1:
    for line in f1.readlines():
    	x = line.split('\t')
    	if(float(x[1]) >= 0.1):
    		popular_items.append(int(x[0]))




session_item_clicks = {}
with open("temp1.txt") as f1:
    for line in f1.readlines():
    	session, item_id, clicks = [int(i) for i in line.split()]
    	try:
    		session_item_clicks[session].append(item_id)
    	except Exception:
    		session_item_clicks[session] = [item_id]


session_item_buys = {}
with open("temp2.txt") as f1:
    for line in f1.readlines():
    	session, item_id, buys = [int(i) for i in line.split()]
    	try:
    		session_item_buys[session].append(item_id)
    	except Exception:
    		session_item_buys[session] = [item_id]



prob = {}
S = 0
N = 0


print(len(session_item_clicks))

# """
for session  in session_item_clicks:
	c_item_ids = session_item_clicks[session]
	l = len(c_item_ids)
	max_prob  = 0.0
	max_item  = -1
	
	flag = 0
	

	for i in range(0 , l):
		t = 0
		y = 0


		if(c_item_ids[i] in popular_items):
			try:
				if(c_item_ids[i] in session_item_buys[session]):
					flag = 1
			except Exception:
				pass
	

		for j in range(0 , l):		
			t = [x['count'] for x in table.iterrows() if(x['clicked_item'] == c_item_ids[j] and x['bought_item'] == c_item_ids[i])]
			#print(t)
			# print(len(t))
			if(len(t) == 1):
				y += float(t[0])
		if(y > max_prob):
			max_prob = y
			max_item = c_item_ids[i]
		#print(session , max_item)
	if(flag == 0):
		try:
			if(max_item in session_item_buys[session]):
				S+=1
			else:
				N+=1
		except Exception:
			if(max_item!=-1):
				N+=1
			else:
				S+=1
	else:
		S = S + 1



print(S , N , S + N)
# 
# """








