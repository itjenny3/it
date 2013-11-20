#!/usr/bin/python
import sys
import os.path
from subprocess import call

if len(sys.argv) < 2:
	print "usage] %s [filename]" % sys.argv[0]
	sys.exit(1)

filename = " ".join(sys.argv[1:])
call("curl -X POST --data-urlencode content@%s http://localhost:8080/article/%s" % (filename, os.path.basename(filename)), shell = True)

sys.exit(1)





if len(sys.argv) < 3:
	print "usage] %s [local|it] [filename]" % sys.argv[0]
	sys.exit(1)

filename = " ".join(sys.argv[2:])

if sys.argv[2] == "it":
	call("curl -X POST --data-urlencode content@%s http://localhost:8080/article/%s" % (filename, os.path.basename(filename)), shell = True)
else:
	call("curl -X POST --data-urlencode content@%s http://14.33.43.57:8080/article/%s" % (filename, os.path.basename(filename)), shell = True)
