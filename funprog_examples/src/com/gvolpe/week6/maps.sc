package com.gvolpe.week6

import com.gvolpe.week6._

object maps {
  
  var capitals = Map("Argentina" -> "Buenos Aires", "Uruguay" -> "Montevideo", "Venezuela" -> "Caracas")
                                                  //> capitals  : scala.collection.immutable.Map[String,String] = Map(Argentina ->
                                                  //|  Buenos Aires, Uruguay -> Montevideo, Venezuela -> Caracas)

  capitals("Argentina")                           //> res0: String = Buenos Aires
  
  //capitals("Chile")  ---> Gives a SuchNoElementException
  
  capitals get "Uruguay"                          //> res1: Option[String] = Some(Montevideo)
  capitals get "Chile"                            //> res2: Option[String] = None

	var cap1 = capitals withDefaultValue "Missing capital"
                                                  //> cap1  : scala.collection.immutable.Map[String,String] = Map(Argentina -> Bue
                                                  //| nos Aires, Uruguay -> Montevideo, Venezuela -> Caracas)
	cap1("Paraguay")                          //> res3: String = Missing capital

  def showCapital(country: String) = capitals.get(country) match {
  	case Some(capital) => capital
  	case None => "No se encontró la capital"
  }                                               //> showCapital: (country: String)String

  showCapital("Venezuela")                        //> res4: String = Caracas
  showCapital("Colombia")                         //> res5: String = No se encontró la capital

  // ************* Sorting *******************
  
  var fruits = List("Frutilla", "Banana", "Manzana", "Kiwi", "Mandarina")
                                                  //> fruits  : List[String] = List(Frutilla, Banana, Manzana, Kiwi, Mandarina)

  fruits sortWith (_.length < _.length)           //> res6: List[String] = List(Kiwi, Banana, Manzana, Frutilla, Mandarina)
  fruits.sorted                                   //> res7: List[String] = List(Banana, Frutilla, Kiwi, Mandarina, Manzana)
  
  fruits groupBy (_.head)                         //> res8: scala.collection.immutable.Map[Char,List[String]] = Map(M -> List(Manz
                                                  //| ana, Mandarina), F -> List(Frutilla), K -> List(Kiwi), B -> List(Banana))
                                                  
  // ************ Polynomials ***************
  val p1 = new Polynomials.Poly(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
                                                  //> p1  : com.gvolpe.week6.Polynomials.Poly = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p2 = new Polynomials.Poly(0 -> 3.0, 3 -> 7.0)
                                                  //> p2  : com.gvolpe.week6.Polynomials.Poly = 7.0x^3 + 3.0x^0
  p1 + p2                                         //> res9: com.gvolpe.week6.Polynomials.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^
                                                  //| 0
  p1.terms(7)                                     //> res10: Double = 0.0
}