/*
 * Uses the latest Jenkins LTS. It's extremely simple since almost all decisions need to be based on the infrastructure,
 * e.g. if we have Docker available then we would use an docker agent or if we have a Nexus to push to.
 */
pipeline {
    agent any
    tools {
        maven 'maven:latest'
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('archive') {
            steps {
                archiveArtifacts(artifacts: 'target/outfitr-*.jar', onlyIfSuccessful: true, fingerprint: true)
            }
        }
    }
}
