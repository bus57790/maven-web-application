pipeline {
  agent any
  stages{
    stage('1.clonecode'){
      steps {
        git branch: 'master', url:'https://github.com/betechsol/maven-web-application.git'
      }
    }
    stage('2.mavenBuild'){
      steps {
        withMaven(globalMavenSettingsConfig: '', jdk: 'jdk11', maven: 'maven', mavenSettingsConfig: '', traceability: true) {
          sh 'mvn clean package'
        }
      }
    }
    stage('3.sonarscan'){
      steps {
        withMaven(globalMavenSettingsConfig: '', jdk: 'jdk11', maven: 'maven', mavenSettingsConfig: '', traceability: true) {
          sh 'mvn sonar:sonar'
        }
      }
    }
  }
}
