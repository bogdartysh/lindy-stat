
# coding: utf-8

# In[1]:

get_ipython().magic(u'matplotlib inline')
import generate, matplotlib.pyplot as plt


# In[2]:

stat = generate.get_surv()


# In[3]:

plt.subplot()
plt.plot(stat['ages'], stat['accumulated_survivabilities'], label='> (greater then, total)');
plt.plot(stat['ages'], stat['current_survivability'], label='~ (roughly equal, +- ' + str(generate.step/2) + ' commits)');
plt.legend(bbox_to_anchor=(1.05, 1), loc=2, borderaxespad=0.)
plt.xlabel('age (in commits)');
plt.ylabel('percentage of survived next ' + str(generate.step) + ' commit (%)');
plt.title(generate.project_name + ' lindy effect');


# In[ ]:



