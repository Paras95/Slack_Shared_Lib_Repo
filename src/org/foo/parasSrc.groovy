
package org.foo
class parasSrc implements Serializable {
  def script
  def steps
  parasSrc(steps, script) {
    this.steps = steps
    this.script = script
  }
  def mvn(args) {
    steps.sh "${steps.tool 'maven'}/bin/mvn -o ${args}"
  }
  
  def env(){
    steps.echo "build number----------${script.env.BUILD_NUMBER}"
  }
  
}
