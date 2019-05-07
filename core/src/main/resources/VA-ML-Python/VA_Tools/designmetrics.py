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
        self.DesignMetrics = None
        self.load ()

    def load(self):
        df = pd.read_csv (self.dm_path,sep=',', header=0)
        self.DesignMetrics=df


    def export(self):
        output_path = self.directory + "DesignMetrics.csv"
        self.DesignMetrics.to_csv (output_path , index_label='index')
