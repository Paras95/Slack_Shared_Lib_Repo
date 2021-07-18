
import src.org.foo.parasSrc

def call(){
    sh"ls -al"
    echo "my first vars function"
}

def beta(){
    echo "from beta"
}

def class1(){
    def obj = new parasSrc(this, this)
    obj.env()
}
