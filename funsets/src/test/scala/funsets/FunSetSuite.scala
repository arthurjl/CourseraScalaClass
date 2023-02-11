package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val largerThan5: FunSet = elem => elem > 5
    val largerThanEqual5: FunSet = elem => elem >= 5

  /**
   * This test is currently disabled (by using .ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */

    test("singleton set one contains one") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("intersect") {
    new TestSets:
      val intersect5 = intersect(largerThan5, largerThanEqual5)
      assert(!contains(intersect5, 5), "Does not contain 5")
      assert(contains(intersect5, 6), "Does contain 6")
  }

  test("diff") {
    new TestSets:
      val diff1 = diff(largerThan5, largerThanEqual5)
      assert(!contains(diff1, 5), "Does not contain 5")

      val diff2 = diff(largerThanEqual5, largerThan5)
      assert(contains(diff2, 5), "Does contain 5")
  }

  test("filter") {
    new TestSets:
      val filter5 = filter(largerThan5, (elem) => elem > 7)
      assert(!contains(filter5, 5), "Does not contain 5")
      assert(contains(filter5, 8), "Does contain 8")
  }
  
  test("forAll") {
    new TestSets:
      assert(forall(largerThan5, (elem) => elem > 0), "All elements larger than 0")
      assert(!forall(largerThan5, (elem) => elem != 11), "All elements are not 11")
  }

  test("exists") {
    new TestSets:
      assert(exists(largerThan5, (elem) => elem == 10), "Exists element 10")
      assert(!exists(largerThan5, (elem) => elem == 1), "Exists element 1")
  }

  test("map") {
    new TestSets:
      val mapped = map(largerThan5, elem => elem + 5)
      assert(!contains(mapped, 5), "Does not contain 5")
      assert(!contains(mapped, 7), "Does not contain 7")
      assert(!contains(mapped, 10), "Does not contain 10")
      assert(contains(mapped, 11), "Contains 11")
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
