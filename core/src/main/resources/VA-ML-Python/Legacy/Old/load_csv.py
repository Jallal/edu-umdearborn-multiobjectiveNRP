# -*- coding: utf-8 -*-
"""
Created on Thu Jul 27 16:27:33 2017

@author: vahid
"""

from os import path
import pandas as pd
from pandas.tools.plotting import parallel_coordinates
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans


fname = path.expanduser(r'~\Desktop\Refactoring-solutions1-noparam.tab')

##Look at the file
#with open(fname) as fp:
#    for lnum, line in enumerate(fp):
#        if lnum > 3:
#            break
#        print(line[:-1])
        
##How many lines
#with open(fname) as fp:
#    print(sum(1 for line in fp))

df = pd.read_csv(fname, sep='\t')
df.index = df.values[:,0]
df = df.iloc[1:,:]
df = df.iloc[:,1:]

df_positive= df.iloc[:,0:9].apply(np.abs)
selected_columns = ['Ref{}'.format(num) for num in np.arange(1,11)]
df_ref = df.loc[:,selected_columns]

df_final = pd.concat([df_positive,df_ref],axis=1)

##export
#output_name = fname.replace('.tab','-exported-all.csv')
#df_final.to_csv(output_name,index_label='index')
#
#output_name = fname.replace('.tab','-exported-ref.csv')
#df_ref.to_csv(output_name,index_label='index')
#
#output_name = fname.replace('.tab','-exported-obj.csv')
#df_positive.to_csv(output_name,index_label='index')


##Clustering
#kmeans = KMeans(n_clusters=3).fit(df_positive.values)
#df_cluster = df_positive.copy()
#df_cluster['cluster'] = kmeans.labels_

##############################################
##Correlation
correlation = df_positive.corr()

##Plotting
#plt.style.use('ggplot')
#fig = plt.figure()
#plt.title('test')
#parallel_coordinates(df_cluster,'cluster')









