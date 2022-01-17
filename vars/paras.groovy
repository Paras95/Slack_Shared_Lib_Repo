
import org.foo.ParasSrc

//def path = './pom.xml'

def call(){
    sh"ls -al"
    echo "my first vars function"
}

def beta(){
    echo "from beta"
}

def class1(){
    def path = './pom.xml'
    def obj = new ParasSrc(this, this)
    //obj.env()
    obj.parallelize(6)
    obj.readXml(path)
}
