#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu May 23 08:16:44 2019

@author: juancastro
"""

import turicreate as tc

m = tc.load_model("my_model.model")

recommendations = m.recommend(['Charlie'])
print(recommendations)


