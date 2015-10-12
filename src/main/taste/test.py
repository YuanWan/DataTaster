__author__ = 'Yuan'
# import os
# import sys
# spath = "/sentiment_mod.py"
#
# sys.path.append(os.path.abspath(spath))

import sentiment_mod as s

print(s.sentiment("plot was wonderful,"))
print(s.sentiment("This movie was awesome! The acting was great, plot was wonderful, and there were pythons...so yea!"))
print(s.sentiment("This movie was utter junk. There were absolutely 0 pythons. I don't see what the point was at all. Horrible movie, 0/10"))
print(s.sentiment("Volkswagen messed up, but the emissions scandal won"))

print(s.sentiment("VOLKSWAGEN POLO FACTORY WORKSHOP MANUAL SELF STUDY PROGRAMME!"))
