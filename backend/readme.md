    // 자유롭게 작성해주세요

# Backend Readme File

### 기술스택
    1. Spring Boot
    2. JPA
    3. JWT
    4. Amazon EC2, S3
    5. Docker
    6. Jenkins
    7. Nginx

### 서버 배포 절차
 - 개요
> 서버배포에 필요한 기술스택은 크게 Ubuntu활용, Docker, Nginx이며, Jenkins는 자동빌드 및 배포를 위한 CI/CD용 앱이다.  
> 우분투 서버 위에 젠킨스를 통해 빌드된 FE,BE 파일들을 Docker에 올려 각각 Nginx와 Spring서버로 올리는 일을 한다.  
> 이를 통해 FE는 Nginx를 통해, BE는 springServer라는 이름으로 Docker의 컨테이너가 담당을 하게된다.  

## 과정
### 1. Jenkins 설치
> Jenkins설치는 Google검색을 통해 쉽게 설치가 가능하다.  
> 설치 중 필요한 플러그인은 기본 플러그인들에 Gitlab, NodeJS를 추가하여 설치한다.  
> 설치가 완료되면 먼저 Gitlab연결 및 Gitlab용 Credentials를 생성한다  

### 2. Jenkins 설정

> Gitlab 설정  
> Jenkins의 시스템설정  
> -> host URL = https://lab.ssafy.com/  
> -> Credentials는 Gitlab API토큰을 사용    
> (Token 발급 : Gitlab계정(중요. 프로젝트가 아님)의 설정에서 API에 꼭 체크하고 발급  
> -> Test Connection 확인  

> 파이프라인에서 사용할 Credentials을 미리 등록 (Git pull을 위한 Credentials)  
> Jenkins의 Manage Credentials  
> -> 새로운 일반 Credentials를 추가  
> -> Gitlab의 아이디/비번 입력 (이름은 비워두면 자동생성)  
> -> 85366c56-6c... 같은 형태로 생성되며 Name부분에 아이디/**** 와 같이 표시된다면 완료  

> 이 외에 Git이나 NodeJS의 설정을 잡아준다.  

### 3. Jenkins 파이프 라인 설정
> 파이프라인은 자동으로 실행될 명령어들의 모음이며, 시작은 사용자에 의해 수동으로 시작할 수 있지만  
> 대표적으로 Git의 Push Event발생을 받아와 실행하는 것이 대표적이다.  

> 파이프라인을 생성  
> -> Build Triggers  
> -> Gitlab의 Push Event  
> -> Jenkins의 URL이 자동생성, 고급을 눌러 Secret Key탭에서 Key 발급  
> -> Gitlab의 프로젝트 -> 설정 -> Webhook -> 추가(URL, Secret Key)  
> -> push Event를 보낼 브랜치 설정  

    > 파이프라인 내용
    pipeline {
        agent any
        // 사용할 tool들을 설정
        tools {
            nodejs "nodejs"
            git "git"
        }
        stages {
            stage('prepare') {
                steps {
                    // git에서 repo를 pull 받아오며 브랜치,아이디/비번으로 만든 Credentials, URL을 설정한다.
                    git branch: 'BEdevelop',
                    credentialsId: '85366c56-6c79-4043-accc-2c9a6ca4b9d3',
                    url: 'https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A108'
                    // --prefix 옵션은 실행하려는 경로가 현재폴더(pwd)와 다른경우 추가한다.
                    // npm install 진행
                    sh 'npm install --prefix ./frontend'
                }
            }
            stage('BE build') {
                steps {
                    // Git에 올리지 않은 추가 설정파일을 복사해 넣은뒤 gradlew로 jar파일을 생성한다.
                    sh 'cp ../application-aws.properties ./backend/photoServer/src/main/resources/'
                    // gradlew 실행 가능하도록 권한 수정
                    sh 'chmod +x ./backend/photoServer/gradlew'
                    // 빌드 폴더를 비우고
                    sh './backend/photoServer/gradlew clean -p ./backend/photoServer/'
                    // 빌드
                    sh './backend/photoServer/gradlew bootJar -p ./backend/photoServer/'
                }
                post {
                    success {
                        echo 'success'
                    }
                }
            }
            stage('FE build') {
                steps {
                    // npm run build 실행
                    sh 'npm run --prefix ./frontend build'
                }
                post {
                    success {
                        echo 'success'
                    }
                }
            }
            stage('copy files') {
                steps {
                    // jar파일 저장할 폴더를 만들고(있다면 무시) 기존은 jar파일들을 삭제하고 jar파일을 정해진위치로 복사
                    sh 'mkdir -p ../jar'
                    sh 'rm -r -f ../jar/*'
                    sh 'cp -r ./backend/photoServer/build/libs/*.jar ./'
                    // Nginx와 vind된 폴더로 FE Build 내용을 (dist폴더) 복사
                    sh 'rm -r -f ../dist/*'
                    sh 'cp -r ./frontend/dist/* ../dist/'
                }
            }
            stage('docker distribution') {
                steps {
                    // 기존의 springServer 컨테이너를 삭제하고
                    sh 'docker rm -f springServer'
                    // 기존의 이미지도 삭제
                    sh 'docker image rm -f spring'
                    // 새로운 jar파일로 docker build를 진행하여 Image 생성
                    sh 'docker build -f ../Dockerfile -t spring .'
                    // 새로운 이미지로 springServer 컨테이너 생성 및 실행
                    sh 'docker run -d -p 8080:8080 --name springServer spring'
                }
            }
        }
    }

### 4. Docker 설치
> Docker 자체의 설치도 Google을 참조하면 쉽게 설치가 가능하다. Docker Hub도 이용한다면 자동배포에 좀 더 쉬운면이 있다.  
> Docker Hub는 마치 Github처럼 이미지를 인터넷에 올리고 쉽게 다시 내려받을 수 있는 서비스이다.  

### 5. Docker 기능 설명
> Docker는 Linux기반 굉장히 가벼운 가상머신이라고 생각하면 쉽다.  
> Image라는 설치 파일을 갖고 설치를 빠르게 할 수 있으며 이를 설치하면 Container라고 부르는 프로그램이 생성된다고 보면 된다.  
> Container는 프로그램처럼 껐다 켰다 할 수 있고 물론 삭제도 가능하다.  
> 우리는 이제 Nginx와 BE개발을 통해 만든 springServer, 두개의 이미지로 서비스를 할 예정이다.  

### 6. Docker Image 생성
    docker run --name nginx -d -p 80:80 443:443 -v /home/user/nginx/:/var/www/html nginx
> 위와 같이 이미지 다운로드 및 컨테이너 생성, 실행까지 쉽게 진행 가능하다.  
> 이는 Docker에서 Nginx 정식 Image파일을 제공하기 때문에 자동으로 모두 진행된다.  
> 위의 명령에 옵션이 많은데 자주 사용하는 옵션들은 아래에 설명하겠다.  

> 우리가 개발한 springServer같은 경우 위와 같은 형식으로 진행할 수 없다. 하려면 Docker Hub를 사용하면 받아와 설치하는 과정을 할 수는 있지만 하지않았다.  
> 우리는 Jar파일을 만들어 이미지를 생성하여 바로 사용하는 방식을 택했다.  
> Jar파일은 Java환경 위에 설치하여 실행이 가능하다.  
> 그렇기 때문에 약간의 기초설정이 필요한데 이를 Dockerfile이라는 파일을 제작하여 이미지를 만들때 활용한다.  

    // Dockerfile  
    FROM azul/zulu-openjdk:8  
    EXPOSE 8080  
    COPY ./*.jar server.jar  
    ENTRYPOINT ["java","-jar","server.jar"]  

> azul/zulu-openjdk:8 환경에서 실행할 것이며,  
> 8080포트를 컨테이너에게 접근이 가능하도록 하고 (컨테이너 안에서 접근 가능하다는 얘기 밖까지 열어준 것은 아님)  
> jar파일을 server.jar라는 이름으로 컨테이너 안으로 복사하며,  
> 추후 이 컨테이너가 실행될때 항상 'java -jar server.jar' 라는 명령이 실행된다는 뜻을 가진다.  

    docker build -f ../Dockerfile -t spring .  
    
> -f 는 Dockerfile 위치, -t는 이미지이름  
> 이렇게 하면 docker는 자동으로 zulu jdk 8을 다운받고 jar파일을 이미지로 만들어 준다.  

### 7. Docker container 실행
    docker run -d -p 8080:8080 --name springServer spring  
> -d : detach 현재 보고있는 커맨드창에 실행되는 컨테이너의 입출력을 붙이지 않는다. (뗀다)  
> -p : port 바깥쪽:안쪽  ex. 9090:8080 밖에서 9090포트로 연결하면 컨테이너에게는 8080포트로 연결된다.  
> --name : container의 이름을 설정  
> 마지막은 실행할 이미지 이름으로 이루어져 있다.  

> 만약 컨테이너의 실행상태를 보고싶다면(spring의 로그를 보고싶다) -d를 빼고 실행하거나  
> 실행 후 docker attach springServer 를 통해 붙일 수 있다.  

### 8. Nginx 설치 및 설정
    docker run --name nginx -d -p 80:80 -v /home/user/nginx/:/usr/share/nginx/html nginx  
> --name : 컨테이너 이름  
> -d detach : 현재 커맨드창과 입출력을 붙이지 않는다 (뗀다)  
> -p port : 바깥쪽:안쪽  ex. 9090:8080 밖에서 9090포트로 연결하면 컨테이너에게는 8080포트로 연결된다.  
> -v vind : 현재 내가 사용하는 환경을 폴더와 컨테이너 내부의 폴더 하나씩 1대1로 바인딩한다.  
> 바인딩이되면 파일을 올리면 컨테이너안에도 파일이 생기고 컨테이너가 파일을 지우면 사용자쪽에서도 지워진다. 말그대로 같은폴더가 된다고 보면 된다.  
> 이를 통해 쉽게 컨테이너 안으로 파일을 옮겨 보낼 수 있다.  
> ex) 사용자폴더:컨테이너폴더 = /home/user/nginx/:/usr/share/nginx/html  
> 마지막은 이미지의 이름으로 이루어져 있다.  

> Nginx는 기본적으로 /usr/share/nginx/html/ 폴더에 html파일이 있으면 80포트를 통해 서비스를 제공한다.  
> 이는 아주 기본적인 제공이며, 추가적인 부분을 아직 진행하지 않았다.  

### 9. Nginx https 설정 및 springServer와의 proxy연결
> Nginx를 통해 https를 제공하고 배포 및 백엔드 서버와 연결하기위해 새로 컨테이너를 만들었다.  
> docker run -d -p 80:80 -p 443:443 -v /var/lib/jenkins/workspace/dist/:/var/www/html/ --name nginxTLS --link springServer: nginx  
> 포트 80, 443 개방  
> 배포를 위한 폴더 바인딩  
> springServer와 링크(하지만 링크되지않음 이유를 모르겠음)  

> docker exec -it nginxTLS /bin/bash로 컨테이너sh 진입 후  
> 편집기(VI), certbot 설치  
> sudo apt install vim  
> sudo apt install certbot python3-certbot-nginx  

default config 파일 제작  

    upstream spring {
        // 주소설정
        server 172.17.0.3:8080;
        // 100개의 Connection을 항상 유지함으로 빠른 반응
        keepalive 100;
    }
    server {
        // FE dist파일 위치
        root /var/www/html;
        index index.html index.htm;

        server_name i5a108.p.ssafy.io;

        // '/' 접근시 FE 파일 제공
        location / {
            try_files $uri $uri/ /index.html;
        }

        // '/api'로 접근시 BE서버 제공
        location /api {
            // docker network link를 통해 연결해야하나  
            // 잘 안되어서 springServer의 docker 내부 ip주소를 하드코딩하였다.  
            // 이 방법을 쓸 바에는 springServer의 docker network할당을 nginx와 붙여  
            // localhost의 포트로만 연결하는 것이 더 좋아보이는 방법이다.  
            // 추후 시간이 되면 수정하겠음  

            // api글자를 떼고 서버에 전달
            rewrite /api/(.*) /$1 break;
            proxy_pass http://spring;
            proxy_redirect off;
            charset utf-8;

            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
            proxy_set_header X-Nginx-Proxy true;
        }
    }

> nginx.config 파일에 해당 파일 include  
> sudo certbot --nginx -d i5a108.p.ssafy.io  


