
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd


class ClusterExplain:

    def __init__(self,BestNumberClusters,ClassesFreqCollection,OperationsFreqCollection,ClassQuality=None):

        self.BestNumberClusters = BestNumberClusters
        self.ClassesFreqCollection = ClassesFreqCollection
        self.OperationsFreqCollection = OperationsFreqCollection
        self.ClassQuality = ClassQuality

        # Get current size
        fig_size = plt.rcParams["figure.figsize"]

        # Set figure width to 12 and height to 9
        fig_size[0] = 10
        fig_size[1] = 8.5
        plt.rcParams["figure.figsize"] = fig_size

    ###########################################################################################
    # Plot Pie Chart
    ###########################################################################################
    def clusterPieChart(self, outputFolder):

        fig, (ax1) = plt.subplots(1, 1)
        self.plotPieChart(ax1, self.BestNumberClusters, self.ClassesFreqCollection, self.ClassQuality, True, 'Refactored Classes')
        plt.savefig(outputFolder+"/RefactoredClasses.png")


        fig, (ax1) = plt.subplots(1, 1)
        self.plotPieChart(ax1, self.BestNumberClusters, self.OperationsFreqCollection, None, True, 'Refactoring Operations')
        plt.savefig(outputFolder+"/RefactoringOperations.png")
        # plt.show()

    @staticmethod
    def plotPieChart(ax, BestNumberClusters, FrequencyCollection, ClassQuality, legend, title):

        NumTopNames = 5
        group_names = []
        group_size = []
        subgroup_names = []
        subgroup_size = []
        for target in range(BestNumberClusters):
            ClusterData = FrequencyCollection[target]

            group_names = group_names + ["Cluster{} ({}) ".format(np.str(target), np.str(ClusterData.sum(0).Count))]
            group_size = group_size + [ClusterData.sum(0).Count]

            subgroup_names = subgroup_names + ClusterData.iloc[0:NumTopNames, 0].tolist() + [
                'Others({})'.format(ClusterData.iloc[NumTopNames:, 1].sum(0))]
            subgroup_size = subgroup_size + ClusterData.iloc[0:NumTopNames, 1].tolist() + [
                ClusterData.iloc[NumTopNames:, 1].sum(0)]

        plt.style.use('seaborn-muted')
        ax.axis('equal')

        # First Ring (outside)
        # fig, ax = plt.subplots()

        mymap = plt.get_cmap("Dark2")
        groupColors = []
        subGroupColors = []

        for g_idx in range(BestNumberClusters):
            groupColors = groupColors + [mymap(g_idx)]

            if ClassQuality is not None:
                for sg_idx in range(NumTopNames):
                    tmpClassName = subgroup_names[sg_idx]
                    tmpClassQuality = ClassQuality[ClassQuality['Class Name'].str.match(tmpClassName)].values[:,1]
                    subGroupColors = subGroupColors + [mymap(g_idx, alpha=tmpClassQuality)]

                subGroupColors = subGroupColors + [mymap(g_idx, alpha=0.05)]  #this is for other subcategory

            else:
                myAlpha = 0.9  # I want to reduce 0.2 from alpha for each subgroup
                for sg_idx in range(NumTopNames + 1):  # +1 for considering others subcategory
                    subGroupColors = subGroupColors + [mymap(g_idx, alpha=myAlpha)]
                    myAlpha -= 0.1

        mypie, _ = ax.pie(group_size, radius=1.3, colors=groupColors, labels=group_names)
        # ax.set(title=title,y=1.08)
        plt.setp(mypie, width=0.3, edgecolor='black')
        plt.title(title, y=1.08, fontsize=20)

        if legend:
            plt.legend(mypie, group_names,
                       title="Clusters",
                       loc="upper right",
                       bbox_to_anchor=(0.1, 0.1))

        # Second Ring (Inside)
        mypie2, _ = ax.pie(subgroup_size, radius=1.3 - 0.3, labels=subgroup_names, labeldistance=0.5,
                           rotatelabels=True,
                           colors=subGroupColors)
        plt.setp(mypie2, width=0.5, edgecolor='black')
        plt.margins(0, 0)

    ###########################################################################################
    # Plot Radar Chart
    ###########################################################################################

    def plotRadarChart(self, instance_RS, ClusterLabels, outputFolder):

        clusterObjAvgDF = pd.DataFrame()

        for target in range(self.BestNumberClusters):
            clusterObjAvgDF["Cluster{}".format(np.str(target))] = instance_RS.obj[ClusterLabels == target].mean()

        df = clusterObjAvgDF.iloc[0:6].transpose()

        # ------- PART 1: Create background

        # number of variable
        categories = list(df)[0:]
        N = len(categories)

        # What will be the angle of each axis in the plot? (we divide the plot / number of variable)
        angles = [n / float(N) * 2 * np.pi for n in range(N)]
        angles += angles[:1]

        # Initialise the spider plot
        ax = plt.subplot(111, polar=True)

        # If you want the first axis to be on top:
        ax.set_theta_offset(np.pi / 2)
        ax.set_theta_direction(-1)

        # Draw one axe per variable + add labels labels yet
        plt.xticks(angles[:-1], categories)

        # Draw ylabels
        ax.set_rlabel_position(0)
        plt.yticks([-3, -1, 1, 3], ["-3", "-1", "1", "3"], color="grey", size=7)
        plt.ylim(-3, 3)

        plt.title("Quality Radar Chart for each cluster")

        # ------- PART 2: Add plots

        # Plot each individual = each line of the data
        # I don't do a loop, because plotting more than 3 groups makes the chart unreadable
        mymap = plt.get_cmap("Dark2")
        mymapColors = iter(plt.cm.rainbow(np.linspace(0, 1, self.BestNumberClusters)))

        from itertools import cycle

        myColors = cycle('bgrcmk')
        #
        for target in range(self.BestNumberClusters):
            values = df.iloc[target].values.flatten().tolist()
            values += values[:1]
            ax.plot(angles, values, linewidth=3, linestyle='solid', label="Cluster{}".format(np.str(target)))

            # ax.fill(angles, values, next(myColors) , alpha=0.2)
            ax.fill(angles, values, alpha=0.4)

        # Add legend
        plt.legend(loc='upper right', bbox_to_anchor=(0.1, 0.1))
        plt.savefig(outputFolder+"/QualityRadarChart.png")
        # plt.show()
