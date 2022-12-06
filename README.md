
RulePharser generates an AST for Comby templates that includes both Python and [Comby](https://comby.live) syntaxes. RulePharser is developed on top of [https://github.com/juliandolby/jython3](Jython3)


 

Follow the below steps to install RulePharser to your local maven repository

- RUN git clone https://github.com/maldil/RulePharser.git
- cd RulePharser
- RUN ant
- RUN mvn install:install-file -Dfile=dist/jython-dev.jar -DgroupId=org.python -DartifactId=jython3 -Dversion=0.0.9-SNAPSHOT -Dpackaging=jar
