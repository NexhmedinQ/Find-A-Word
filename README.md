# Find-A-Word
Few problems: 
- I now regret making a lot of stuff static for no reason (like the entire MatrixGenerator class). I should
have just had a constructor with a single public method as an entry point to build up the matrix.

- I'm not happy with the random generation of the matrix especially in the really unlucky case where 
it can't be computed because every randomly generated coordinate falls on a coordinate already in use by another word.
This is minimised by how big I made the matrix in comparison to the space used by the words (and how many retries I have)
upon failing to build it but it is still a possiblility. One thing I could do to make it even more unlikely is add another outer loop that will randomly pick a direction a few times if we keep failing but the problem persists it's just 
being made less likely. 

- Was my choice of storage in a frequency hashmap for the words the best? I thought about having a trie but it feels
like pointless complexity for no gain given we don't use the most powerful feature of a trie which is prefix matching.

- (This final point isn't a big deal I'm just a leetcode nerd so I like when stuff goes fast). I thought about using some extra space to store the words in a list as well as a hashmap but that's pointless because after searching for each word it would need to be removed from the list anyway which is worst case O(n), the same as just converting the hashmap into a list each time. An argument can be made that some searched by the user will be unsuccessful and in that case we wouldn't need to update the list so the hashmap conversion to list overall has a higher time complexity. 