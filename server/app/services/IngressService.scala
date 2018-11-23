package services

import com.google.inject.ImplementedBy


@ImplementedBy(classOf[IngressServiceS3])
abstract class IngressService {

}
