pipelineJob('BuildTest') {
//   parameters {
//     stringParam('DOCKER_IMG_NAME', '', 'Docker image name')
//     stringParam('SCM_PROJECT_NAME', '', 'SCM Origin to build')
//     stringParam('SCM_BRANCH', 'master', 'SCM branch to build')
//     stringParam('GIT_SHORT_HASH', '', 'Git short hash')
//     stringParam('GIT_BRANCH_FRIENDLY', '', 'Git friendly branch name')
//   }
  triggers{
      githubPush()
  }
  definition {
    cps {
      script('''
            pipeline {
                agent {
                label 'generic'
                }
                environment {
                DOCKER_ORG="gcr.io/homework-268814"
                }
                stages {      
                    stage('Git checkout'){
                        steps {
                            script{
                                git credentialsId: 'gitsshkey', url: 'git@github.com:Saar-m/netomedia.git', branch: 'master'
                            }
                        }
                    }          
                    stage('Build Docker Image') {
                        steps {
                            script {
                                app = docker.build("gcr.io/homework-268814/nm","-f Product/Dockerfile Product/")
                            }
                        }   
                    }
                    stage('Push Docker Image') {
                        steps {
                            script {
                                docker.withRegistry('https://gcr.io/', 'gcr:homework-268814'){
                                    app.push("${env.BUILD_NUMBER}")
                                    app.push("latest")
                                }
                            }
                        }
                    }
                }
                
                post {
                always {
                    cleanWs()
                }
            }
        }
      ''')
    }
  }
}