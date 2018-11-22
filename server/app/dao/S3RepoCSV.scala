package dao

import com.google.inject.ImplementedBy

@ImplementedBy(classOf[S3RepoCSV])
class S3RepoCSV extends S3Repo {

}
