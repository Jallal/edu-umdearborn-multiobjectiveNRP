
SET in="input/example-grocery/example-grocery.blocks"
SET alg="nsga2"
SET pop=100
SET maxEval=100
SET minRef=2
SET maxRef=10
SET cProb=0.9
SET mProb=0.1

java -jar .\Console.jar -in %in% -alg %alg% -maxEval %maxEval% -cProb %cProb% -mProb %mProb% -maxRef %maxRef% -minRef %minRef% -pop %pop%