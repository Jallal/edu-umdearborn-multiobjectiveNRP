"""
Created on 3/28/2018 , 7:59 AM

@author: vahid
"""

import os
import sys


def AddSystemPath(systempath):
    # sys.path.insert (0 , os.path.abspath (systempath))
    sys.path.insert (0 , systempath)
    print ("Path has been added: " + systempath)

    for p in sys.path:
        print (p)
if __name__ == "__main__":
    try:
        AddSystemPath (sys.argv[ 1 ])
    except Exception as exc:
        raise exc
