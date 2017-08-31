# nxtera
IntelliJ based project. See src and test folders.


Below is a Haskell solution  of the dictionary based question.

----------
import Data.Map (empty, Map, insertWith)

wordsOccur :: [String] -> Map String Int
wordsOccur  = foldr f empty  where 
    f x  = insertWith (+) x 1
    
----------

which is even more succinct than the Java stream based solution.
