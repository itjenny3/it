Skill Set
---------
 - Spring : Java Framework
 - Spring Social : Social Login (facebook, twitter, google+)
 - Spring Mobile : Spring Mobile MVC
 - Spring Security : Authentication, Authorization
 - Hibernate : SQL mapping Framework
 - Maven : Build & Dependency Management
 - Bootstrap : CSS Framework
 - Tiles2 : JSP Layout Framework
 - Selenium : Automatic Web Browser Test Framework
 - JUnit : Java Test Framework
 - Mockito : Mocking Framework
 - Hamcrest : library of matchers
 - Lombok : Getter/Setter 생략 가능한 Library
 - Guava, Apache-Commons : Collection 라이브러리
 - Logback : log 프레임워크
 - MoreUnit : Code, Test Code 이동 툴
 - Emma : Code Coverage 검사 툴 (for eclipse)
 - Cobertura : Code Coverage 검사 툴 (for jenkins)
 - Checkstyle : Coding Convention 검사 툴
 - FindBugs : 코드 정적 분석 툴
 - Fabric : 파이썬 배포 라이브러리
 
 
개발 환경 세팅
--------------
### JDK ###

[설치하기](http://support.apple.com/kb/DL1572)

### MySQL ###

[설치하기](http://dev.mysql.com/downloads/mysql/)

### Eclipse EE ###

[다운로드](http://www.eclipse.org/downloads/)

설치 후 eclipse.ini에서 -Xms512m로 변경
 
### Maven ###

[다운로드](http://maven.apache.org/download.html)

    2.2.1 버전 다운로드
    /etc/paths에 maven의 bin 폴더를 PATH로 추가

### Tomcat ###

[다운로드](http://tomcat.apache.org/download-60.cgi)

### Lombok ###

[다운로드](http://projectlombok.org/mavenrepo/index.html)

lombok.jar를 더블클릭하여 수행하여 설치한다.

### Project 생성 ###

	$ git clone https://github.com/itjenny3/it.git
	$ mvn eclipse:clean eclipse:eclipse

 - Eclipse의 Package Explorer에 우클릭 후 Import > General > Existing Projects into Workspace 선택 후 Browser를 클릭한다.

 - Package Explorer에서 it 우클릭 > Configure > Convert To Maven Project를 수행하여 maven project로 생성한다.


### 브라우저 설치 ###

Selenium 테스트를 위해 아래 브라우저 설치한다.

 - Internet Explorer
 - Chrome
 - Firefox
 
### private.properties 생성 ###

/src/main/resources 에 private.properties 파일에 소셜 로그인을 위한 앱 설정 및 이메일 발송을 위해 설정한다.

	twitter.consumerKey=
	twitter.consumerSecret=
	facebook.clientId=
	facebook.clientSecret=
	google.clientId=
	google.clientSecret=
	
	email.smtp=smtp.gmail.com
	email.port=587
	email.id=
	email.password=

### 타켓 서버에 publickey 설정 ###

 - 타켓 서버에 ~/.ssh 폴더를 700 권한으로 생성한다.
 - ssh-keygen -t rsa -C "이메일" : 인증키 생성한다.
 - 생성된 id_rsa.pub파일을 ~/.ssh/authorized_keys로 이름을 변경하여 타켓 서버가 복사한다.

### Python 배포용 fabric 설치 ###

 - Python 2.5 이상 설치한다.
 - easy_install을 이용하여 pip 설치한다.

		sudo easy_install pip

 - pip를 이용하여 fabric을 설치한다.

		 sudo pip install fabric


Eclipse Setting
---------------
### Tomcat 연동 ###

 - Preference > Server > Runtime Environments > Add 하여 Tomcat 다운로드 경로 추가

### Maven 연동 ###

 - Eclipse > Preference > Maven > Installations > Add 하여 Maven 다운로드 경로 추가


### Plugin 설치 ###

Eclipse > Help > Eclipse MarketPlace 클릭 후 아래 키워드를 검색한다.

 - Maven integration for eclipse

 - MoreUnit
 
		[사용법]
		Ctrl + j : 구현 코드와 테스트 코드 간 이동한다.
		Ctrl + r : 구현 코드에서 테스트코드를 수행한다.
		
 - Checkstyle

		URL : http://eclipse-cs.sf.net/update
		Help > Eclipse Marketplace > Checkstyle 검색 후 설치 (5.x 버전만 설치함)
		활성화 : 프로젝트 이름을 우클릭 후 Checkstyle > Activate Checkstyle
		View : Window > Show View > Others > Checkstyle > Checkstyle violations
	
 - Emma
 	
		Help > Eclipse Marketplace > Emma 검색 후 설치
		검사 : 프로젝트 이름을 우클릭 후 Coverage As > Junit Test 수행 후 아래 view에서 결과 확인한다.
		View : Window > Show View > Others > Java > Coverage

 - FindBugs
 
		Help > Eclipse Marketplace > FindBugs 검색 후 설치
		분석 : 프로젝트 명 우클릭 > Find bugs > Find bugs 수행 후 아래 view에서 결과 확인한다.
		View : Window > Show View > Find Bugs > Bug Explorer	

 - Terminal

		Help > Eclipse Marketplace > terminal 검색 후 설치
		Project 우클릭 > Open Terminal Here

 - Vrapper (optional)

		Vim plugin
		설치하면 상단에 V 모양의 이미지가 추가된다.  클릭하면 vim 모드이다.
 		

### 설정 ###
 - Java Convention을 기본 템플릿으로 사용함

        Preference > Java > Code Style > Formatter에서 New를 클릭하여 새로운 profile 생성 후 Edit를 눌러 아래 내용을 수정한다.
        Indentation > General settings > Tab policy: spaces only
        Indentation > General settings > Indentation size: 4
        Indentation > General settings > Tab size: 4
    
 - JavaScript Convention을 기본 템플릿으로 사용함

        Preference > JavaScript > Code Style > Formatter에서 New를 클릭하여 새로운 profile 생성 후 Edit를 눌러 아래 내용을 수정한다.
        Indentation > General settings > Tab policy: spaces only
        Indentation > General settings > Indentation size: 4
        Indentation > General settings > Tab size: 4

 - XML 설정

        Preference > XML > XML Files > Editor : Line width 80으로 설정
        Preference > XML > XML Files > Editor : Indent using spaces 선택
        Preference > XML > XML Files > Editor > Indentation size : 4 설정

 - CSS 설정

        Preference > Web > CSS Files > Editor : Line width 80으로 설정
        Preference > Web > CSS Files > Editor : Indent using spaces 선택
        Preference > Web > CSS Files > Editor > Indentation size : 4 설정

 - HTML 설정

        Preference > Web > HTML Files > Editor : Line width 80으로 설정
        Preference > Web > CSS Files > Editor : Indent using spaces 선택
        Preference > Web > CSS Files > Editor > Indentation size : 4 설정

 - Checkstyle 설정파일 불러오기

		Preference > Checkstyle > New 선택하여 아래 설정대로 입력한다.

		Type : External Configuration File
		Name : tinyCheckstyle
		Location : tinyCheckstyle.xml

 - Encoding 설정

		Preference > Web > CSS Files > Encoding : ISO 10646/Unicode(UTF-8)
		Preference > Web > HTML Files > Encoding : ISO 10646/Unicode(UTF-8)
		Preference > Web > JSP Files > Encoding : ISO 10646/Unicode(UTF-8)
        Preference > General > Workspace > Text file encoding : UTF-8 선택
		Preference > General > Content Types > Text > *.txt 선택 후 UTF-8을 입력 후 Update
		Preference > General > Content Types > Text > *.md 추가 후 UTF-8을 입력
		Preference > General > Content Types > Text > Java Properties Files > *.properties > UTF-8을 입력 후 Update
		Preference > General > Content Types > Text > Javascript Source File > *.js > UTF-8을 입력 후 Update
		Project 우클릭 > properties > Resource > Text file encoding > Other : UTF-8 선택
	
 - 개행문자 (LF 사용, Not CRLF)

		Preference > General > Workspace > New text file line delimiter : Unix

 - colors and Fonts/Basic

		Preferences/General/Editors/File Associations/*.md 파일에 Text Editor 추가한다.

 - 자주 쓰는 static import 등록

		Preference > Java > Editor > Templates > New Type에 클릭 후 아래 내용 추가한다.

		name : ti
		context : java
		description : ti 입력 후 Ctrl + Space 입력 시 아래 package가 자동 import
	
		pattern : 
		import static org.hamcrest.Matchers.*;
		import static org.junit.Assert.*;
		import static org.junit.matchers.JUnitMatchers.*;
		import static org.mockito.Matchers.*;
		import static org.mockito.Mockito.*;

### resources 순서 변경 ###

eclipse에서 local 설정을 적용하기 위해 다음과 같이 설정한다.  
Project Properties > Java Build Path > Order and Export 에서 아래와 같은 순서로 설정 변경한다.
 	
	src/main/java
	src/main/resources
	src/main/resources-local
	src/test/java

### 검색 시 특정 폴더 제외 ###

Project Properties -> Resource -> resource filter

    exclude all, folder 선택
    All children (recursive) 클릭
    File and Folder Attributes에 bin 추가
	

운영 서버 설치
-----------------------------------
### JDK ###

설치하기

    $ yum install java-1.6.0-openjdk.x86_64 java-1.6.0-openjdk-devel.x86_64
    [Java 다운로드 참조](http://www.oracle.com/technetwork/java/javaee/downloads/index.html)

### MySQL ###

설치하기

	$ yum install mysql-server

/etc/my.cnf 파일에 다음 내용 추가하여 한글 지원
	
	[mysqld]
	character-set-server=utf8
	collation-server=utf8_general_ci
	init_connect=SET collation_connection=utf8_general_ci
	init_connect=SET NAMES utf8

	[client]
	default-character-set=utf8

	[mysql]
	default-character-set=utf8
	
[MySQL 다운로드 참고](http://dev.mysql.com/downloads/mysql)

시작하기

	$ service mysqld start
### Tomcat ###

설치하기

	$ yum install tomcat6-webapps tomcat6-admin-webapps
	
시작하기

	$ service tomcat6 start
	
mvn으로 tomcat에 배포하기 위해서 /usr/share/tomcat6/conf/tomcat-users.conf 파일에 아래 내용 추가한다.

	<tomcat-users>
	    <role rolename="manager"/>
	    <user username="admin" password="암호" roles="manager"/>
	</tomcat-users>

UTF-8을 지원하기 위해 server.xml파일에 아래 내용 중 가장 아래 줄을 추가한다.
맨 위에 Port를 8080에서 80으로 변경한다.

	<Connector port="80" protocol="HTTP/1.1" 
		connectionTimeout="20000" 
		redirectPort="8443" 
		URIEncoding="UTF-8" />
	
pom.xml 파일에 배포 서버 IP 설정

	!-- mvn tomcat:deploy 배포 가능 -->
	<plugin>
		<groupId>org.codehaus.mojo</groupId>
		<artifactId>tomcat-maven-plugin</artifactId>
		<version>${tomcat-maven-plugin.version}</version>
		<configuration>
			<server>tomcat6</server>
			<url>http://54.248.89.221:8080/manager</url>
			<path>/ROOT</path>
		</configuration>
	</plugin>
	
local의 maven폴더/conf/settings.xml에 아래 내용 추가한다.

	<server>
		<id>tomcat6</id>
		<username>admin</username>
		<password>암호</password>
	</server>


최초 배포 시 아래 명령어를 이용한다.

	mvn tomcat:deploy

최초 배포가 아닐 경우 아래 명령어를 이용한다.

	mvn tomcat:undeploy tomcat:deploy
	
### ssh로 War 파일 배포 ###

/usr/share/tomcat6/webapps의 root권한이 필요한 폴더에 ssh로 WAR파일을 배포하기 위하여 아래 부분 수정 필요하다.  
/etc/sudoers 파일에 아래 부분 주석 처리한다.

	Defaults    requiretty
