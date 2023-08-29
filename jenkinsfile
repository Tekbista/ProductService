pipeline{
    agent any
    
    environment {
        // Define environment variables here if needed
        MAVEN_HOME = tool 'MAVEN'
        Docker = tool "docker"
    }
    stages{
        stage('Fetch from Git'){
            steps{
                // Clone the repository from github
                checkout scmGit(branches: [[name: '*/main']], extensions: 
[], userRemoteConfigs: [[url: 
'https://github.com/Tekbista/ProductService.git']])
            }
        }
        stage('Test') {
            steps {
                // Run tests using Maven
                sh "${MAVEN_HOME}/bin/mvn clean test"
            }
        }
        
        stage('Build') {
            steps {
                // Build the Spring Boot application using Maven
                sh "${MAVEN_HOME}/bin/mvn clean package -DskipTests"
            }
        }
        
        stage('Build Docker Image'){
            steps{
                // Build docker image
                sh "docker build -t tekbista37545/productservice-1.0.0 ."
            }
        }
        
        stage('Push Image to DockerHub'){
            steps{
                // Login to the docker hub 
                withCredentials([string(credentialsId: 'dockerhubpwdid', 
variable: 'dockerhubpwd')]) {
                    sh 'docker login -u tekbista37545 -p ${dockerhubpwd}'
                }
                // Push docker image to dockerhub
                sh "docker push tekbista37545/productservice-1.0.0 "
            }
        }
    }
}
