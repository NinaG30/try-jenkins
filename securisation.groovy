pipeline {
    agent any
    environment {
        MY_SECRET = credentials('my-secret-test')
    }
    stages {
        stage('Utiliser Secret') {
            steps {
                sh 'echo $MY_SECRET'
            }
        }
    }
}