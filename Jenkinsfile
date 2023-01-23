pipeline {
  agent any
  tools {
    maven '3.8.7'
  }
  stages {
    stage ('Build ...') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage ('Deploy ...') {
      steps {
        script {
          deploy adapters: [custom-tomcat-8.5(credentialsId: 'tomcat_credential', path: '', url: 'http://localhost:8085')], contextPath: '/api-weather', onFailure: false, war: 'webapp/target/*.war'
        }
      }
    }
  }
}