pipeline {
    agent any
    stages {
        stage('Build Image') {
            steps {
                script {
                    docker.build("yourrepo/yourmicroservice:${env.BUILD_ID}")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
                    socker.withRegistry('https://registry.hub.docker.com', 'dockerHubCredentials') {
                        docker.image("yourrepo/yourmicroservice:${env.BUILD_id}").push()
                    }
                }
            }
        } 
        stage('Deploy') {
            steps {
                // utilisez des scripts ou des outils de déploiement pour mettre à jour votre service avec la nouvelle image
                echo 'Deployer le service avec la nouvelle image Docker'
            }
        }
    }
}