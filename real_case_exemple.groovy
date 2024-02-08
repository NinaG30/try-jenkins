pipeline {
    agent any {
        environment {
            DOCKER_IMAGE = "dockerhubusername/myapp"
            KUB_CONFIG = credentials('kubeconfig-id')
            DOCKER_CREDENTIALS_ID = 'dockerhub-credentials-id'
        }
        stages {
            stage('Checkout') {
                steps {
                    git url: 'https://github.com/yourrepo/myapp.git', branch: 'main'
                }
            }
            stage('Build') {
                steps {
                    sh 'mvn clean package'
                }
            }
            stage('Build Docker Image') {
                steps {
                    script {
                        docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}").push("${env.BUILD_ID}", {
                            withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                                sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
                            }
                        })
                    }
                }
            }
            stage('Deploy to Kubernetes') {
                steps {
                    withCredentials([file(credentialsId: "${KUBE_CONFIG}", variable: 'KUBECONFIG')]) {
                        sh """
                        kubectl set image deployment/myapp-deployment myapp-container=${DOCKER_IMAGE}:${env.BUILD_ID} --record
                        """
                    }
                }
            }
        }
    }
    post {
        always {
            sh 'mvn clean'
            // Nettoyer les images Docker localement si nécessaire
        }
        success {
            mail to: 'team@yourdomain.com',
            subject: "Déploiement réussi: My App ${env.BUILD_NUMBER}",
            body: "Nouvelle version de l'application déployée en production: ${DOCKER_IMAGE}:${env.BUILD_ID}"
        }
        failure {
            mail to: 'team@yourdomain.com',
            subject: "Echec du déploiement: My App ${env.BUILD_NUMBER}",
            body: "Le déploiement a échoué. Veuillez vérifier les logs de jenkins pour plus de détails ."
        }
    }
}