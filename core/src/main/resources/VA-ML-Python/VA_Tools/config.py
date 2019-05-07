"""
Created on 8/16/2017 , 12:37 AM

@author: vahid
"""


class Config:
    # set the directory to the resources
    num_obj = 9
    max_num_ref = 100
    project_name = "Grocery"
    directory = "C:/Users/vahid/Desktop/Refactoring-Outputs/{}/".format(project_name)
    # ref_directory = directory + "{}obj/".format(num_obj)
    ref_directory = directory + "Plots/"
    # dm_path = directory + "{}.dm".format(project_name)
    dm_path = directory + "Plots/dm.txt"

