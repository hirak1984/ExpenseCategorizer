# ExpenseCategorizer
Expense categorizer for my budget tracking.
If you want to use it for your needs, then here what you need to do - 

1. Review and modify (if required) the Input.java to suite the format of your statements.
2. Update the metadata.csv with your own category/sub-category and add the keywords.
3. Run the MyMain.java , give the directory/file paths.

Metadata.csv format - 
Category,SubCategory, keywordContainsIgnoreCase....

So all values starting column 3 will be considered as keyword.
There are some markups possible for keywords - 
* plain text -> will be checked against the transaction description.
* $(amt) -> will be checked against the transaction amount (abs). Use this is you know what a recurring item costs no matter where you buy from.
