#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage ('Build') {
            git url: 'https://github.com/FefeJoker/usuarios'
            withMaven {
                sh "mvn clean verify"
            } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
        }
    }
    post {
        success{
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
        always{
            archiveArtifacts artifacts: '**/target/site/**', fingerprint: true
            publishHTML([allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/site',
                        reportFiles: 'index.html',
                        reportName: 'Site'
            ])
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            jacoco ( execPattern: 'target/jacoco.exec')
            recordIssues enabledForFailure: true, tools: [mavenConsole(), java(), javaDoc()]
            recordIssues enabledForFailure: true, tools: [checkStyle()]
            recordIssues enabledForFailure: true, tools: [spotBugs()]
            recordIssues enabledForFailure: true, tools: [cpd(pattern: '**/target/cpd.xml')]
            recordIssues enabledForFailure: true, tools: [pmdParser(pattern: '**/target/pmd.xml')]
        }
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
}
