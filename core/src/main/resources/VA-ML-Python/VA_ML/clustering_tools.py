"""
Created on 8/20/2017 , 11:47 PM

@author: vahid
"""
from sklearn.cluster import KMeans
from sklearn.metrics.cluster import silhouette_score
from sklearn.mixture import GaussianMixture as GMM
from sklearn import metrics
import numpy as np


def best_clustering_kmeans_silhouette(obj):
    # best num of clusters
    """

    :type obj: dataframe of objectives
    """
    scores = [ ]
    num_clusters = np.arange (2 , 10)
    for nc in num_clusters:
        y_pred = KMeans (n_clusters=nc , random_state=10).fit_predict (obj.values)
        scores.append (silhouette_score (obj.values , y_pred))

    max_score = np.max (scores)
    max_index = scores.index (max_score)
    best_num_cluster = num_clusters[ max_index ]
    print (
        "Best number of clusters is {} with the silhouette score={}".format (best_num_cluster , round (max_score , 3)))
    return best_num_cluster


def BestClusteringGMM(objectives):
    """

    :type objectives: dataframe of objectives
    """
    X = objectives.values
    num_clusters = np.arange (3 , 7)
    models = [ GMM (n_components=n , covariance_type='full').fit (X) for n in num_clusters ]
    scores = [ metrics.calinski_harabaz_score (X , m.predict (X)) for m in models ]
    # scores2 = [metrics.cluster.silhouette_score(X,m.predict(X)) for m in models ]
    max_score = np.max (scores)
    max_index = scores.index (max_score)
    best_num_cluster = num_clusters[ max_index ]
    print ("Best number of clusters is {} with the calinski_harabaz_score={}".format (best_num_cluster ,
                                                                                      round (max_score , 3)))
    return best_num_cluster
