"""
Created on 3/26/2018 , 2:31 AM

@author: vahid
"""

#########################################IMPORTS###########################################
import os
import sys

import numpy as np
import pandas as pd

from sklearn.mixture import GaussianMixture as GMM
import scipy.stats
import matplotlib.pyplot as plt

sys.path.insert (0 , os.path.abspath ('..'))

from VA_Tools import refactoringsolution as RS
from scipy.spatial import distance
import VA_Tools.designmetrics as DM
from VA_ML import clustering_tools as CL
from VA_Tools import config as cfg

###########################################################################################
# region Load and Export
# Load Refactorings
instance_RS_1 = RS.RefactoringSolution ('output-e7940b29-obj-0.txt' , 'output-e7940b29-var-0.txt')
instance_RS_2 = RS.RefactoringSolution ('output-e7940b29-obj-1.txt' , 'output-e7940b29-var-3.txt')

labels = pd.read_csv (instance_RS_1.ref_directory + "ClusterLabels-e7940b29-0.csv" , header=None)
centers = pd.read_csv (instance_RS_1.ref_directory + "ClusterCenters-e7940b29-0.csv")

Dim1 = 0
Dim2 = 2

X1 = instance_RS_1.obj.values
X2 = instance_RS_2.obj.values

plt.scatter (X1[ : , Dim1 ] , X1[ : , Dim2 ] , c=labels.iloc[ : , 1 ].values , s=200 , label="objectives")
plt.scatter (X2[ : , Dim1 ] , X2[ : , Dim2 ] , c="r" , marker='*' , s=200 , label="Centers")
plt.xlabel ("Objective {}".format (Dim1))
plt.ylabel ("Objective {}".format (Dim2))
plt.legend (loc=2)
plt.show ()

numClusters = np.unique (labels.iloc[ : , 1 ].values).size
allDistance = np.zeros ([ 1 , numClusters ])
for i in range (numClusters):
    distToCluster = 0
    for index , row in instance_RS_2.obj.iterrows ():
        dst = distance.euclidean (row , centers.iloc[ i , 2: ])
        distToCluster += dst

    allDistance[ 0 , i ] = distToCluster / instance_RS_2.obj.size
