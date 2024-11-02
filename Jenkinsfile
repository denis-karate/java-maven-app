#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    libraries {
        library identifier: 'jenkins-shared-library@master', 
            retriever: modernSCM([
                $class: 'GitSCMSource',
                remote: 'https://github.com/denis-karate/jenkins-shared-library.git',
                credentialsId: 'github-credentials'
            ])
    }
    enviroment {
        IMAGE_NAME = 'denchikkarate/demo-app:jma-aws'
    }
    stages {
        stage("build jar") {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    buildImage(env.IMAGE_NAME)
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    def dockerCmd = 'docker run -d -p 8887:8080 denchikkarate/demo-app:jma-2.0'
                    sshagent(['deniswork-key-server1']) {
                        sh "ssh -o StrictHostKeyChecking=no deniswork@192.168.100.7 ${dockerCmd}"
                    }
                }
            }
        }
    }   
}
