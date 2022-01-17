package org.foo
@Grab('org.apache.commons:commons-math3:3.4.1')
import org.apache.commons.math3.primes.Primes

//package org.foo
class ParasSrc implements Serializable {
  def script
  def steps
  
  ParasSrc(steps, script) {
    this.steps = steps
    this.script = script
  }

  void parallelize(int count) {
  if (!Primes.isPrime(count)) {
    steps.echo "${count} was not prime"
  }
}
 
def readXml(def path) {
  steps.echo "from readXml"
        def text = steps.readFile(path)
        steps.echo "from readXml12"
        def parser = new XmlParser()
        steps.echo "from readXml 123"
        def xml = parser.parseText(text.toString())
        steps.echo "from readXml 1234"
        return xml
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
  
  

