// webhook enable version 1.0
pipeline {
  agent { label 'slave-server' }
  environment {
        SCANNER_HOME= tool 'sonar-scanner'
    }
  stages{
    stage('1.Clone'){
      steps {
        git branch: 'master', url:'https://github.com/bus57790/maven-web-application.git'
      }
    }
    stage('2.Test'){
      steps {
        withMaven(globalMavenSettingsConfig: '', jdk: 'jdk-1.8', maven: 'maven2', mavenSettingsConfig: '', traceability: true) {
          sh 'mvn test'
        }
      }
    }
    stage('3.Trivy FS Scan') {
      steps {
          sh 'trivy fs --format table -o fs.html .'
      }
    }
    stage('4.Build'){
      steps {
        withMaven(globalMavenSettingsConfig: '', jdk: 'jdk-1.8', maven: 'maven2', mavenSettingsConfig: '', traceability: true) {
          sh 'mvn clean package'
        }
      }
    }
    stage('5.SonarQube Analysis') {
        steps {
            withSonarQubeEnv('sonar-server'){ //the server we set up in System
                sh '''
                $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=delta-pipeline -Dsonar.projectKey=delta-pipeline \
                -Dsonar.java.binaries=target '''
            }
         }
      }
      stage('6.Publish Artifacts') {
        steps {
            withMaven(globalMavenSettingsConfig: '', jdk: 'jdk-1.8', maven: 'maven2', mavenSettingsConfig: '', traceability: true) {
                sh 'mvn deploy'
            }
         }
      }
      stage('7.Deploy to web server') {
        steps {
          deploy adapters: [tomcat9(alternativeDeploymentContext: '', credentialsId: 'tomcat-admin', path: '', url: 'http://192.168.1.198:8083/')], contextPath: null, war: 'target/*war'
          }
        }
   }
}
