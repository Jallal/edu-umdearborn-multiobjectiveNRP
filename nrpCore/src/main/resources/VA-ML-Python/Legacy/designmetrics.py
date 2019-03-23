# -*- coding: utf-8 -*-
"""
Created on Sun Aug 13 15:09:16 2017

@author: vahid
"""
import pandas as pd
import numpy as np
from VA_Tools import config as cfg


class DesignMetrics (cfg.Config):
    def __init__(self):
        self.output_original = None
        self.load ()

    def load(self):
        df = pd.read_csv (self.dm_path,sep=',', header=0)
        self.output_original=df

    def labeled_metrics(self , solution_obj):

        """
        :param solution_obj: Object of solutions
        """
        metrics = self.output_original.copy ()
        classes = solution_obj.ref_classes.fillna ('none')
        methods = solution_obj.ref_noparam.fillna ('none')

        methods_list = methods.values.ravel ()
        classes_list = classes.values.ravel ()

        labelsDF_columns = [ 'AllMethodsCount' ,
                             'ExtractClass' , 'ExtractSubClass' , 'ExtractSuperClass' ,
                             'MoveField' , 'MoveMethod' ,
                             'PullUpField' , 'PullUpMethod' ,
                             'PushDownField' , 'PushDownMethod' ,
                             'EncapsulateField' ,
                             'IncreaseMethodSecurity' , 'DecreaseMethodSecurity' ,
                             'IncreaseFieldSecurity' , 'DecreaseFieldSecurity' ]

        labelsDF_zeroMat = np.matlib.zeros ((metrics.__len__ () , labelsDF_columns.__len__ ()))
        labelsDF = pd.DataFrame (labelsDF_zeroMat , columns=labelsDF_columns)

        for idx in metrics.index:
            methods_for_class = methods_list[ classes_list == metrics.ClassName[ idx ] ]
            methods_used = pd.value_counts (
                methods_for_class).to_dict ()  # {dictionary} of frequency of methods used for a specific class
            sum_methods = sum (methods_used.values ())

            labelsDF.AllMethodsCount[ idx ] = sum_methods
            for key , value in methods_used.items ():
                labelsDF[ key ][ idx ] = value / sum_methods

        LabeledMetrics = pd.concat ([ metrics , labelsDF ] , axis=1)

        self.output_LabeledMetrics = LabeledMetrics

        print('Metrics have been labeled with the applied methods!')

    def export(self):
        output_path = self.filepath.replace ('.csv' , '-processed.csv')
        self.output_LabeledMetrics.to_csv (output_path , index_label='index')
