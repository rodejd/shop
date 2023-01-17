pipeline {
    agent any

    stages {

            stage('Build') {
                steps {
                    sh 'chmod +x gradlew'
                    sh './gradlew clean build'
                    sh '/app/jenkins/libs/gradle/bin/gradle bootJar'
                }
                post {
                    success {
                        echo 'gradle build success'
                    }

                    failure {
                        echo 'gradle build failed'
                    }
                }
            }


        stage('Registry Push') {
            steps {
                sh "docker build --tag 127.0.0.1:5000/wepinit-shop:master ."
                sh "docker push 127.0.0.1:5000/wepinit-shop:master"
            }
            post {
                success {
                    echo 'Registry Push success'
                }

                failure {
                    echo 'Registry Push failed'
                }
            }
        }

        stage('Dockerizing'){
            steps{
                sh 'echo " Image Bulid Start"'
                sh 'ssh -i /app/jenkins/ssh_key/wepinit/wepinit_shop wepinit@13.209.132.5 -p 22 "docker-compose -f /app/docker-compose.yml pull wepinit-shop"'
                sh 'ssh -i /app/jenkins/ssh_key/wepinit/wepinit_shop wepinit@13.209.132.5 -p 22 "docker-compose -f /app/docker-compose.yml up -d wepinit-shop"'
            }
            post {
                success {
                    sh 'echo "Bulid Docker Image Success"'
                }

                failure {
                    sh 'echo "Bulid Docker Image Fail"'
                }
            }
        }


    }
}