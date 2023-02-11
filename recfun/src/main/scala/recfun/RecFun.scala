package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    // println("Pascal's Triangle")
    // for row <- 0 to 10 do
    //   for col <- 0 to row do
    //     print(s"${pascal(col, row)} ")
    //   println()
    println(balance("()())".toList))
    println(balance("()()".toList))

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if c == 0 || c == r then 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean =
    var count = 0;
    def balanceHelper(chars: List[Char]): Boolean =
      if count < 0 then return false
      else if chars.isEmpty then return count == 0
      else if chars.head == '(' then count += 1
      else if chars.head == ')' then count -= 1
      balanceHelper(chars.tail)
    balanceHelper(chars)

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if money == 0 then 1
    else if coins.isEmpty || money < 0 then 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
