
# coding: utf-8

# In[1]:

get_ipython().magic(u'matplotlib inline')
import generate, matplotlib.pyplot as plt


# In[2]:

stat = generate.get_surv()

plt.subplot()
plt.plot(stat['ages'], stat['accumulated_survivabilities'], label='> (greater then, total)');
plt.plot(stat['ages'], stat['current_survivability'], label='~ (roughly equal, +- 500 commits)');
plt.legend(bbox_to_anchor=(1.05, 1), loc=2, borderaxespad=0.)
plt.xlabel('age (in commits)');
plt.ylabel('percentage of survived next 1000 commit (%)');
plt.title('mercurial lindy effect');


# In[ ]:



