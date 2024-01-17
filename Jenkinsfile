pipeline {
    agent any

    environment {
        BASE_URI = 'https://api.spotify.com'
        QA_URL = 'https://api.spotify.com'
        UAT_URL = 'https://api.spotify.com'
        PROD_URL = 'https://api.spotify.com'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    withEnv(['BASE_URI=' + BASE_URI]) {
                        // Set up Maven
                        def mvnHome = tool 'Maven_Central'
                        env.PATH = "${mvnHome}/bin:${env.PATH}"
                        sh 'mvn clean install'
                    }
                }
            }
        }

        stage('Run Tests - QA') {
            steps {
                script {
                    withEnv(['BASE_URI=' + BASE_URI]) {
                            echo 'Running  In QA environment'
                            sh "mvn test -DBASE_URI=${QA_URL}"
                        }
                    }
                }
            }
        }

        stage('Run Tests - UAT') {
            steps {
                script {
                    withEnv(['BASE_URI=' + BASE_URI]) {
                        // Run Selenium tests for UAT environment
                        echo 'Running  In UAT environment'
                        sh "mvn test -DBASE_URI=${UAT_URL}"
                    }
                }
            }
        }

        stage('Run Tests - Prod') {
            steps {
                script {
                    withEnv(['BASE_URI=' + BASE_URI]) {
                        // Run Selenium tests for Production environment
                        echo 'Running  In PROD environment'
                        sh "mvn test -DBASE_URI=${PROD_URL}"
                        }
                }
            }
        }

        stage('Deploy to Prod') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('UNSTABLE') }
            }
            steps {
                script {
                    // Perform deployment to Production environment
                    echo 'Deploying to Production...'
                    // Add deployment steps as needed
                }
            }
        }
    }

    post {
        success {
            // Notify or perform additional actions on successful build
            echo 'Build and Tests Passed!'
        }
        failure {
            // Notify or perform actions on build failure
            echo 'Build or Tests Failed!'
        }
    }
}

