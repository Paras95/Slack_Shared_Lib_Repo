
package org.foo
class ParasSrc implements Serializable {
  def script
  def steps
  ParasSrc(steps, script) {
    this.steps = steps
    this.script = script
  }
  def mvn(args) {
    steps.sh "${steps.tool 'maven'}/bin/mvn -o ${args}"
  }
  
  def env(){
    steps.echo "build number----------${script.env.BUILD_NUMBER}"
    steps.sh 'mvn install'
    steps.sh 'mvn test'
    steps.echo "testing.......1,2,3"
    archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', fingerprint: true
    def test = junit '**/target/surefire-reports/*.xml'    
    slackSend (
                channel: "#general",
                color: '#007D00',
                message: "\n *Test Summary* - ${test.totalCount}, Failures: ${test.failCount}, Skipped: ${test.skipCount}, Passed: ${test.passCount}"
    )
  }

}
  
  

