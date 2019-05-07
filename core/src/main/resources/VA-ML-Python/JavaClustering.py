"""
Created on 3/11/2018 , 8:31 AM

@author: vahid
"""

# print (__doc__)
#########################################IMPORTS###########################################

def import_or_install(package):
    try:
        __import__ (package)
    except ImportError:
        from pip._internal import main
        main(([ 'install' , package ]))

        #This gives error for pip 10.0.1 : AttributeError: Module Pip has no attribute 'main'
        # pip.main ([ 'install' , package ])

        #another solution
        # print "Trying to Install required module: requests\n"
        # os.system('python -m pip install {}'.format(package))


##########################################################################################


try:
    import pip
    import os
    import sys

    sys.path.insert (0 , os.path.abspath ('..'))

    for p in sys.path:
        print (p)

    # sys.path.insert (0 , os.path.abspath ('C:\@D\PhD\@SBSE Projects\@My Codes\ML Refactoring'))

    import_or_install ('numpy')
    import_or_install ('pandas')
    import_or_install ('sklearn')
    import_or_install ('scipy')
    import_or_install ('traceback')
    import_or_install ('matplotlib')

    import traceback
    import numpy as np
    import pandas as pd

    from sklearn.mixture import GaussianMixture as GMM
    import scipy.stats
    from sklearn import preprocessing

    from VA_Tools import refactoringsolution as RS
    import VA_Tools.designmetrics as DM
    from VA_ML import clustering_tools as CL
    from VA_Tools import config as cfg

except Exception as exc:
    traceback.print_exc ()
    raise exc

###########################################################################################
def py_version():
    print (sys.version)


###########################################################################################

def clustering(directory , exec_id , counter , num_obj , max_num_ref):
    cfg.Config.directory = str (directory)
    cfg.Config.ref_directory = str (directory)
    cfg.Config.num_obj = int (num_obj)
    cfg.Config.max_num_ref = int (max_num_ref)
    cfg.Config.dm_path = directory + "output-" + str (exec_id) + "-dm-" + str (counter) + ".txt"

    obj_file = "output-" + str (exec_id) + "-obj-" + str (counter) + ".txt"
    ref_file = "output-" + str (exec_id) + "-var-" + str (counter) + ".txt"

    instance_RS = RS.RefactoringSolution (obj_file , ref_file)
    instance_DM = DM.DesignMetrics ()

    ###########################################################################################
    # region Clustering
    BestNumberClusters = CL.BestClusteringGMM (instance_RS.obj)

    X = instance_RS.obj.values
    gmm = GMM (n_components=BestNumberClusters , covariance_type='full' , max_iter=1000 , tol=0.0001).fit (X)
    ClusterLabels = gmm.predict (X)
    ClustersProb = gmm.predict_proba (X)

    centers = np.empty (shape=(gmm.n_components , X.shape[ 1 ]))
    centers_id = np.empty (shape=(gmm.n_components , 1))
    for i in range (gmm.n_components):
        density = scipy.stats.multivariate_normal (cov=gmm.covariances_[ i ] , mean=gmm.means_[ i ]).logpdf (X)
        centers[ i , : ] = X[ np.argmax (density) ]
        centers_id[ i ] = np.argmax (density)

    clusterDFColNames = instance_RS.obj.columns.tolist ()
    clusterDFColNames.insert (0 , 'point_id')
    ClusterCenters = pd.DataFrame (np.concatenate ((centers_id , centers) , axis=1)
                                   , columns=clusterDFColNames)
    ClusterCenters.point_id = ClusterCenters.point_id.astype (int)

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
    ###########################################################################################

    ClusterCenters.to_csv (cfg.Config.ref_directory + "ClusterCenters-{}-{}.csv".format (exec_id , counter)
                           , index_label="Cluster#")
    AllClustersMetrics.to_csv (cfg.Config.ref_directory + "ClusterMetrics-{}-{}.csv".format (exec_id , counter)
                               , index_label="Cluster#")

    pd.DataFrame (ClusterLabels).to_csv (
        cfg.Config.ref_directory + "ClusterLabels-{}-{}.csv".format (exec_id , counter) , header=False)

    for ClusterNum in range (BestNumberClusters):
        ClassesFreq = ClassesFreqCollection[ ClusterNum ]
        OperationsFreq = OperationsFreqCollection[ ClusterNum ]
        ClassesFreq.to_csv (
            cfg.Config.ref_directory + "ClassesFreq-{}-{}-{}.csv".format (exec_id , counter , ClusterNum)
            , index_label="#")
        OperationsFreq.to_csv (
            cfg.Config.ref_directory + "OperationsFreq-{}-{}-{}.csv".format (exec_id , counter , ClusterNum)
            , index_label="#")

    ###########################################################################################
    ## LOADING Design Metrics
    ###########################################################################################
    DesignMetrics = instance_DM.DesignMetrics

    # Normalizing the values of design metric
    dmValues = DesignMetrics.values  # Return as numpy array

    min_max_scaler = preprocessing.MinMaxScaler()
    dm_scaled = min_max_scaler.fit_transform(dmValues[:, 1:])

    # Calculating single quality value from different metrics
    # Encapsulation + Cohesion + Composition + Inheritance + Abstraction - Complexity + Messaging - Coupling + Polymorphism
    quality = dm_scaled[:, 0] + dm_scaled[:, 1] + dm_scaled[:, 2] + \
              dm_scaled[:, 3] + dm_scaled[:, 5] - dm_scaled[:, 6] + \
              dm_scaled[:, 10] - dm_scaled[:, 11] + dm_scaled[:, 12]

    # normalizing quality again since there might be negative values
    quality_normalized = min_max_scaler.fit_transform(quality.reshape(-1, 1))

    ClassQuality = pd.DataFrame({"Class Name": dmValues[:, 0],
                                 "Quality": quality_normalized.reshape(1, -1)[0]})

    ###########################################################################################
    # Plot Pie Chart and Quality Radar Chart
    ###########################################################################################
    from VA_Viz import ClusterExplain as CE

    clusterExplain = CE.ClusterExplain(BestNumberClusters, ClassesFreqCollection, OperationsFreqCollection, ClassQuality)

    outputFolder = cfg.Config.ref_directory

    clusterExplain.clusterPieChart(outputFolder)

    clusterExplain.plotRadarChart(instance_RS,ClusterLabels,outputFolder)



if __name__ == "__main__":
    try:
        py_version ()
        clustering (sys.argv[ 1 ] , sys.argv[ 2 ] , sys.argv[ 3 ] , sys.argv[ 4 ] , sys.argv[ 5 ])
    except Exception as exc:
        traceback.print_exc ()
        raise exc
