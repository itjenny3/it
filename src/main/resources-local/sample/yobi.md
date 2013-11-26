Yobi
=======
[![Build Status](https://travis-ci.org/nforge/yobi.png?branch=master)](https://travis-ci.org/nforge/yobi)


Yobi, collaborative SW development platform.<br/>(Currently, 0.3 - work in progress)


What is Yobi?
---

Yobi, a brand new version of nFORGE, is a web-based collaborative platform for software development.
Yobi offers many features to increase productivity and quality of your software: a issue tracker to manage bugs and issue, a wiki style board to share documents, a configuration management tool to control software version and so on.

License
--
Copyright 2013 NAVER Corp, under the Apache 2.0 license.

## Installation

### check java version

    java -version

    Required minimum java version is 7(1.7)

### download playframework

    curl -O http://downloads.typesafe.com/play/2.1.0/play-2.1.0.zip

    or

        wget http://downloads.typesafe.com/play/2.1.0/play-2.1.0.zip

        or using web browser (for windows)

            http://downloads.typesafe.com/play/2.1.0/play-2.1.0.zip

### unzip

    unzip play-2.1.0.zip

### cd to unzipped directory

    cd play-2.1.0

### download yobi

    git clone https://github.com/nforge/yobi.git

    You can also make your own yobi directory in any other place. But in that case, you should add playframework home path to $PATH environment.


### cd to cloned yobi directory

    cd yobi


### run play framework

    ../play

    Required files will be download automatically. In the first time, it may take about 10 min or more.


### type run command in console

    run

    It will downloaded addtional files and compile sources.

    If you want to run yobi in production mode, use **start** , not **run**.

### connect with browser

    http://127.0.0.1:9000

    If you want to change port, check your permission to use 80 port

    see [http://www.playframework.com/documentation/2.1.1/Production](http://www.playframework.com/documentation/2.1.1/Production)

