"""
Created on 3/28/2018 , 4:36 AM

@author: vahid
"""

import pip

def import_or_install(package):
    try:
        __import__(package)
    except ImportError:
        pip.main(['install', package])

def ListOfInstalledModules():
    sorted ([ "%s==%s" % (i.key , i.version) for i in pip.get_installed_distributions () ])

ListOfInstalledModules()

# import_or_install('numpy')