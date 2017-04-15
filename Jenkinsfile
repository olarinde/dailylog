node {

   def javaHome = tool 'jdk1.8.0'
   // MAVEN_HOME configured in global configuration - http://localhost:8080/jenkins/configureTools/
   def mavenHome = tool 'maven'

   stage('Prebuild') {
      echo "initialising $javaHome"
      echo "initialising $mavenHome"
   }

   stage('Build') {
      // pull code from repo
      git 'https://github.com/olarinde/dailylog.git'
      
      withEnv(["PATH+MAVEN=${mavenHome}/bin"]) {
        sh 'mvn -Dmaven.test.failure.ignore clean package'
      }
   }

   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.war'
   }

}
