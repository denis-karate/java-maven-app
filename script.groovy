def buildJar() {
    echo "building the application v2prod ..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image v2prod ..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-cr', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t denchikkarate/demo-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push denchikkarate/demo-app:jma-2.0'
    }
} 

def deployApp() {
    echo 'deploying the application v2 prod ...'
} 

return this
