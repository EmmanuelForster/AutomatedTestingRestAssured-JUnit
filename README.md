# Name: Testing with RestAssured and JUnit

# Description:

# For this test it will be used the page https://parabank.parasoft.com/parabank/index.htm
# and there will be tested its methods for registering a new user, login, creating a new account,
# getting an overview of all the accounts, and transfering between accounts.

# How to run the tests:
# To run the tests right click on  the java folder at the directory "..src/test/java", then click on
# the "Run" option and next select "All Tests".
# Also by clicking on the play button at the top of the TestFront.class or at the TestBack.class.
# Finally, try generating a new Suite at the run/debug configurations editor and adding a new configuration for JUnit with the tag "Regression". Then run it.

# Observations:
# In case the test for the register method fails please check if the user was already created.
# For instance, if you run all the tests and the register for the TestFront.class fails it may be due to the user was already created at the TestBack.class.
