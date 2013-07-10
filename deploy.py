from fabric.api import *

env.roledefs = {
	'tiny' : ['itjenny.com'],
}
env.hosts = ['itjenny.com']

def deploy():
	""" deploy """
	print
	print " * deploy"
	local("scp ./target/ROOT.war root@itjenny.com:/usr/share/tomcat6/webapps/")
	tomcatRestart()

def tomcatRestart():
	""" tomcat restart """
	print " * tomcat restart"
	with settings(user = 'root'):
		run("service tomcat6 restart")
	
