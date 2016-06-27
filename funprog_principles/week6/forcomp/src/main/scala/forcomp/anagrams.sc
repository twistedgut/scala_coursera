import forcomp.Anagrams._

type Word = String
type Sentence = List[Word]
type Occurrences = List[(Char, Int)]

//  val dictionary: List[Word] = loadDictionary
//
//  def wordOccurrences(w: Word): Occurrences =
//    (w.toLowerCase groupBy identity mapValues(_.length)).toList.sortWith(_._1 < _._1)
//
//  def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences(s.flatten.mkString)
//
//  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = dictionary groupBy(wordOccurrences(_))
//
//  def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences(wordOccurrences(word))

  def combinations(occurrences: Occurrences): List[Occurrences] = occurrences
    .map(o => (for (x <- 1 until (o._2 + 1)) yield (o._1, x)) toList)
    .foldRight(List[Occurrences](Nil))((l,r) => r ++ (for(x <- l; y <- r) yield x :: y))

  def subtract(x: Occurrences, y: Occurrences): Occurrences =
    x.map( c => (c._1, c._2 - y.find(_._1 == c._1).getOrElse(c._1,0)._2 ))
     .filterNot(_._2 == 0)


def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
  def loop(occ: Occurrences): List[Sentence] = {
    if (occ.isEmpty) List(Nil)
    else {
      for {
        c <- combinations(occ) if dictionaryByOccurrences.keySet(c)
        d <- dictionaryByOccurrences(c)
        e <- loop(subtract(occ, c))
      } yield d :: e
    }
  }
  loop(sentenceOccurrences(sentence))
}

//def sentenceAnagramsInner(o: Occurrences): List[Sentence] = {
//  if (o.isEmpty) {
//    List(Nil)
//  } else {
//    val combs = combinations(o)
//    for (i <- combs if dictionaryByOccurrences.keySet(i);
//         j <- dictionaryByOccurrences(i);
//         s <- sentenceAnagramsInner(subtract(o, i))) yield {j :: s}
//  }
//}






  //def sentenceAnagrams(sentence: Sentence): List[Sentence] = ???

  combinations(Nil)
  combinations(List(('a', 2), ('b', 2)))
val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
val r = List(('r', 1))
val lad = List(('a', 1), ('d', 1), ('l', 1))
subtract(lard, r)
