  
#!/bin/bash

echo "Compiling .java files..."
echo
javac ./*.java

tests=($(ls Test*.java))
for test in ${tests[@]}; do
  echo;echo
  echo "=========="
  echo;echo
  echo "Running $test..."
  echo
  eval "java ${test%.java}"
done