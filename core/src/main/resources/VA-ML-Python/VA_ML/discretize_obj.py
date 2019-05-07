"""
Created on 8/21/2017 , 1:12 AM

@author: vahid
"""

import pandas as pd



def discretized_eq_fre(obj, num_freq):

    """

    :rtype: dataframe of objectives, number of quantiles
    """
    disc_data = []
    for col in obj.columns:
        disc_col = pd.qcut(obj[col] , 3)
        disc_data.append(disc_col)

    disc_obj = pd.DataFrame(disc_data).transpose()

    return disc_obj

