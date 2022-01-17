
import org.foo.ParasSrc

def path = 'target/surefire-reports/TEST-com.mycompany.app.AppTest.xml.xml'

def call(){
    sh"ls -al"
    echo "my first vars function"
}

def beta(){
    echo "from beta"
}

def class1(){
    def obj = new ParasSrc(this, this)
    //obj.env()
    obj.parallelize(6)
    obj.readXml(path)
}
