package com.etermax

object sheet1 {
  
	val a = new Main()                        //> a  : com.etermax.Main = com.etermax.Main@1f8bd6f9
	a.balance(":-(".toList)                   //> res0: Boolean = false
	a.balance("(soy (gabriel))".toList)       //> res1: Boolean = true

}