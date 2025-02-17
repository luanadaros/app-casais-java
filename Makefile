JAVAC = javac
MAIN = Main

all:
	$(JAVAC) *.java

clean:
	find . -type f -name "*.class" -delete
