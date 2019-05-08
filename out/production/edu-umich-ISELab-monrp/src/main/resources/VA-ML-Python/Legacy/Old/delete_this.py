# -*- coding: utf-8 -*-
"""
Created on Sat Aug 12 20:38:42 2017

@author: vahid
"""

import math 
class Point:
# static class variable, point count 
    count = 0
    def __init__(self, x, y): 
        self.x = float(x) 
        self.y = float(y) 
        Point.count += 1
    def __str__(self): 
        return \
        '(x={}, y={})'.format(self.x, self.y) 
        
    def to_polar(self):
        r = math.sqrt(self.x**2 + self.y**2) 
        theta = math.atan2(self.y, self.x) 
        return(r, theta)
# static method – trivial example ... 
    def static_eg(n):
        print ('{}'.format(n)) 
#    static_eg = staticmethod(static_eg)
    
# Instantiate 9 points & get polar coords 
for x in range(-1, 2): 
    for y in range(-1, 2): 
        p = Point(x, y) 
        print (p) # uses __str__() method 
        print (p.to_polar())
        print (Point.count) # check static variable 
        
#Point.static_eg(9) # check static method