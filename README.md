javamut - permutations in Java
==============================

javamut generates all the permutations (possible combinations) of a set of conditions (also known as factors).
By specifying the levels (the number of values) for each condition, javamut can create a table that contains every
possible combination of the conditions, known as a full factorial design.  The table can then be used in an experiment
to examine how each condition affects the results.

For example, suppose you're about to conduct a traffic study and want to break the results down by season and time of
day.  Your first condition might be Season with four levels Spring, Summer, Autumn, Winter; your second condition might
be Time Of Day with two levels Day and Night.  The full factorial design in this case would look something like

| Season | Time Of Day |
| -------|------------ |
| Spring | Day         |
| Summer | Day         |
| Autumn | Day         |
| Winter | Day         |
| Spring | Night       |
| Summer | Night       |
| Autumn | Night       |
| Winter | Night       |

With only two conditions and a few levels per condition this table is easy to generate by hand, but the complexity rises
quickly as you add conditions.  If we add a third condition Vehicle with three levels Car, Truck/SUV, and Commercial there
are (2*4*3) 24 combinations; if we add a fourth condition Speed Limit with three levels 30, 40, and 50 now there are
(2*4*3*3) 72 combinations, and so on.

Credits
-------
The full factorial expansion algorithm is based on the [PyDOE](http://pythonhosted.org/pyDOE/) Design of Experiments for
Python project, itself based on the full factorial code provided in the
[Scilab SciDOE package](http://forge.scilab.org/index.php/p/scidoe/) by the following authors:

* Copyright (C) 2012-2013 - Michael Baudin
* Copyright (C) 2012 - Maria Christopoulou
* Copyright (C) 2009 - Yann Collette
* Copyright (C) 2001 - Per A. Brodtkorb