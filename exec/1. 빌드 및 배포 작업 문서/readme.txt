## 배포는 Docker를 사용하는 것을 기준으로 설명합니다. 

# WAS		: Apache Tomcat (REST API 제공)
 gradlew bootJar를 통한 jar파일 생성 -> docker에서 실행
 IDE 활용시 IntelliJ Ultimate 2021.1 이상에서 실행


# 사용한 JVM	: azul/zulu-openjdk:8
 -> docker build -f Dockerfile -t spring .
 -> docker run -it -d -p 8080:8080 --name springServer spring
 Dockerfile을 텍스트뷰어로 확인가능
 docker 설치 후 Dockerfile활용 빌드시 배포 가능

특이사항
 - Docker파일과 같은 위치에 .jar파일을 한개만 두고 실행해주세요.


# WebServer	: Nginx (Vue.js dist제공)
 -> docker run --name nginx -d -p 80:80 -p 443:443 -v /home/user/nginx/:/var/www/html nginx
 Nginx 설정파일은 nginxConfigFiles 폴더에서 텍스트뷰어로 확인 가능
 default파일은 	/etc/nginx/sites-available/ 에 복사
 nginx.conf파일은	/etc/nginx/ 에 복사
 dist폴더 내 파일들	/var/www/html/에 복사

 아래 명령어를 nginx의 bash에서 실행
 -> sudo apt install certbot python3-certbot-nginx 
 -> sudo certbot --nginx -d i5a108.p.ssafy.io

특이사항
 - default 파일 내 springServer의 주소를 잡아줘야합니다 (도커 내 링크 미사용)
 - springServer의 주소는 -> docker network inspect bridge를 통해 확인 가능


# DB		: MySQL
 접속정보		: A108 / 0000
 DB접속정보는 /backend/photoServer/src/main/resources/application.properties 에 있습니다.
 별도의 DB를 활용할 경우 DBdump파일을 통해 DB생성뒤 연결해주세요.
 ERD는 사진파일로 첨부합니다.


* 주의사항 *
 /frontend/src/assets/js 폴더에 있는
 axios.js, axiosNotJWT.js 에 있는 serverURL을 springTomcat의 주소로 설정해주십시오.
 Nginx활용시 'https://도메인/api/'로 설정해 주십시오.

