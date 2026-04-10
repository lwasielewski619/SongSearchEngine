Song Search Engine

This program was made to be used as a search egine that lets people search for words and get results based on song title and lyrics.

It reads 100k lines of a TSV file song_lyrics because anything bigger would not load in eclipse. 

It uses TF-IDF to rank the results of a search.

TF = how often words appear

IDF = Is looking for the important searched words

Each song gets a score based on their TF-IDF results, but gets a boosted score if the searched word is in the title

Users can search for words as long as they like until they type EXIT and then will also print everything the user has searched. 

Precision@5 was used to dtermine the accurarcy and how relevant the results were.
