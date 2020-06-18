node {
  label 'generic'
  git(branch: 'master',
      credentialsId: 'gitsshkey',
      url: 'git@github.com:Saar-m/netomedia.git')
  jobDsl(targets: ['Pipelines/*.groovy'].join('\n'))
}
