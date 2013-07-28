from fabric.api import *

env.hosts = ['14.33.43.57']

TOMCAT = "/usr/share/tomcat6"
WAR = "ROOT"

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
		run("rm -rf %s/webapps/%s" % (TOMCAT, WAR))

def copyWar():
	local("scp ./target/%s.war root@%s:%s/webapps/" % (WAR, env.host, TOMCAT))

def build():
	print
	print " * maven packge"
	local('mvn clean package')

def tomcatRestart():
	""" tomcat restart """
	print " * tomcat restart"
	with settings(user = 'root'):
		run("/etc/init.d/tomcat6 restart", pty = False)
