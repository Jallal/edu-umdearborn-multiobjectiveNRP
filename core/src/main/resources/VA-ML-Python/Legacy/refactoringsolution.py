# -*- coding: utf-8 -*-
"""
Created on Sat Aug 12 20:58:06 2017

@author: vahid
"""
import re
from os import remove
import pandas as pd
import numpy as np
from VA_Tools import config as cfg


class RefactoringSolution(cfg.Config):

    def __init__(self , FileFun):
        self.filepath = self.ref_directory + FileFun
        self.load ()

    def load(self):
        df = pd.read_csv (self.filepath , sep='\t')

        df.index = df.values[ : , 0 ]
        df = df.iloc[ : , 1: ]
        # df.columns.values[ 8 ] = 'Obj9'

        # df_positive = df.iloc[ : , 0:9 ].apply (np.abs) #Making all values positive
        df_positive = df.iloc[ : , 0:self.num_obj ] #Making all values positive

        selected_columns = [ 'Ref{}'.format (num) for num in np.arange (1 , self.num_ref+1) ]
        df_ref = df.loc[ : , selected_columns ]

        df_final = pd.concat ([ df_positive , df_ref ] , axis=1)

        self.all = df_final
        self.obj = df_positive
        self.ref = df_ref
        print ('Data is loaded!')
        ######################################################
        file = open (self.filepath)
        text = file.read ()
        new_text = re.sub (r'\([^)]*\)' , '' , text)
        temp_path = self.ref_directory + '/temp/noparam.csv'
        temp_file = open (temp_path , 'w')
        temp_file.write (new_text)

        df = pd.read_csv (temp_path , sep='\t')
        df.index = df.values[ : , 0 ]
        df = df.iloc[ : , 1: ]
        selected_columns = [ 'Ref{}'.format (num) for num in np.arange (1 , self.num_ref + 1) ]
        df_ref = df.loc[ : , selected_columns ]

        self.ref_noparam = df_ref
        temp_file.close ()
        remove (temp_path)
        print ('Parameters has been removed!')
        #####################################################
        file = open (self.filepath)
        text = file.read ()
        new_text = re.sub (r'[\t][a-zA-Z]*\(' , '\t(' , text)
        new_text = re.sub (r'\;[^)]*\)' , '' , new_text)
        new_text = re.sub (r'\(([^.\s]*\.)*' , '' , new_text)
        temp_path = self.ref_directory + '/temp/class.csv'
        temp_file = open (temp_path , 'w')
        temp_file.write (new_text)

        df = pd.read_csv (temp_path , sep='\t')
        df.index = df.values[ : , 0 ]
        df = df.iloc[ : , 1: ]
        selected_columns = [ 'Ref{}'.format (num) for num in np.arange (1 , self.num_ref+1) ]
        df_ref = df.loc[ : , selected_columns ]

        self.ref_classes = df_ref
        temp_file.close ()
        remove (temp_path)
        print ('Data with class names has been created!')
        ######################################################


    def export_to_csv(self):
        output_file = self.filepath.replace ('.csv' , '-exported-ref-noparam.csv')
        self.ref_noparam.to_csv (output_file , index_label='index')

        output_file = self.filepath.replace ('.csv' , '-exported-obj.csv')
        self.obj.to_csv (output_file , index_label='index')

        output_file = self.filepath.replace ('.csv' , '-exported-class.csv')
        self.ref_classes.to_csv (output_file , index_label='index')

        print ('Data has been exported to CSV!')
