# Decision-Tree
An implementation of decision tree learning algorithm
The program generates the root node of a decision tree in a recursive manner given training data. Entropy and information gain are used to determine the attribute to be used in each layer, and a leaf is produced when either the entropy is zero (which means the classification result is fixed) or no more attribute can be used, in which case we take the majority to be the classification result.
