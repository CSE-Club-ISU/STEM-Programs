SRC_DIR = src/main/java
BUILD_DIR = build/classes
MAIN_CLASS = StartMenu.Frame

all: compile

compile:
	mkdir -p $(BUILD_DIR)
	find $(SRC_DIR) -name "*.java" > sources.txt
	javac -d $(BUILD_DIR) @sources.txt
	rm sources.txt

run: compile
	java -cp $(BUILD_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(BUILD_DIR)

.PHONY: all compile run clean
