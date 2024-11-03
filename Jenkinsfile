#!/usr/bin/env groovy

pipeline {
    agent any

    tools {
        maven 'maven-3.6'
    }

    environment {  
        IMAGE_NAME = 'denchikkarate/demo-app:jma-aws'
    }

    stages {
        stage("Load Libraries") {
            steps {
                script {
                    library identifier: 'jenkins-shared-library@master',
                        retriever: modernSCM([
                            $class: 'GitSCMSource',
                            remote: 'https://github.com/denis-karate/jenkins-shared-library.git',
                            credentialsId: 'github-credentials'
                        ])
                }
            }
        }

        stage("Build JAR") {
            steps {
                script {
                    buildJar()  
                }
            }
        }

        stage("Build Image") {
            steps {
                script {
                    buildImage(env.IMAGE_NAME)  
                }
            }
        }

        stage("Deploy") {
            steps {
                script {
                    def dockerCmd = "docker run -d -p 8887:8080 ${IMAGE_NAME}"
                    sshagent(['deniswork-key-server1']) {
                        sh "ssh -o StrictHostKeyChecking=no deniswork@192.168.100.7 ${dockerCmd}"
                    }
                }
            }
        }
    }
}

