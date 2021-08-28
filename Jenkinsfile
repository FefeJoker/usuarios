#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('permissions setup'){
            steps{
                sh 'chmod 777 -R /var/lib/jenkins/workspace/DAN-Lab-Usuarios_develop'
            }
        }
        stage('clean') {
            when {
                branch 'master'
            }
            steps {
                sh "java -version"
                sh "./mvnw clean"
            }
        }
        stage('clean-develop') {
            when {
                branch 'develop'
            }
            steps {
                sh "java -version"
                sh "./mvnw clean"
                sh "echo buildeando develop"
            }
        }
        stage('maven install and docker build') {
            steps {
                sh "mvn clean install"
                sh "mvn clean package spring-boot:repackage"
                sh "docker build -t guillegregoret/usuarios ."
            }
        }
        stage('docker run mirror 1') {
            steps {
                sh 'docker ps -f name=usuario-service-1 -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=usuario-service-1 -q | xargs -r docker container rm'
                sh "docker run -d --name usuario-service-1 -it --cpu-shares="256" --memory="512m" --network hosts guillegregoret/usuarios"
            }
        }
        stage('docker run mirror 2') {
            steps {
                sh 'docker ps -f name=usuario-service-2 -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=usuario-service-2 -q | xargs -r docker container rm'
                sh "docker run -d --name usuario-service-2 -it --cpu-shares="256" --memory="512m" --network hosts guillegregoret/usuarios"
            }
        }
        stage('docker run mirror 3') {
            steps {
                sh 'docker ps -f name=usuario-service-3 -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=usuario-service-3 -q | xargs -r docker container rm'
                sh "docker run -d --name usuario-service-3 -it --cpu-shares="256" --memory="512m" --network hosts guillegregoret/usuarios"
            }
        }
    }
}
