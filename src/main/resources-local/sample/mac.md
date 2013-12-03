시스템 환경 설정
==============
 - 키보드

        키 반복 : 젤 빠르게
        반복 지연 시간 : 젤 짧게
        모든 F1, F2 등의 키를 표준 기능 키로 사용 
        키보드 > 조합키 : Caps Lock을 Control로 변경함.
        키보드 > 단축키 > > 입력 소스 : 입력 메뉴에서 다음 소스 선택 disable 
        키보드 > 단축키 > Spotlight : Spotlight 검색 필드 보기 : F1
        키보드 > 입력 소스 : 두벌식, U.S. 빼고 삭제
        Dock 가리기 켜기/끄기 : disable

 - 언어 및 텍스트

        입력 소스 : 두벌식/영어 만 설정

 - 트랙패드

        탭하여 클릭하기 : enable

 - Dock

        화면에서의 위치 : 오른쪽
        자동으로 Dock 가리기와 보기 : enable

 - Mission Control

        핫코너 : 왼쪽 하단 - 모니터 잠자기

 - Spotlight 설정

        Finder에 단축키 설정
        Spotlight : 하단 - Spotlight 윈도우 키보드 단축키 : Cmd + e 설정

 - 보안 및 개인 정보 (KeyRemap4Macbook 설치하기 위해서)

        다음에서 다운로드한 App 허용
        모든 곳 : enable

Finder 설정
==========
 - 바탕 화면에서 외장 디스크 아이콘 없애기 위해서 아래와 같이 설정

        외장 디스크 : disable

 - 현재 위치를 표시하기 위해서 아래 설정 추가

        보기 > 경로 막대 보기

 - 시스템 한글 폰트 수정 (나눔고딕)
 
 		MacOSXDefaultFontFallbacksChanger.command 수행


설치 프로그램
===========
 - [JDK 설치](https://developer.apple.com/downloads/index.action?q=java)
 
        애플 로그인 후 Java for OS X 2013-005 Developer Package 다운로드 (150MB)

 - Keynote
 - Office
 - Chrome, Firefox
 - KeyRemap4Macbook : 한영키를 Shift + space로 변경

         Change Key > For Korean > Make Command_R, Option_R as HanEng > Shift+Space to Command+Space : enable
         Key Repeat > Baasic Configurations > Key Repeat > Delay Until Repeat : 100ms
         Key Repeat > Baasic Configurations > Key Repeat > Key Repeat : 20ms

 - [iTerm2 설치](http://www.iterm2.com/)

        Preferences > Appearance > Dim background window : inactive terminal은 dim 처리함.
        
 - [iTerm2과 Finder 연동](http://hssuh.tistory.com/241)


시스템 설정
==========
 - /etc/sudoers 파일 맨 아래에 내용 추가
 	- sudo 명령어 칠 때 비밀번호 물어보지 않음

        [계정] ALL=(ALL) NOPASSWD: ALL

 - backup.sh를 참조하여 설정한다.


개발환경 세팅
============
 - .m2/repository/com/klocwork을 현재 폴더에 klocwork.tar.gz으로 대체하자.


