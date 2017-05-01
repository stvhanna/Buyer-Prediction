"""
d = {}
e = {}
counter = 1
l = []
with open("session_item_buys.txt", "r") as f:
  for line in f:
#  		print(counter)
  		p = line.split(" ")
  		ses = int(p[0])
  		q = p[1].split("\t")
  		itm = int(q[0])
  		clk = int(q[1])
  		# if((ses , itm ) in d.keys()):
  			# d[(ses , itm)]  = d[(ses , itm)] + 1			
  		# else:
  		d[(ses , itm)]  = clk
  		# counter = counter + 1	

with open("session_item_clicks.txt", "r") as f:
  for line in f:
#  		print(counter)
  		p = line.split(" ")
  		ses = int(p[0])
  		q = p[1].split("\t")
  		itm = int(q[0])
  		clk = int(q[1])
  		# if((ses , itm ) in d.keys()):
  			# d[(ses , itm)]  = d[(ses , itm)] + 1			
  		# else:
  		e[(ses , itm)]  = clk
  		# counter = counter + 1	


val = [0]*100

for i in e:
	l.append([e[i] , i])
l.sort()
l.reverse()
for i in l:
	if( i[1] in d.keys() ):
		if(d[i[1]] > val[e[i[1]]]):
			val[e[i[1]]] = d[i[1]]
		#print(e[i[1]] , d[i[1]])
	# else:
		# print(e[i[1]] , 0)	
for i in range(1 , 9):
	print(i , val[i])


temp = {}

with open('session_clicks.txt' , 'r') as f:
  for line in f:
    p = line.split('\t')
    temp[int(p[0])] = 0.0


with open('session_popular_clicked_probability.txt' , 'r') as f:
  for line in f:
    p = line.split('\t')
    temp[int(p[0])] = float(p[1])

for k in sorted(temp):
  print(k , temp[k])
"""


d = []
q = 1
with open("session_item_clicks.txt", "r") as f:
  for line in f:
    p = line.split("\t")
    q = p[0].split(' ')
    # d.append( list( int(q[0])  , int(q[1]) , int(p[1])) )
    x = []
    x.append(int(q[0]))
    x.append(int(q[1]))
    x.append(int(p[1]))
    d.append(x)
d.sort()

for i in d:
  print(i[0] , i[1] , i[2])




