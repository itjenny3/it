CREATE DATABASE IF NOT EXISTS itjenny;
USE itjenny;
DROP TABLE IF EXISTS `article`;

CREATE TABLE IF NOT EXISTS `article` (
	`title` VARCHAR(255),
	`content` TEXT,
	`published` INTEGER DEFAULT 0,
    PRIMARY KEY (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT
INTO `article` 
	(`title`,
	`content`,
	`published`)
VALUES ("git",
		"Skill Set\n---------\n - Spring : Java Framework\n - Spring Social : Social Login (facebook)\n - Spring Mobile : Spring Mobile MVC\n - Mybatis : SQL mapping Framework\n - Ehcache : cache Framework\n - Maven : Build & Dependency Management\n - Bootstrap : CSS Framework\n - Tiles2 : JSP Layout Framework\n - JUnit : Java Test Framework\n - Mockito : Mocking Framework\n - Lombok : Getter/Setter 등 불필요한 코드 생략 가능한 Library\n - MoreUnit : Code, Test Code 이동 툴\n - Emma : Code Coverage 검사 툴 (eclipse)\n - Cobertura : Code Coverage 검사 툴 (jenkins)\n - Checkstyle : Coding Convention 검사 툴\n - FindBugs : 코드 정적 분석 툴\n \n \n 개발 환경 세팅\n ---------------\n ### JDK ###\n \n 설치하기\n \n $ yum install java-1.6.0-openjdk.x86_64 java-1.6.0-openjdk-devel.x86_64\n \n [Java 다운로드 참조](http://www.oracle.com/technetwork/java/javaee/downloads/index.html)\n \n ### MySQL ###\n \n 설치하기\n \n $ yum install mysql-server\n \n /etc/my.cnf 파일에 다음 내용 추가하여 한글 지원\n \n [mysqld]\n character-set-server=utf8\n collation-server=utf8_general_ci\n init_connect=SET collation_connection=utf8_general_ci\n init_connect=SET NAMES utf8\n \n [client]\n default-character-set=utf8\n \n [mysql]\n default-character-set=utf8\n \n [MySQL 다운로드 참>고](http://dev.mysql.com/downloads/mysql)\n \n 시작하기\n \n $ service mysqld start\n \n 기본 DB, Table 생성하기\n \n $ mysql -u root < src/main/db/create.sql\n \n ### Spring Tool Suite ###\n \n [다운로드](http://www.springsource.org/downloads/sts)\n \n ### Maven ###\n \n [다운로드](http://maven.apache.org/download.html)\n \n ### Lombok ###\n \n [다운로드](http://projectlombok.org/mavenrepo/index.html)\n \n ### Project clone ###\n \n STS의 File > Import > Projects from Git > URI 선택 후 아래 설정값을 입력한다.\n \n URI : ssh://git@github.com:[ID]/it.git\n Host : github.com\n Repository path : [ID]/it.git\n Protocol : ssh\n Port : 없음\n User : git\n Password : 없음\n \n Import existing projects 시 No projects found 라는 에러가 발생하면 아래 단계를 수행 후 다시 시도한다.  \n Package Explorer에서 template 우클릭 > Configure > Convert To Maven Project를 수행하여 maven project로 생성한다.\n \n ### Eclipse project >생성 ###\n \n 다운로드 받아진 template project에서 경로에서 아래와 같이 입력하여 eclipse project를 생성한다.\n \n $ mvn eclipse:clean eclipse:eclipse\n \n ### markdown4j 추가 ###\n \n markdown4j.jar 파일을 로컬 저장소에 아래 스크립트를 수행하여 저장한다.\n \n $ ./src/main/lib/deploy-lib.sh\n \n ### 한글 처리 ###\n \n TC server를 사용하는 경우 Spring으로 유입되는 parameter의 한글을 UTF-8로 처리하기 위해서는 server.xml을 다음과 같이 수정한다.\n \n <Connector ... useBodyEncodingForURI=true URIEncoding=UTF-8/>\n \n ### 브>라우저 설치 ###\n \n Selenium 테스트를 위해 아래 브라우저 설치한다.\n - Internet Explorer\n - Chrome\n - Firefox\n \n ### 타켓 서버>에 publickey 설정 ###\n \n - 타켓 서버에 ~/.ssh 폴더를 700 권한으로 생성한다.\n - ssh-keygen -t rsa -C 이메일 : 인증키 생성한다.\n - 생성된 id_rsa.pub파일을 ~/.ssh/authorized_keys로 이름을 변경하여 타켓 서버가 복사한다.\n \n ### Python 배포용 fabric 설치 ###\n \n - Python 2.5 이상 설치한다.\n - easy_install을 이용하여 pip 설치한다.\n \n sudo easy_install pip\n \n - pip를 이용하여 fabric을 설치한다.\n \n sudo pip install fabric\n \n \n STS Setting\n -----------\n ### Eclipse Plugin 설치 ###\n \n STS > Help > Install New Software... 클릭 후 work with에 설치하고자 plugin URL을 입력한다.\n \n - MoreUnit\n \n 검색 : Help > Eclipse Marketplace > MoreUnit >검색 후 설치\n URL : http://moreunit.sourceforge.net/update-site\n \n [사용법]\n Ctrl + j : 구현 코드와 테스트 코드 간 이동한다.\n Ctrl + r : 구현 코드에서 테스트코드를 수행한다.\n \n - Checkstyle\n \n URL : http://eclipse-cs.sf.net/update\n Help > Eclipse Marketplace > FindBugs 검색 후 설치\n 활성화 : 프로젝트 이름을 우클릭 후 Checkstyle > Activate Checkstyle\n View : Window > Show View > Others > Checkstyle > Checkstyle violations\n \n - Emma\n \n Help > Eclipse Marketplace > Emma 검색 후 설치\n 검사 : 프로젝트 이름을 우클릭 후 Coverage As > Junit Test 수행 후 아래 view에서 결과 확인한다.\n View : Window > Show View > Others > Java > Coverage\n \n - FindBugs\n",
		1);
		
INSERT
INTO `article` 
	(`title`,
	`content`,
	`published`)
VALUES ("qa",
		"Skill Set\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer2\n\n",
		1);
		
		