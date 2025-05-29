pipeline {
  tools {
    maven 'maven'
    jdk 'jdk'
  }
  stage('1.clonecode'){
    git branch: 'master', url:'https://github.com/betechsol/maven-web-application.git'
  }

  stage('2.mavenBuild'){
    withMaven(globalMavenSettingsConfig: '', jdk: 'jdk11', maven: 'maven', mavenSettingsConfig: '', traceability: true) {
    'mvn clean package'
    }
  }
}
