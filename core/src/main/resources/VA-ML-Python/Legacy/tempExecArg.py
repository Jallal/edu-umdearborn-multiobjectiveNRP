"""
Created on 3/8/2018 , 1:34 AM

@author: vahid
"""
import sys


def version():
    print (sys.version)


def test1(a , b):
    sum = a + b
    print ('sum = {}'.format (sum))


def justprint():
    print ("hi hoy hey")


if __name__ == "__main__":

    try:
        version ()
        justprint ()
        arg1 = int (sys.argv[ 1 ])
        arg2 = int (sys.argv[ 2 ])
        test1 (arg1 , arg2)
    except:
        print ("Error")
