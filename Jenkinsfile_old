pipeline {
  agent any
  environment {
        SCANNER_HOME= tool 'sonar-scanner'
    }
  stages{
    stage('1.Clone'){
      steps {
        git branch: 'master', url:'https://github.com/betechsol/maven-web-application.git'
      }
    }
    stage('2.Test'){
      steps {
        withMaven(globalMavenSettingsConfig: '', jdk: 'jdk11', maven: 'maven', mavenSettingsConfig: '', traceability: true) {
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
        withMaven(globalMavenSettingsConfig: '', jdk: 'jdk11', maven: 'maven', mavenSettingsConfig: '', traceability: true) {
          sh 'mvn clean package'
        }
      }
    }
    stage('5.SonarQube Analysis') {
        steps {
            withSonarQubeEnv('sonar-server'){ //the server we set up in System
                sh '''
                $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=delta-pipleline -Dsonar.projectKey=delta-pipeline \
                -Dsonar.java.binaries=target '''
            }
        }
    }
    stage('6.Publish Artifacts') {
        steps {
            withMaven(globalMavenSettingsConfig: '', jdk: 'jdk11', maven: 'maven', mavenSettingsConfig: '', traceability: true) {
                sh 'mvn deploy'
            }
        }
    }
    stage('7.Deploy to web server') {
      steps {
          deploy adapters: [tomcat9(alternativeDeploymentContext: '', credentialsId: 'tomcat-admin', path: '', url: 'http://44.246.164.237:8080/')], contextPath: null, war: 'target/*war'
      }
    }
  }
}
