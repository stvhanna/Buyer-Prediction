"""
Plots categories vs buys/clicks ratio

"""

import matplotlib.pyplot as plt
import matplotlib.ticker as mticker
fig, ax = plt.subplots()

plt.xlabel('Categories')
plt.ylabel('buys/clicks ratio')

item_category = {}

category_buy = {}

category_click = {}


with open("./item_clicks.txt") as f1:
    for line in f1.readlines():
    	category, item_id, num_clicks = [int(i) for i in line.split()]
    	item_category[item_id] = category
        try:
            category_click[category] = category_click[category] + num_clicks
        except Exception, e:
            category_click[category] = num_clicks

with open("./item_buys.txt") as f2:
    for line in f2.readlines():
    	item_id, num_buys = [int(i) for i in line.split()]
    	try:
            category_buy[item_category[item_id]] = category_buy[item_category[item_id]] + num_buys
        except Exception, e:
            category_buy[item_category[item_id]] = num_buys

#Plotting all session
plt.plot(category_click.keys(), [(category_buy[cat]*1.00)/category_click[cat] for cat in category_click.keys()], 'bo')

#ax.set_xscale("log", nonposx='clip')
#ax.set_yscale("log", nonposy='clip')

#Adding legend
#plt.legend(loc = 'lower right', ncol = 1, shadow = True, fancybox = True, numpoints = 1)
#plt.ylim([0, 2])
plt.show()


