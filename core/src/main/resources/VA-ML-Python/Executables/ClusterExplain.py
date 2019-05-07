## Importing the libraries

import os
import sys

import numpy as np
import pandas as pd

sys.path.insert(0, os.path.abspath('..'))

from VA_Tools import refactoringsolution as RS
from VA_ML import clustering_tools as CL
from VA_Tools import config as cfg
from VA_Tools import designmetrics as DM

from sklearn.mixture import GaussianMixture as GMM
from sklearn import preprocessing
import scipy.stats

import matplotlib.pyplot as plt

from math import pi
############################################################################
## LOADING REFACTORING DATA
############################################################################
instance_RS = RS.RefactoringSolution('obj.txt', 'var.txt')
X1 = instance_RS.obj
labels1 = pd.read_csv(instance_RS.ref_directory + "ClusterLabels.csv", header=None)
labels1.columns = ['id', 'clusters']
XL1 = pd.concat([X1, labels1], axis=1)

############################################################################
## LOADING Design Metrics
############################################################################
DesignMetrics = DM.DesignMetrics().DesignMetrics

# Normalizing the values of design metric
dmValues = DesignMetrics.values  # Return as numpy array

min_max_scaler = preprocessing.MinMaxScaler()
dm_scaled = min_max_scaler.fit_transform(dmValues[:, 1:])

#Calculating single quality value from different metrics
# Encapsulation + Cohesion + Composition + Inheritance + Abstraction - Complexity + Messaging - Coupling + Polymorphism
quality = dm_scaled[:, 0] + dm_scaled[:, 1] + dm_scaled[:, 2] + \
          dm_scaled[:, 3] + dm_scaled[:, 5] - dm_scaled[:, 6] + \
          dm_scaled[:, 10] - dm_scaled[:, 11] + dm_scaled[:, 12]

#normalizing quality again since there might be negative values
quality_normalized =  min_max_scaler.fit_transform(quality.reshape(-1,1))

ClassQuality = pd.DataFrame({"Class Name": dmValues[:, 0],
                             "Quality": quality_normalized.reshape(1,-1)[0]})




############################################################################
# region Clustering
############################################################################
BestNumberClusters = CL.BestClusteringGMM(instance_RS.obj)

X = instance_RS.obj.values
gmm = GMM(n_components=BestNumberClusters, covariance_type='full', max_iter=1000, tol=0.00001).fit(X)
ClusterLabels = gmm.predict(X)
ClustersProb = gmm.predict_proba(X)

centers = np.empty(shape=(gmm.n_components, X.shape[1]))
centers_id = np.empty(shape=(gmm.n_components, 1))
for i in range(gmm.n_components):
    density = scipy.stats.multivariate_normal(cov=gmm.covariances_[i], mean=gmm.means_[i]).logpdf(X)
    centers[i, :] = X[np.argmax(density)]
    centers_id[i] = np.argmax(density)

clusterDFColNames = instance_RS.obj.columns.tolist()
clusterDFColNames.insert(0, 'point_id')
ClusterCenters = pd.DataFrame(np.concatenate((centers_id, centers), axis=1)
                              , columns=clusterDFColNames)
ClusterCenters.point_id = ClusterCenters.point_id.astype(int)
ClusterCenters.to_csv(cfg.Config.ref_directory + "ClusterCenters.csv", index_label="Cluster#")
# endregion
###########################################################################################
ClassesFreqCollection = {}
OperationsFreqCollection = {}
for target in range(BestNumberClusters):
    print("Calculating Frequency of Classes and Operations for Cluster {}".format(target))
    classes = instance_RS.ref_classes[ClusterLabels == target]
    operations = instance_RS.ref_noparam[ClusterLabels == target]

    from collections import Counter

    AllClassesCountDict = {}
    AllClassesWeightProbDict = {}

    for idx, row in classes.iterrows():
        ClassesDict = pd.value_counts(row).to_dict()
        Weight = ClustersProb[idx, target]
        AllClassesCountDict = dict(Counter(AllClassesCountDict) + Counter(ClassesDict))
        ClassesDict.update((key, value * Weight) for key, value in ClassesDict.items())
        AllClassesWeightProbDict = dict(Counter(AllClassesWeightProbDict) + Counter(ClassesDict))
        # only positive count values will remain in the dictionary

    FinalClassFrequencyDF = pd.DataFrame(list(AllClassesCountDict.items()), columns=['Classes', 'Count'])
    CountsList = FinalClassFrequencyDF.loc[:, ['Count']]
    CountsSum = np.sum(CountsList)
    ProbList = CountsList / CountsSum
    FinalClassFrequencyDF['Probability'] = ProbList

    WeightedCountsList = list(AllClassesWeightProbDict.values())
    WeightedCountsSum = np.sum(WeightedCountsList)
    WeightedProbList = WeightedCountsList / WeightedCountsSum
    FinalClassFrequencyDF['Weighted_Probability'] = WeightedProbList
    FinalClassFrequencyDFSorted = FinalClassFrequencyDF.sort_values('Probability', ascending=False)

    ClassesFreqCollection[target] = FinalClassFrequencyDFSorted

    # =================================================================
    AllOperationsCountDict = {}
    AllOperationsWeightProbDict = {}

    for idx, row in operations.iterrows():
        OperationsDict = pd.value_counts(row).to_dict()
        Weight = ClustersProb[idx, target]
        AllOperationsCountDict = dict(Counter(AllOperationsCountDict) + Counter(OperationsDict))
        OperationsDict.update((key, value * Weight) for key, value in OperationsDict.items())
        AllOperationsWeightProbDict = dict(Counter(AllOperationsWeightProbDict) + Counter(OperationsDict))
        # only positive count values will remain in the dictionary

    FinalOperationsFrequencyDF = pd.DataFrame(list(AllOperationsCountDict.items()),
                                              columns=['Operations', 'Count'])
    CountsList = FinalOperationsFrequencyDF.loc[:, ['Count']]
    CountsSum = np.sum(CountsList)
    ProbList = CountsList / CountsSum
    FinalOperationsFrequencyDF['Probability'] = ProbList

    WeightedCountsList = list(AllOperationsWeightProbDict.values())
    WeightedCountsSum = np.sum(WeightedCountsList)
    WeightedProbList = WeightedCountsList / WeightedCountsSum
    FinalOperationsFrequencyDF['Weighted_Probability'] = WeightedProbList
    FinalOperationsFrequencyDFSorted = FinalOperationsFrequencyDF.sort_values('Probability', ascending=False)

    OperationsFreqCollection[target] = FinalOperationsFrequencyDFSorted
###########################################################################################
# Plot Pie Chart and Quality Radar Chart
###########################################################################################
from VA_Viz import ClusterExplain as CE

clusterExplain = CE.ClusterExplain(BestNumberClusters, ClassesFreqCollection, OperationsFreqCollection,ClassQuality)

outputFolder = "C:/Users/vahid/Desktop"

clusterExplain.clusterPieChart(outputFolder)

clusterExplain.plotRadarChart(instance_RS, ClusterLabels, outputFolder)
