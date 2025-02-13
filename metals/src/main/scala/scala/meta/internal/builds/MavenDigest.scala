package scala.meta.internal.builds

import java.security.MessageDigest
import scala.meta.io.AbsolutePath
import scala.meta.internal.mtags.WalkFiles
import scala.meta.internal.mtags.MtagsEnrichments._

object MavenDigest extends Digestable {
  override protected def digestWorkspace(
      workspace: AbsolutePath,
      digest: MessageDigest
  ): Boolean = {
    Digest.digestFile(workspace.resolve("pom.xml"), digest)
    WalkFiles.foreach(workspace) { file =>
      if (file.filename == "pom.xml") {
        Digest.digestFile(file, digest)
      }
    }
    true
  }
}
