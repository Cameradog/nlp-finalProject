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
