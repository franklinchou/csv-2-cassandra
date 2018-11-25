package util

import play.api.Logger

object ElapsedTime {

  /**
    * Wrap a function, f, to determine the elapsed time
    *
    * @param f
    * @param message
    * @param division
    * @tparam R
    * @return
    */
  def time[R](f: => R, message: String, division: Double = 1e9): R = {
    val start = System.nanoTime()
    val result = f
    val stop = System.nanoTime()
    val elapsed = (stop - start) / division
    Logger.info(s"$message in ${elapsed}s") // TODO Change unit
    result
  }


  /**
    * Perform a time trial on the given function, f, returning
    * None if the function fails to return within the time limit
    * specified.
    *
    * @param f
    * @param upper Upper performance bound in nanoseconds
    * @tparam R
    * @return
    */
  def timeTrial[R](f: => R, upper: Double): Option[R] = {
    val start = System.nanoTime()
    val result = f
    val stop = System.nanoTime()

    if ((stop - start) > upper) {
      None
    } else {
      Some(result)
    }
  }

}
