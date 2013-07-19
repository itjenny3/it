from fabric.api import *

env.hosts = ['14.33.43.57']

TOMCAT = "/usr/share/tomcat6"
WARFILE = "ROOT.war"

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
	local("scp ./target/%s root@%s:%s/webapps/" % (WARFILE, env.host, TOMCAT))

def build():
	print
	print " * maven packge"
	local('mvn package')

def restart():
	""" tomcat restart """
	print " * tomcat restart"
	with settings(user = 'root'):
		run("/etc/init.d/tomcat6 restart", pty = False)
