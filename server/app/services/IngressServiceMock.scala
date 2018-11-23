package services

import com.google.inject.Inject

import scala.io.Source

class IngressServiceMock @Inject()() extends IngressService {

  def streamFromFile(file: String): Iterator[String] = {
    Source.fromResource(file).getLines()
  }

}
