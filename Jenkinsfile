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
        withMaven(globalMavenSettingsConfig: '', jdk: 'java17', maven: 'maven2', mavenSettingsConfig: '', traceability: true) {
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
        withMaven(globalMavenSettingsConfig: '', jdk: 'java17', maven: 'maven2', mavenSettingsConfig: '', traceability: true) {
          sh 'mvn clean package'
        }
      }
    }
   }
}
