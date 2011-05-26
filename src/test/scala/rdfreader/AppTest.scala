

package rdfreader {

import java.io.File
import scala.xml.XML
import org.scalatest._


/**
 * Unit test for simple App.
 */
class TestApp extends Suite {

  /**
   * Rigourous Tests :-)
   */
  def testOK() = assert(true)
  // def testKO() = assertTrue(false);

  /**
   * Tests to make sure the project's XML files are well-formed.
   *
   * Finds every *.html and *.xml file in src/main/webapp (and its
   * subdirectories) and tests to make sure they are well-formed.
   */
  def testXml() = {
    var failed: List[File] = Nil

    def handledXml(file: String) =
      file.endsWith(".xml")

    def handledXHtml(file: String) =
      file.endsWith(".html") || file.endsWith(".htm") || file.endsWith(".xhtml")

    def wellFormed(file: File) {
      if (file.isDirectory)
        for (f <- file.listFiles) wellFormed(f)

      if (file.isFile && handledXml(file.getName)) {
        try {
          XML.loadFile(file)
        } catch {
          case e: _root_.org.xml.sax.SAXParseException => failed = file :: failed
        }
      }
      if (file.isFile && handledXHtml(file.getName)) {
        /* // Not Handled Yet
        PCDataXmlParser(new _root_.java.io.FileInputStream(file.getAbsolutePath)) match {
          case Full(_) => // file is ok
          case _ => failed = file :: failed
         
        } */
      }
    }

    wellFormed(new File("src/main/webapp"))

    val numFails = failed.size
    if (numFails > 0) {
      val fileStr = if (numFails == 1) "file" else "files"
      val msg = "Malformed XML in " + numFails + " " + fileStr + ": " + failed.mkString(", ")
      println(msg)
      fail(msg)
    }
  }
}
}