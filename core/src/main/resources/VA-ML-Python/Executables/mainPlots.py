"""
Created on 4/25/2018 , 4:19 PM

@author: vahid
"""

#########################################IMPORTS###########################################
import os
import sys

import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import matplotlib.patches as patches

sys.path.insert (0 , os.path.abspath ('..'))

from VA_Tools import refactoringsolution as RS
from scipy.spatial import distance
import VA_Tools.designmetrics as DM
from VA_ML import clustering_tools as CL
from VA_Tools import config as cfg



###################Seaborn Plots#########################
# set font size of labels on matplotlib plots
plt.rc ('font' , size=16)

# set style of plots
sns.set_style ('white')

# define a custom palette
customPalette = [ '#630C3A' , '#39C8C6' , '#D3500C' , '#FFB139' ]
sns.set_palette (customPalette)
# sns.palplot (customPalette)
###########################################################################################
RS_1 = RS.RefactoringSolution ('obj1.txt' , 'var1.txt')
X1 = RS_1.obj
labels1 = pd.read_csv (RS_1.ref_directory + "label1.csv" , header=None)
labels1.columns = [ 'id' , 'clusters' ]
XL1 = pd.concat ([ X1 , labels1 ] , axis=1)

selectedCluster = 0

###################STYLE 6: LABELS CENTERED ON CLUSTER MEANS (2)#########################

# create a new figure
fig1 = plt.figure (figsize=(15 , 10))
ax1 = fig1.add_subplot (131)

# loop through labels and plot each cluster
for i , label in enumerate ([ 0 , 1 , 2, 3 ]):
    # add data points
    ax1.scatter (x=XL1.loc[ XL1[ 'clusters' ] == label , 'Obj1' ] ,
                 y=XL1.loc[ XL1[ 'clusters' ] == label , 'Obj2' ] ,
                 color=customPalette[ i ] ,
                 alpha=1)
    if label==selectedCluster:
        annotate = str(selectedCluster) +' :PC'
    else:
        annotate = str(label)
    # add label
    ax1.annotate (annotate ,
                  XL1.loc[ XL1[ 'clusters' ] == label , [ 'Obj1' , 'Obj2' ] ].mean () ,
                  horizontalalignment='center' ,
                  verticalalignment='center' ,
                  size=15 , weight='bold' ,
                  color='white' ,
                  backgroundcolor=customPalette[ i ])

ax1.add_patch (
    patches.Ellipse (
        (0.019,0.065),
        0.27 ,  # width
        0.035 ,  # height
        70 ,  # radius
        alpha=0.2 , facecolor=customPalette[0] , edgecolor="black" , linewidth=1 , linestyle='solid'
    )
)

plt.axvline(-0.027, color='darkred')
plt.axhline(-0.045, color='darkred')


ax1.set_title('Iteration 1 - Clustered Pareto-front', fontweight='bold', color = 'black', fontsize='17', horizontalalignment='center')
ax1.set_xlabel(r'', fontweight='bold', color = 'black', fontsize='17', horizontalalignment='center')
ax1.set_ylabel(r'$Extendibility$', fontweight='bold', color = 'black', fontsize='17', horizontalalignment='center')

ax1.set_xlim(-0.1,0.1)
ax1.set_ylim(-0.29,0.25)
###########################################################################################
###########################################################################################
####################################Iteration 2#########################################
###########################################################################################
###########################################################################################
refXL = XL1
refSelCluster = selectedCluster

RS_1 = RS.RefactoringSolution ('obj2.txt' , 'var2.txt')
X1 = RS_1.obj
labels1 = pd.read_csv (RS_1.ref_directory + "label2.csv" , header=None)
labels1.columns = [ 'id' , 'clusters' ]
XL1 = pd.concat ([ X1 , labels1 ] , axis=1)

selectedCluster = 1

###################STYLE 6: LABELS CENTERED ON CLUSTER MEANS (2)#########################

# create a new figure
# fig1 = plt.figure (figsize=(15 , 10))
ax1 = fig1.add_subplot (132)

# loop through labels and plot each cluster
for i , label in enumerate ([ 0 , 1 , 2 , 3 ]):
    # add data points
    ax1.scatter (x=XL1.loc[ XL1[ 'clusters' ] == label , 'Obj1' ] ,
                 y=XL1.loc[ XL1[ 'clusters' ] == label , 'Obj2' ] ,
                 color=customPalette[ i ] ,
                 alpha=1)

    if label==selectedCluster:
        annotate = str(selectedCluster) +' :PC'
    else:
        annotate = str(label)
    # add label
    ax1.annotate (annotate ,
                  XL1.loc[ XL1[ 'clusters' ] == label , [ 'Obj1' , 'Obj2' ] ].mean () ,
                  horizontalalignment='center' ,
                  verticalalignment='center' ,
                  size=15 , weight='bold' ,
                  color='white' ,
                  backgroundcolor=customPalette[ i ])

ax1.add_patch (
    patches.Ellipse (
        # refXL.loc[ refXL[ 'clusters' ] == refSelCluster , [ 'Obj1' , 'Obj2' ] ].mean () ,  # (x,y)
        (0.019,0.065),
        0.27 ,  # width
        0.035 ,  # height
        70 ,  # radius
        alpha=0.2 , facecolor=customPalette[ 0 ] , edgecolor="black" , linewidth=1 , linestyle='solid'
    )
)

plt.axvline(-0.027, color='darkred')
plt.axhline(-0.045, color='darkred')


