import numpy as np
from tables import *

#Preparing a file to store data for pytables
class Matrix(IsDescription):
	clicked_item = Int64Col()
	bought_item = Int64Col()
	count = Float64Col()

h5file = open_file("click_buy_matrix.h5", mode = "w", title = "Test file")
group = h5file.create_group("/", 'detector', 'Buy Click information')
table = h5file.create_table(group, 'readout', Matrix, "Readout example")

matrix_row = table.row


#Storing session information
session_item_clicks = {}
session_item_buys = {} 

with open("temp1.txt") as f1:
    for line in f1.readlines():
    	session, item_id, clicks = [int(i) for i in line.split()]
    	try:
    		session_item_clicks[session].append(item_id)
    	except Exception:
    		session_item_clicks[session] = [item_id]


with open("temp2.txt") as f1:
    for line in f1.readlines():
    	session, item_id, buys = [int(i) for i in line.split()]
    	try:
    		session_item_buys[session].append(item_id)
    	except Exception:
    		session_item_buys[session] = [item_id]

c_b_count = {}

#Storing click buy data
click_count = {}
for session  in session_item_clicks:
	c_item_ids = session_item_clicks[session]
	l = len(c_item_ids)
	for i in range(0 , l):
		for j in range(0 , l):
				try:
					click_count[(c_item_ids[i] , c_item_ids[j])]+=1
				except Exception:
					click_count[(c_item_ids[i] , c_item_ids[j])] = 1




for session  in session_item_clicks:
	if session not in session_item_buys.keys():
		continue	
	c_item_ids = session_item_clicks[session]
	for c_item_id in c_item_ids:
		for b_item_id in session_item_buys[session]:
			try:
				c_b_count[(c_item_id, b_item_id)] += 1
			except Exception:
				c_b_count[(c_item_id, b_item_id)] = 1
			

for session  in session_item_clicks:
	if session not in session_item_buys.keys():
		continue	
	c_item_ids = session_item_clicks[session]
	for c_item_id in c_item_ids:
		for b_item_id in session_item_buys[session]:
			# try:
				c_b_count[(c_item_id, b_item_id)] = float(c_b_count[(c_item_id, b_item_id)])/float(click_count[(c_item_id, b_item_id)])
			# except Exception:
				# c_b_count[(c_item_id, b_item_id)] = 1
			





for c_b in c_b_count:
	count = c_b_count[c_b]
	matrix_row['clicked_item'] = c_b[0]
	matrix_row['bought_item'] = c_b[1]
	matrix_row['count'] = count
	matrix_row.append()
#print(c_b_count)

#Flushing this data into pytable file 
table.flush()
#h5file.close()


table = h5file.root.detector.readout











