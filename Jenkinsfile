pipeline {
  agent any
  stages{
    stage('1.clonecode'){
      steps {
        git branch: 'master', url:'https://github.com/betechsol/maven-web-application.git'
      }
    }
    stage('2.mavenBuild'){
      withMaven(globalMavenSettingsConfig: '', jdk: 'jdk11', maven: 'maven', mavenSettingsConfig: '', traceability: true) {
      sh 'mvn clean package'
      }
    }
  }
}
