# -*- coding: utf-8 -*-
"""
Created on Sat Aug 12 21:54:43 2017

@author: vahid
"""

print (__doc__)

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
import VA_Tools.designmetrics as DM
from VA_ML import clustering_tools as CL
from VA_Tools import config as cfg

###########################################################################################
# region Load and Export
# Load Refactorings
instance_RS = RS.RefactoringSolution ('fun.fun' , 'var.var')
instance_RS.export_to_csv ()

# Load Design Metrics
instance_DM = DM.DesignMetrics ()
# instance_DM.export()
# endregion
###########################################################################################
# region Clustering
BestNumberClusters = CL.BestClusteringGMM (instance_RS.obj)

X = instance_RS.obj.values
gmm = GMM (n_components=BestNumberClusters , covariance_type='full' , max_iter=1000 , tol=0.00001).fit (X)
ClusterLabels = gmm.predict (X)
ClustersProb = gmm.predict_proba (X)

centers = np.empty (shape=(gmm.n_components , X.shape[ 1 ]))
centers_id = np.empty (shape=(gmm.n_components , 1))
for i in range (gmm.n_components):
    density = scipy.stats.multivariate_normal (cov=gmm.covariances_[ i ] , mean=gmm.means_[ i ]).logpdf (X)
    centers[ i , : ] = X[ np.argmax (density) ]
    centers_id[ i ] = np.argmax (density)

Dim1 = 0
Dim2 = 1

plt.scatter (X[ : , Dim1 ] , X[ : , Dim2 ] , c=ClusterLabels , s=200 , label="objectives")
plt.scatter (centers[ : , Dim1 ] , centers[ : , Dim2 ] , c="r" , marker='*' , s=500 , label="Centers")
plt.xlabel ("Objective {}".format (Dim1))
plt.ylabel ("Objective {}".format (Dim2))
plt.legend (loc=2)
plt.show ()

clusterDFColNames = instance_RS.obj.columns.tolist ()
clusterDFColNames.insert (0 , 'point_id')
ClusterCenters = pd.DataFrame (np.concatenate ((centers_id , centers) , axis=1)
                               , columns=clusterDFColNames)
ClusterCenters.point_id = ClusterCenters.point_id.astype (int)
ClusterCenters.to_csv (cfg.Config.ref_directory + "ClusterCenters.csv" , index_label="Cluster#")
# endregion
###########################################################################################
ClassesFreqCollection = {}
OperationsFreqCollection = {}
for target in range (BestNumberClusters):
    print ("Calculating Frequency of Classes and Operations for Cluster {}".format (target))
    classes = instance_RS.ref_classes[ ClusterLabels == target ]
    operations = instance_RS.ref_noparam[ ClusterLabels == target ]

    from collections import Counter

    AllClassesCountDict = {}
    AllClassesWeightProbDict = {}

    for idx , row in classes.iterrows ():
        ClassesDict = pd.value_counts (row).to_dict ()
        Weight = ClustersProb[ idx , target ]
        AllClassesCountDict = dict (Counter (AllClassesCountDict) + Counter (ClassesDict))
        ClassesDict.update ((key , value * Weight) for key , value in ClassesDict.items ())
        AllClassesWeightProbDict = dict (Counter (AllClassesWeightProbDict) + Counter (ClassesDict))
        # only positive count values will remain in the dictionary

    FinalClassFrequencyDF = pd.DataFrame (list (AllClassesCountDict.items ()) , columns=[ 'Classes' , 'Count' ])
    CountsList = FinalClassFrequencyDF.loc[ : , [ 'Count' ] ]
    CountsSum = np.sum (CountsList)
    ProbList = CountsList / CountsSum
    FinalClassFrequencyDF[ 'Probability' ] = ProbList

    WeightedCountsList = list (AllClassesWeightProbDict.values ())
    WeightedCountsSum = np.sum (WeightedCountsList)
    WeightedProbList = WeightedCountsList / WeightedCountsSum
    FinalClassFrequencyDF[ 'Weighted_Probability' ] = WeightedProbList
    FinalClassFrequencyDFSorted = FinalClassFrequencyDF.sort_values ('Probability' , ascending=False)

    ClassesFreqCollection[ target ] = FinalClassFrequencyDFSorted

    # =================================================================
    AllOperationsCountDict = {}
    AllOperationsWeightProbDict = {}

    for idx , row in operations.iterrows ():
        OperationsDict = pd.value_counts (row).to_dict ()
        Weight = ClustersProb[ idx , target ]
        AllOperationsCountDict = dict (Counter (AllOperationsCountDict) + Counter (OperationsDict))
        OperationsDict.update ((key , value * Weight) for key , value in OperationsDict.items ())
        AllOperationsWeightProbDict = dict (Counter (AllOperationsWeightProbDict) + Counter (OperationsDict))
        # only positive count values will remain in the dictionary

    FinalOperationsFrequencyDF = pd.DataFrame (list (AllOperationsCountDict.items ()) ,
                                               columns=[ 'Operations' , 'Count' ])
    CountsList = FinalOperationsFrequencyDF.loc[ : , [ 'Count' ] ]
    CountsSum = np.sum (CountsList)
    ProbList = CountsList / CountsSum
    FinalOperationsFrequencyDF[ 'Probability' ] = ProbList

    WeightedCountsList = list (AllOperationsWeightProbDict.values ())
    WeightedCountsSum = np.sum (WeightedCountsList)
    WeightedProbList = WeightedCountsList / WeightedCountsSum
    FinalOperationsFrequencyDF[ 'Weighted_Probability' ] = WeightedProbList
    FinalOperationsFrequencyDFSorted = FinalOperationsFrequencyDF.sort_values ('Probability' , ascending=False)

    OperationsFreqCollection[ target ] = FinalOperationsFrequencyDFSorted
