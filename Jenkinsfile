node {
   def mvnHome
   stage('Prebuild') {
      // pull code from repo
      git 'https://github.com/olarinde/dailylog.git'
      // MAVEN_HOME configured in global configuration - http://localhost:8080/jenkins/configure
      mvnHome = tool 'MAVEN_HOME'
   }
   stage('Build') {
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.war'
   }
}
