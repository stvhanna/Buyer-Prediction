from datetime import datetime
from datetime import timedelta

# buys = {}
# clicks = {}
# session_max = {}
# session_min = {}

# for i in range(0 , 33010):
	# buys[i] = 0
	# clicks[i] = 1

# for i in range(0 , 11562161):
	# session_max[i] = 0
	# session_min[i] = 100000


"""
with open("session_buys.txt", "r") as f:
  for line in f:
  	p = line.split('\t')
  	k = int(p[0])
  	v = int(p[1])
  	if(k < 33010):
  		buys[k] = v
# print(buys)
print("buys done")
# """
"""
with open("session_clicks.txt", "r") as f:
  for line in f:
  	p = line.split('\t')
  	k = int(p[0])
  	v = int(p[1])
  	if(k < 33010):
  		clicks[k] = v
"""

print("clicks done")
# """
print("is the number of seconds is 0 means that there is only one click in the session")
with open("timestamps_all.txt", "r") as f:
  for line in f:
    l = line.split('\t')    
    p = l[1].split(',')
    q = []
    p.pop()
    p.pop()
    for s in p:
      if(len(s)>=5):
        q.append(datetime.strptime(s, "%Y-%m-%dT%H:%M:%S.%fZ"))
    d = 0
    temp = []
    for i in range(0 , len(q))	:
      for j in range(i + 1 , len(q)):
        t = (q[j] - q[i]).seconds
        if(t < 0):
          temp = q[i]
          q[i] = q[j]
          q[j] = temp
    cnt = len(q)
    tt  = 0 
    t = 0
    if(cnt >= 1):
      tt = (q[cnt -1 ] - q[0]).seconds    
      t = tt/cnt
    print(l[0] , "\t" , t)
    



