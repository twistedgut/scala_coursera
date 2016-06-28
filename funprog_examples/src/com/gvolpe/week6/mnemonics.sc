package com.gvolpe.week6

object mnemonics {

  type ??? = Nothing

  val words = List("Java", "Scala", "Python", "Haskell", "Ruby", "Lisp", "Lava")
                                                  //> words  : List[String] = List(Java, Scala, Python, Haskell, Ruby, Lisp, Lava)
                                                  //| 

  val nem = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
  					'6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")
                                                  //> nem  : scala.collection.immutable.Map[Char,String] = Map(8 -> TUV, 4 -> GHI,
                                                  //|  9 -> WXYZ, 5 -> JKL, 6 -> MNO, 2 -> ABC, 7 -> PQRS, 3 -> DEF)

  val charCode: Map[Char, Char] =
		for ((digit, str) <- nem; ltr <- str) yield ltr -> digit
                                                  //> charCode  : Map[Char,Char] = Map(E -> 3, X -> 9, N -> 6, T -> 8, Y -> 9, J -
                                                  //| > 5, U -> 8, F -> 3, A -> 2, M -> 6, I -> 4, G -> 4, V -> 8, Q -> 7, L -> 5,
                                                  //|  B -> 2, P -> 7, C -> 2, H -> 4, W -> 9, K -> 5, R -> 7, O -> 6, D -> 3, Z -
                                                  //| > 9, S -> 7)

	def wordCode(word: String): String = word.toUpperCase map charCode
                                                  //> wordCode: (word: String)String

  wordCode("Java")                                //> res0: String = 5282
  
  val wordsForNum: Map[String, Seq[String]] = words groupBy wordCode withDefaultValue Seq()
                                                  //> wordsForNum  : Map[String,Seq[String]] = Map(7829 -> List(Ruby), 798466 -> L
                                                  //| ist(Python), 5477 -> List(Lisp), 5282 -> List(Java, Lava), 72252 -> List(Sca
                                                  //| la), 4275355 -> List(Haskell))
	
	def encode(number: String): Set[List[String]] = {
		if (number.isEmpty) Set(List())
		else {
			for {
				split <- 1 to number.length
				word <- wordsForNum(number take split)
				rest <- encode(number drop split)
			} yield word :: rest
		}.toSet
  }                                               //> encode: (number: String)Set[List[String]]
 
  encode("5282")                                  //> res1: Set[List[String]] = Set(List(Java), List(Lava))
  
  def translate(number: String): Set[String] =
  	encode(number) map (_ mkString " ")       //> translate: (number: String)Set[String]
  	
  translate("52827829")                           //> res2: Set[String] = Set(Java Ruby, Lava Ruby)
}