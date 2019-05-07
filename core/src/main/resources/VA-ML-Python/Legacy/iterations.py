"""
Created on 8/15/2017 , 9:08 PM

@author: vahid
"""
from os import listdir

from VA_Tools import config as cfg , refactoringsolution as RS


class RsIterations(cfg.Config):

    def __init__(self , directory_name):
        self.directory_name = directory_name
        self.path = self.ref_directory + directory_name

    def ListFiles(self):
        return listdir(self.path)

    def LoadFiles(self):
        self.RS_list = []
        for index , file in enumerate(self.ListFiles()):
            solution = RS.RefactoringSolution(self.directory_name+ "/" + file)
            self.RS_list.append(solution)
            print("Iteration {} is loaded!".format(index))
            if index == 5:
                break

    def ComputeDiff(self):
        #TODO
        pass




