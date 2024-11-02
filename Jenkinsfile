def gv

pipeline {
    agent any
    stages {
        stage("build jar prod") {
            steps {
                script {
                    echo "building jar"
                }
            }
        }
        stage("build image prod") {
            steps {
                script {
                    echo "building image"
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    def dockerCmd = 'docker run -p 8888:8080 denchikkarate/demo-app:jma-1.0'
                    sshagent(['deniswork-key-server1']) {
                        sh "ssh -o StrictHostKeyChecking=no deniswork@192.168.100.7 ${dockerCMd}"
                    }
                }
            }
        }
    }   
}
