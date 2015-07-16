
# coding: utf-8

# In[1]:

get_ipython().magic(u'matplotlib inline')
import generate, matplotlib.pyplot as plt


# In[2]:

stat = generate.get_surv()

plt.plot(stat['ages'], stat['survivabilities']);
plt.xlabel('age (in commits)');
plt.ylabel('percentage of survived next 1000 commit (%)');
plt.title('mercurial lindy effect');


# In[ ]:



