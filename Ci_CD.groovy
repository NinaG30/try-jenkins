pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven 3'
    }
    stages {
        stage('Checkout') {
            steps {
                git url 'https://github.com/yourrepo/yourapp.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean package"
            }
        }
        stage('Test') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn test"
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                // ajoutez ici les étapes pour déployer dans l'environment de staging
                echo "Déploiement dans l'environment de staging"
            }
        }
        stage('Production') {
            when {
                branch 'main'
            }
            steps {
                // ajoutez ici les étapes pour déployer dans l'environment de production
                echo "Déploiement en production"
            }
        }
    }
}