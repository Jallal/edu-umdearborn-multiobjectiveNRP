# -*- coding: utf-8 -*-
"""
Created on Sat Aug 12 20:58:06 2017

@author: vahid
"""
import re
import os
import pandas as pd
import numpy as np
from VA_Tools import config as cfg


class RefactoringSolution(cfg.Config):

    def __init__(self, FileFun, FileVar):
        self.FunFilepath = self.ref_directory + FileFun
        self.VarFilepath = self.ref_directory + FileVar

        print("path to the obj file: {}".format(self.FunFilepath))
        self.load()

    def load(self):
        col_names_obj = ['Obj{}'.format(num) for num in np.arange(1, self.num_obj + 1)]
        self.obj = pd.read_csv(self.FunFilepath, sep='\s+', names=col_names_obj)
        # df_positive = df.iloc[ : , 0:9 ].apply (np.abs) #Making all values positive

        col_names_ref = ['Ref{}'.format(num) for num in np.arange(1, self.max_num_ref + 1)]
        self.ref = pd.read_csv(self.VarFilepath, names=col_names_ref)

        print('Data is loaded!')

        # ######################################################
        file = open(self.VarFilepath)
        text = file.read()
        new_text = re.sub(r'\([^)]*\)', '', text)
        new_text = re.sub(r'\s', '\n', new_text)
        temp_directory = self.ref_directory + 'temp'
        if not os.path.exists(temp_directory):
            os.makedirs(temp_directory)
        temp_path = temp_directory + '/operators.csv'
        temp_file = open(temp_path, 'w')
        temp_file.write(new_text)
        self.ref_noparam = pd.read_csv(temp_path, names=col_names_ref)
        temp_file.close()
        os.remove(temp_path)
        print('Parameters has been removed!')
        # #####################################################
        file = open(self.VarFilepath)
        text = file.read()

        finalList = []
        Lines = re.findall(r'[^\n]*\n', text)
        for line in Lines:
            new_text = re.findall(r'\([^\;\(]*;', line)
            new_text = re.sub(r';', ',\n', "".join(new_text))
            new_text = re.sub(r'\(', '', new_text)
            new_text = re.findall(r'[^.\n]*,', new_text)
            new_text = re.sub(r'\n', '', "".join(new_text))
            finalList.append(new_text)

        new_text = "\n".join(finalList)

        # new_text = re.sub(r'\n[^.]*\(', '\n', text)
        # new_text = re.sub(r',[^.]*\(', ',', new_text)
        # new_text = re.sub(r'^[^.]*\(', '', new_text)
        # new_text = re.sub(r'\;[^)]*\)', '', new_text)
        # new_text = re.sub(r'[^,\n]*[.]', '', new_text)

        temp_path = temp_directory + '/param.csv'
        temp_file = open(temp_path, 'w')
        temp_file.write(new_text)
        self.ref_classes = pd.read_csv(temp_path, names=col_names_ref)
        temp_file.close()
        os.remove(temp_path)
        print('Data with class names has been created!')
        # ######################################################

    def export_to_csv(self):
        output_file = self.ref_directory + 'Solutions-exported-ref-operations.csv'
        self.ref_noparam.to_csv(output_file, index_label='index')

        output_file = self.ref_directory + 'Solutions-exported-objectives.csv'
        self.obj.to_csv(output_file, index_label='index')

        output_file = self.ref_directory + 'Solutions-exported-ref-classes.csv'
        self.ref_classes.to_csv(output_file, index_label='index')

        print('Data has been exported to CSV!')