###########################################################################################
metrics = instance_DM.DesignMetrics
ClassNames = metrics.loc[ : , 'Class Name' ]
AllClustersMetrics = pd.DataFrame ({} , columns=metrics.columns)
AllClustersMetrics.rename (index=str , columns={"Class Name": "Cluster"} , inplace=True)

for target in range (BestNumberClusters):
    classes = instance_RS.ref_classes[ ClusterLabels == target ]
    ClusterUniqueClasses = pd.unique (classes.values.ravel ())
    ClusterMetrics = metrics.iloc[ np.isin (ClassNames , ClusterUniqueClasses) , : ]
    ClusterAverage = np.average (ClusterMetrics.drop ('Class Name' , 1).values , axis=0)
    # ClusterAverageDF=pd.DataFrame(['Cluster']+list(ClusterAverage))
    AllClustersMetrics.loc[ target ] = [ 'Cluster {}'.format (target) ] + list (ClusterAverage)







#
#
#
#
#
#
# metrics = self.output_original.copy ()
# classes = solution_obj.ref_classes.fillna ('none')
# methods = solution_obj.ref_noparam.fillna ('none')
#
# methods_list = methods.values.ravel ()
# classes_list = classes.values.ravel ()
#
# labelsDF_columns = [ 'AllMethodsCount' ,
#                      'ExtractClass' , 'ExtractSubClass' , 'ExtractSuperClass' ,
#                      'MoveField' , 'MoveMethod' ,
#                      'PullUpField' , 'PullUpMethod' ,
#                      'PushDownField' , 'PushDownMethod' ,
#                      'EncapsulateField' ,
#                      'IncreaseMethodSecurity' , 'DecreaseMethodSecurity' ,
#                      'IncreaseFieldSecurity' , 'DecreaseFieldSecurity' ]
#
# labelsDF_zeroMat = np.matlib.zeros ((metrics.__len__ () , labelsDF_columns.__len__ ()))
# labelsDF = pd.DataFrame (labelsDF_zeroMat , columns=labelsDF_columns)
#
# for idx in metrics.index:
#     methods_for_class = methods_list[ classes_list == metrics.ClassName[ idx ] ]
#     methods_used = pd.value_counts (
#         methods_for_class).to_dict ()  # {dictionary} of frequency of methods used for a specific class
#     sum_methods = sum (methods_used.values ())
#
#     labelsDF.AllMethodsCount[ idx ] = sum_methods
#     for key , value in methods_used.items ():
#         labelsDF[ key ][ idx ] = value / sum_methods
#
# LabeledMetrics = pd.concat ([ metrics , labelsDF ] , axis=1)
#
# self.output_LabeledMetrics = LabeledMetrics
#
# print ('Metrics have been labeled with the applied methods!')
#




