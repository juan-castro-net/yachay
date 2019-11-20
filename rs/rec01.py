#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu May 23 08:06:43 2019

@author: juancastro
"""
import turicreate

data = turicreate.SFrame({
'user_id': ["Ann", "Ann", "Ann", "Brian", "Brian", "Brian"],
'item_id': ["Item1", "Item2", "Item4", "Item2", "Item3", "Item5"],
'rating': [1, 3, 2, 5, 4, 2]})
m = turicreate.factorization_recommender.create(data, target='rating')

recommendations = m.recommend()
print(recommendations)

recommendations = m.recommend(users=['Brian'])
print(recommendations)


recommendations = m.recommend(['Charlie'])
print(recommendations)

m.save("my_model.model")














