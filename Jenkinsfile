node {

   def mavenHome
   def javaHome

   stage('Prebuild') {
      // pull code from repo
      git 'https://github.com/olarinde/dailylog.git'
      // MAVEN_HOME configured in global configuration - http://localhost:8080/jenkins/configureTools/
      mavenHome = tool 'maven'
      javaHome = tool 'jdk1.8.0'
   }

   stage('Build') {
      withEnv(["JAVA_HOME=$javaHome",
               "PATH+MAVEN=$mavenHome/bin:${env.JAVA_HOME}/bin"]){
	      if (isUnix()) {
	         sh "'${mavenHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
	      } else {
	         bat(/"${mavenHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
	      }
      }
   }

   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.war'
   }

}
