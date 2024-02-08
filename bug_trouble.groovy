pipeline {
    agent any
    stages {
        stage('Checkout') {
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                git url: 'https://github.com/yourrepo/yourapp.git', branch: main
            }
        }
    }
    // Ajoutez d'autre étapes avec catchError pour les diagnotics
    post {
        failure {
            mail to: 'your.email@example.com',
            subject: "Echec du build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: "Le build a echoue. Veuillez vérifier"
        }
    }
}