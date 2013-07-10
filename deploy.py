from fabric.api import *

env.hosts = ['itjenny.com']

TOMCAT = "/usr/share/tomcat6"
WARFILE = "ROOT"

def deploy():
	""" deploy """
	print
	print " * deploy"
	build()
	copyWar()
	deleteWebappsFolder()
	tomcatRestart()

def deleteWebappsFolder():
	with settings(user = 'root'):
		run("rm -rf %s/webapps/%s" % (TOMCAT, WARFILE))

def copyWar():
	local("scp ./target/%s.war root@itjenny.com:%s/webapps/" % (WARFILE, TOMCAT))

def build():
	print
	print " * maven packge"
	local('mvn package')

def tomcatRestart():
	""" tomcat restart """
	print " * tomcat restart"
	with settings(user = 'root'):
		run("service tomcat6 restart")
	
