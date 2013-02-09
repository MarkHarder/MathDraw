APPNAME = mathdraw
CLASSNAME = MathDraw
JAVAC = javac
sources = $(wildcard src/main/markharder/$(APPNAME)/*.java)
classes = $(sources:.java=.class)

default: jar

jar: all
	jar cfm $(CLASSNAME).jar MANIFEST.MF -C bin .; java -jar $(CLASSNAME).jar

all: $(classes)

clean:
	$(RM) src/main/markharder/$(APPNAME)/*.class

%.class: %.java
	$(JAVAC) -d bin/ -cp src/main $<
