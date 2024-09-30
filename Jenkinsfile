pipeline {
    agent any
    tools {
        maven 'maven-3.6'  // Maven tool configuration
    }
    stages {
        stage('build jar') {
            steps {
                script {
                    echo "Building JAR..."
                    sh 'mvn package'
                }
            }
        }
        stage('build image') {  // Исправлено название stage
            steps {
                script {
                    echo "Building Docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-cr', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t denchikkarate/demo-app:jma-2.0 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push denchikkarate/demo-app:jma-2.0'
                    }
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo "Deploying the application..."
                }
            }
        }
    }
}
