# nlp-finalProject

##progress
### 11/11
1. data collection(Twitter API)
2. pretreatment of the data deconstructed by using hashTag,NER or POS tag.
3. Remove and filter URL(use regular expression) ,abbreviation(stemming?) , prep....
4. if there are no training data? How to mining the sentiment data and process?
5. small amount of data? How is the accuracy? How about the accuracy with bigger data?
6. feature unigram,bigram,trigram to n-gram accuracy performance.
7. each word's statical counts.
8. improve algorithm, analysis using SVM ,CRF, classifier, naive bayes, our-own-algorithm


* Test how data size , remove and filter word, any kind of application have effects on accuracy.
* UI?

## 11/15
* advance classifier
* improve function
* movie analysis
* web implementation
* deadline: 11/17(二)

## 11/22
* try to implement maximum entropy
* constructive n-gram
* add new feature: linear-lambda ...

##Work_Division
###11/11
1. data collection(Twitter API)
2. tokenization + removing and filter + stemming
3. constructing n-gram algorithm
4. naive bayes algorithm

###11/15
1. Twitter API & SVM algorithm analysis and implementation(丟入input輸出output)(凱哥)
2. tokenization + removing and filter + stemming(文彬)
3. constructing n-gram algorithm + basic program GUI(振安)
4. naive bayes algorithm(丁錦)


* [due day: 11/15(Tuesday)]
* finding papers for better implementation
* next week: Chinese,SVM,CRF,application(use algorithm on website),analysis

###11/17
1. naive bayes + entropy ->(丁錦)
2. 前處理 and feature(stoping word, stemming) + SVM ->(文彬+振安)
3. data mining(twitter... or other)並做資料結構(分類:hashtag,文章內容,文章極性) +
   定義一句話的accurate answer(先用表情判斷一文章的極性) +
   研究lexicon＆polarity 做極性判斷(正中負分細改進？ 分數判斷極性傾向？ 分數判斷依據？)

### 11/22
1. smoothing
2. define polarity using emoji and hashtag.
3. NY times and washington post is objective? define what is objective.
4. 將同一人,具有不同情感的文句,切成兩份不同的情感的文句

## improvement
1. Feature improvement
2. Pre-processing improvement
  1. add more stop words
  2. Strange word changing like transforming “happyyyy” to “happy”
3. Both sentiment words Hashmap and the emoji in Tweet are used to define the tweet
4. Naïve Bayes algorithm improvement
  1. Laplacian Smoothing
  2. Max Entropy improvement
5. Define tweet that have several sentence with different emoji
  * Split an tweet into several sentences and calculate the sentences with emoji(the paper drops tweets with contradict emoji)
  * if positive sentences’ number >negative sentences’ number the tweet is positive
  * the tweet is negative
  (the result may influence the weight of some tweets in corpus)
6. Define the degree of emotion of a tweet like
  * “very happy”
  * “happy”
  * ”neutral”
  * ”unhappy”
  * “very unhappy”
7. A sentence with a lot of emoji, define the sentence based on number of emoji, for   example, a sentence with x “:)” and y “:(”, if x>y, the sentence is positive
8. Define Subject related tweets and compare the result with non-subject tweet’s result




##problem
* how to define the accuracy and accurate answer?

##reference && papers
* https://cs.stanford.edu/people/alecmgo/papers/TwitterDistantSupervision09.pdf
* http://rare-technologies.com/word2vec-tutorial/#app
* http://www.icwsm.org/papers/3--Godbole-Srinivasaiah-Skiena.pdf
* http://oro.open.ac.uk/34929/1/76490497.pdf
* https://www.jair.org/media/4272/live-4272-8102-jair.pdf
* http://hughchristensen.co.uk/papers/socialNetworking/Twitter%20Sentiment%20Analysis.pdf
* http://www.sentimentor.co.uk
* http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/
* http://link.springer.com/chapter/10.1007/978-3-642-28604-9_39
* http://arxiv.org/pdf/1003.5699.pdf
* http://aclweb.org/anthology/S/S13/S13-2052.pdf
* http://nlp.csai.tsinghua.edu.cn/site2/images/file/xlx.pdf
* http://www.aclweb.org/anthology/H/H05/H05-1.pdf#page=383