ax1.add_patch (
    patches.Ellipse (
        (0.037,0.13),
        0.18 ,  # width
        0.039 ,  # height
        68 ,  # radius
        alpha=0.2 , facecolor=customPalette[ 1 ] , edgecolor="black" , linewidth=1 , linestyle='solid'
    )
)

plt.axvline(0.003, color=customPalette[ 1 ])
plt.axhline(0.055, color=customPalette[ 1 ])


ax1.set_title('Iteration 2 - Clustered Pareto-front', fontweight='bold', color = 'black', fontsize='17', horizontalalignment='center')
ax1.set_xlabel(r'$Effectiveness$', fontweight='bold', color = 'black', fontsize='17', horizontalalignment='center')
ax1.set_ylabel(r'', fontweight='bold', color = 'black', fontsize='17', horizontalalignment='center')


ax1.set_xlim(-0.1,0.1)
ax1.set_ylim(-0.29,0.25)

###########################################################################################
###########################################################################################
####################################Iteration 3#########################################
###########################################################################################
###########################################################################################
RS_1 = RS.RefactoringSolution ('obj3.txt' , 'var3.txt')
X1 = RS_1.obj
labels1 = pd.read_csv (RS_1.ref_directory + "label3.csv" , header=None)
labels1.columns = [ 'id' , 'clusters' ]
XL1 = pd.concat ([ X1 , labels1 ] , axis=1)

###################STYLE 6: LABELS CENTERED ON CLUSTER MEANS (2)#########################

# create a new figure
# fig1 = plt.figure (figsize=(15 , 10))
ax1 = fig1.add_subplot (133)

# loop through labels and plot each cluster
for i , label in enumerate ([ 0 , 1 , 2 , 3 ]):
    # add data points
    ax1.scatter (x=XL1.loc[ XL1[ 'clusters' ] == label , 'Obj1' ] ,
                 y=XL1.loc[ XL1[ 'clusters' ] == label , 'Obj2' ] ,
                 color=customPalette[ i ] ,
                 alpha=1)

    # add label
    ax1.annotate (label ,
                  XL1.loc[ XL1[ 'clusters' ] == label , [ 'Obj1' , 'Obj2' ] ].mean () ,
                  horizontalalignment='center' ,
                  verticalalignment='center' ,
                  size=15 , weight='bold' ,
                  color='white' ,
                  backgroundcolor=customPalette[ i ])

ax1.add_patch (
    patches.Ellipse (
        # refXL.loc[ refXL[ 'clusters' ] == refSelCluster , [ 'Obj1' , 'Obj2' ] ].mean () ,  # (x,y)
        (0.019 , 0.065) ,
        0.27 ,  # width
        0.035 ,  # height
        70 ,  # radius
        alpha=0.2 , facecolor=customPalette[ 0 ] , edgecolor="black" , linewidth=1 , linestyle='solid'
    )
)

plt.axvline(-0.027, color='darkred')
plt.axhline(-0.045, color='darkred')


ax1.add_patch (
    patches.Ellipse (
        (0.037,0.13),
        0.18 ,  # width
        0.039 ,  # height
        68 ,  # radius
        alpha=0.2 , facecolor=customPalette[ 1 ] , edgecolor="black" , linewidth=1 , linestyle='solid'
    )
)

plt.axvline(0.003, color=customPalette[ 1 ])
plt.axhline(0.055, color=customPalette[ 1 ])

ax1.set_title('Iteration 3 - Clustered Pareto-front', fontweight='bold', color = 'black', fontsize='17', horizontalalignment='center')
ax1.set_xlabel(r'', fontweight='bold', color = 'black', fontsize='17', horizontalalignment='center')
ax1.set_ylabel(r'', fontweight='bold', color = 'black', fontsize='17', horizontalalignment='center')


ax1.set_xlim(-0.1,0.1)
ax1.set_ylim(-0.29,0.25)


















###################STYLE 1: STANDARD LEGEND#########################
# plot data with seaborn
# facet = sns.lmplot(data=XL1, x='Obj1', y='Obj2', hue='clusters',
#                    fit_reg=False, legend=True, legend_out=True)


###################STYLE 2: COLOR-CODED LEGEND#########################
# plot data with seaborn (don't add a legend yet)
# facet = sns.lmplot (data=XL1 , x='Obj1', y='Obj2', hue='clusters',
#                     fit_reg=False , legend=False)
#
# # add a legend
# leg = facet.ax.legend (bbox_to_anchor=[ 1 , 0.75 ] ,
#                        title="Clusters" , fancybox=True)
# # change colors of labels
# for i , text in enumerate (leg.get_texts ()):
#     plt.setp (text , color=customPalette[ i ])

###################STYLE 3: COLOR-CODED TITLE#########################
#
# #plot data with seaborn
# facet = sns.lmplot(data=XL1, x='Obj1', y='Obj2', hue='clusters',
#                    fit_reg=False, legend=False)
#
# #define padding -- higher numbers will move title rightward
# pad = 4.5
#
# #define separation between cluster labels
# sep = 0.3
#
# #define y position of title
# y = 5.6
#
# #add beginning of title in black
# facet.ax.text(pad, y, 'Distributions of points in clusters:',
#               ha='right', va='bottom', color='black')
#
# #add color-coded cluster labels
# for i, label in enumerate([0,1,2,3]):
#     text = facet.ax.text(pad+((i+1)*sep), y, label,
#                          ha='right', va='bottom',
#                          color=customPalette[i])