###########################################################################################
# instance_DM = DM.DesignMetrics('vahid-output-design-metrics/Design-Metrics.csv')
# metrics = instance_DM.output_original
#
#
# instance_DM.labeled_metrics(instance_RS)
# LabeledMetrics = instance_DM.output_LabeledMetrics
# print("Number of affected classes: {}".format(sum(LabeledMetrics.AllMethodsCount!=0)))
# print("Percentage of affected classes: {0:.2f}".format(sum(LabeledMetrics.AllMethodsCount!=0) / len(LabeledMetrics) * 100 ) )
# # instance_DM.export()
#
# X = LabeledMetrics.iloc[:,1:13]
# Y = LabeledMetrics.iloc[:,14:]
# Y[Y>0] = 1
#
# X = X.values
# Y = Y.values
# classif = DecisionTreeClassifier(random_state=0)
# CV_scores = model_selection.cross_val_score(classif, X , Y , cv=5)
# print("Average CV accuracy of Decision Tree: {}".format(round(np.mean(CV_scores),2)*100))
#
# ########## CLUSTERING K-means
# from VA_ML import clustering_tools as CL
# from VA_ML import discretize_obj as DISC
# from sklearn.cluster import KMeans
# import matplotlib.pyplot as plt
# #
# obj = instance_RS.obj
# best_num_cluster = CL.best_clustering (obj)
#
# # Clustering
# y_pred = KMeans (n_clusters=best_num_cluster , random_state=150).fit_predict (obj.values)
#
# # plot
# plt.figure(figsize=(12, 12))
# plt.scatter(obj.values[:, 4], obj.values[:, 7], c=y_pred)
# plt.title("Clusters")
# #
# # Objects discretization
# obj_disc = DISC.discretized_eq_fre (obj , 3)
# for col in obj_disc.columns:
#     obj_disc[col] = col + obj_disc[col]
#
# solution_df = pd.concat ([ obj_disc , instance_RS.ref_noparam ] , axis=1)
# solution_df = solution_df.fillna ('None')
#
# #Extracting clusters
# cluster_idx = np.where(y_pred == 1)
#
# # Statistics of the objectives in each cluster
# from tabulate import tabulate
# print(tabulate(obj.iloc[cluster_idx].describe(),headers=obj.columns, tablefmt="rst"))
# temp = obj.iloc[cluster_idx].describe()
# temp.to_csv(instance_RS.directory+"temp/obj_stat.csv")
#
#
# # Finding the classes affected in the cluster
# classes = instance_RS.ref_classes.iloc[cluster_idx]
# classes_frequency = pd.value_counts(classes.values.ravel()) #result is a sorted dictionary
# print('\n\n **The most affected class is "{}" on which "{}" refactoring methods are applied.'
#       .format(classes_frequency.keys()[0],classes_frequency.values[0]))
#
# classes_idx = [metrics.Class[metrics.Class == var].index.tolist()[0] for var in classes_frequency.keys()]
# MetricsInCluster = metrics.iloc[classes_idx]
# print(tabulate(MetricsInCluster.describe(),headers=MetricsInCluster.columns, tablefmt="rst"))
# temp = MetricsInCluster.describe()
# temp.to_csv(instance_RS.directory+"temp/dm_stats.csv")
#
# #Convert to list
# solution_list = solution_df.iloc[cluster_idx].values.tolist ()
#
# #Removing NaN values
# list_noNan = [ ]
# for row in solution_list:
#     noNan_row = [ a for a in row if a != 'None' ]
#     list_noNan.append (noNan_row)
#
# # Converting to OneHotTransaction
# from mlxtend.preprocessing import OnehotTransactions
#
# oht = OnehotTransactions ()
# oht_ary = oht.fit (list_noNan).transform (list_noNan)
# oht_df = pd.DataFrame (oht_ary , columns=oht.columns_)
#
# #Apriori Algorithm
# from mlxtend.frequent_patterns import apriori
#
# frequent_itemsets = apriori(oht_df , min_support=0.8, use_colnames=True)
# print('\n\n **Frequent itemsets using Apriori algorithm , min_support=0.8')
# print(tabulate(frequent_itemsets, headers= ['support','itemsets'], tablefmt="rst"))
# temp = frequent_itemsets
# temp.to_csv(instance_RS.directory+"temp/freq_itm.csv")
#
# # Association Rules Generation from Frequent Itemsets
# from mlxtend.frequent_patterns import association_rules
#
# rules = association_rules(frequent_itemsets, metric="confidence", min_threshold=0.8)
# print('\n\n **Association Rules ,metric="confidence", min_threshold=0.8')
# print(tabulate(rules, headers= ['antecedants','consequents','support','confidence','lift'], tablefmt="rst"))
# temp = rules
# temp.to_csv(instance_RS.directory+"temp/rules.csv")
