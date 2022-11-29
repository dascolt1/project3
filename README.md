Project 3 for CS 520

This project is a login system written in C utilizing a hashtable for user lookup

It was created by Tommy D'Ascoli and Prashant Patil 2022

"An input file named ‘LoginsAndPasswords.txt' contains valid user ids and their corresponding passwords. Use a hashmap (Java) or hashtable (C) to store this information. Create a prompt for the user to enter their login credentials."

Plan here:

1. Need function to read in name from text file (acting as db, username will be key)
   - getline() function + https://stackoverflow.com/questions/3501338/c-read-file-line-by-line
   - Need to split line by , + strtok() function split at ", " https://www.tutorialspoint.com/c_standard_library/c_function_strtok.htm
2. Need function to write to file with timestamp, IP and username
   - If successful print SUCCESS: <username, IP, timestamp>
   - Unsuccessful print FAILED: <username, IP, timestamp>
3. Have to write hashtable from scratch
   - https://www.tutorialspoint.com/data_structures_algorithms/hash_table_program_in_c.htm
4. Checks
   - Make sure valid input + if 3 times wrong, display "Try again in 1 hour"
