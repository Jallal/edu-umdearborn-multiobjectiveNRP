# -*- coding: utf-8 -*-
"""
Created on Thu Jul 27 16:27:33 2017

@author: vahid
"""

#import re
path = 'C:/Users/vahid/Desktop/Refactoring-solutions1.tab'
new_path = 'C:/Users/vahid/Desktop/Refactoring-solutions1_new.tab'

def remove_text_inside_brackets(text, brackets="()[]"):
    count = [0] * (len(brackets) // 2) # count open/close brackets
    saved_chars = []
    for character in text:
        for i, b in enumerate(brackets):
            if character == b: # found bracket
                kind, is_close = divmod(i, 2)
                count[kind] += (-1)**is_close # `+1`: open, `-1`: close
                if count[kind] < 0: # unbalanced bracket
                    count[kind] = 0  # keep it
                else:  # found bracket to remove
                    break
        else: # character is not a [balanced] bracket
            if not any(count): # outside brackets
                saved_chars.append(character)
    return ''.join(saved_chars)


solutions = open(path,'r')
text = solutions.read()

new_text = remove_text_inside_brackets(text)

new_solutions = open(new_path,'w')
new_solutions.write(new_text)

solutions.close()
new_solutions.close()

print("Done")









