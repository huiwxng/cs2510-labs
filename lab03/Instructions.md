1. Read through `WaltzGenerator.java` and look at the `csv` files.
2. Uncomment and define the constants at the beginning of `WaltzGenerator.java`.
3. Fill in the bodies of the two constructors, following the guidance in the
   javadoc and comments.
4. Use IntelliJ's `Refactor > Introduce Constant...` functionality to extract
   the string literals from `getWaltz()` and `saveWaltz()`, giving them good
   names. (I show how to do this near the end of the setup video.)
5. Write tests for the method `rollDice()` and run them _before_ implementing
   `rollDice()`. Your tests should just ensure that the numbers returned are in
   the appropriate range, not that the die is fair (as likely to turn up one
   side as another).
6. Once your tests fail, implement `rollDice()`. You must use `Random` to
   generate random numbers, so the same seed always generates the same
   waltz.
7. Implement `buildTable()`, following the inlined instructions, which will
   involve creating two helper methods to:
    * break the work into smaller steps
    * make it easier to test

   The two helper methods should be package-private and tagged with
   `@VisibleForTesting`.

   It's up to you whether to write the code first and then split it into the
   helper methods or to create the helper methods as you go along.
8. Write tests for the two helper methods and keep iterating (revising
   your tests and your code) until you have confidence in the tests and code.
9. Implement `makeMinuet()` and `makeTrio()`.
10. Run the program. If it works, submit your code (`WaltzGenerator.java` and
    `WaltzGeneratorTest.java`) to Gradescope
    * so you can get autograder feedback
    * so you can retrieve this code if you later break something
11. Right now there is duplicated code in `makeMinuet()` and `makeTrio()`.
    Create a private helper method (with multiple parameters) that both of
    them will call to eliminate the duplicated code.
12. Go through and clean up your code:
    * Run the auto-formatter on both Java files.
    * Run checkstyle on both Java files.
    * Make final any variables that can be in `WaltzGenerator.java`.
    * Make sure the parts of `WaltzGenerator` are properly ordered:
        * static variables
        * instance variables
        * constructors
        * methods
13. Complete `Summary.md` and submit that along with your two source files.
