package com.gvolpe.week6

object anagrams {
  
   // Test it
  
  Anagrams.wordOccurrences("gabrielmarcelovolpe") //> res0: com.gvolpe.week6.Anagrams.Occurrences = List((a,2), (b,1), (c,1), (e,3
                                                  //| ), (g,1), (i,1), (l,3), (m,1), (o,2), (p,1), (r,2), (v,1))
  
  "gabrielmarcelo".toLowerCase.toList.groupBy(c => c).map({case (char, list) => (char, list.length)}).toList.sorted
                                                  //> res1: List[(Char, Int)] = List((a,2), (b,1), (c,1), (e,2), (g,1), (i,1), (l,
                                                  //| 2), (m,1), (o,1), (r,2))
                                                  
  val oracion = List("tea", "the")                //> oracion  : List[String] = List(tea, the)
  oracion.mkString                                //> res2: String = teathe
  Anagrams.wordOccurrences(oracion.mkString)      //> res3: com.gvolpe.week6.Anagrams.Occurrences = List((a,1), (e,2), (h,1), (t,2
                                                  //| ))
  
  //dictionary.groupBy(w => wordOccurrences(w))
 
  Anagrams.sentenceAnagrams(oracion)              //> res4: List[com.gvolpe.week6.Anagrams.Sentence] = List(List(hate, et), List(h
                                                  //| eat, et), List(Thea, et), List(ate, the), List(eat, the), List(tea, the), Li
                                                  //| st(Tate, he), List(ah, et, et), List(ha, et, et), List(at, he, et), List(at,
                                                  //|  et, he), List(he, Tate), List(he, at, et), List(he, et, at), List(the, ate)
                                                  //| , List(the, eat), List(the, tea), List(et, hate), List(et, heat), List(et, T
                                                  //| hea), List(et, ah, et), List(et, ha, et), List(et, at, he), List(et, he, at)
                                                  //| , List(et, et, ah), List(et, et, ha))
  
}