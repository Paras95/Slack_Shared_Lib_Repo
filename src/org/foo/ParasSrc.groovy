package org.foo
@Grab('org.apache.commons:commons-math3:3.4.1')
import org.apache.commons.math3.primes.Primes

//package org.foo
class ParasSrc implements Serializable {
  def script
  def steps
  def buildPlanPath = 'target/surefire-reports/TEST-com.mycompany.app.AppTest.xml.xml'
  ParasSrc(steps, script) {
    this.steps = steps
    this.script = script
    
  }

  void parallelize(int count) {
  if (!Primes.isPrime(count)) {
    steps.echo "${count} was not prime"
  }
}
 
def parseXML()
{
  def buildPlan = steps.new XmlParser().parseText(readFile(buildPlanPath))
  steps.echo "${buildPlan.getClass()}"
  steps.echo "${buildPlan}"
  steps.echo "${buildPlan.branch}"


}


  def mvn(args) {
    steps.sh "${steps.tool 'maven'}/bin/mvn -o ${args}"
  }
  
  def env(){
    steps.echo "build number----------${script.env.BUILD_NUMBER}"
    steps.sh 'mvn install'
    steps.sh 'mvn test'
    steps.echo "testing.......1,2,3"
    steps.archiveArtifacts artifacts: 'target/surefire-reports/*.xml', fingerprint: true
    def test = steps.junit 'target/surefire-reports/*.xml'    
    steps.slackSend (
                channel: "#general",
                color: '#007D00',
                message: "\n *Test Summary* - ${test.totalCount}, Failures: ${test.failCount}, Skipped: ${test.skipCount}, Passed: ${test.passCount}"
    )
  }

}
  
  

