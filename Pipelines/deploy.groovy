
pipelineJob('Deploy') {
  definition {
    cps {
      script('''
            pipeline {
                agent any
                environment {
                    PROJECT_ID = 'homework-268814'
                    CLUSTER_NAME = 'homework-saar'
                    LOCATION = 'us-central1-c'
                    CREDENTIALS_ID = 'homework-268814'
                    TAG = 
                }
                stages {
                    stage('Wait for user to input text') {
                        steps {
                            script {
                                def TAG = input(id: 'TAG', message: 'Which tag to deploy? (for example: 1.2)',
                                parameters: [[$class: 'ImageTagParameterDefinition', defaultValue: '1.0', 
                                    description:'docker image tag', name:'TAG', image: 'homework-268814/nm',registry: 'gcr.io/homework-268814/nm']
                                ])
                                println(TAG);
                            }
                        }

                    }
                    stage('Deploy to GKE') {
                        steps{
                            step([
                            $class: 'KubernetesEngineBuilder',
                            projectId: env.PROJECT_ID,
                            clusterName: env.CLUSTER_NAME,
                            location: env.LOCATION,
                            manifestPattern: 'netomedia/ClusterConfig/product/templates/deployment.yaml',
                            credentialsId: env.CREDENTIALS_ID,
                            verifyDeployments: true])

                            sh'kubectl -n nm set image deployment/nm-netomedia-prod netomedia-prod=nm:${TAG} --record'
                        }
                    }
                }
            }
      ''')
    }
  }
}


