

import sbt._

class MyProject(info: ProjectInfo) extends DefaultWebProject(info) {
  val scalatoolsRelease = "Scala Tools Snapshot" at
  "http://scala-tools.org/repo-releases/"

 
  override val jettyPort = 9000

  override def libraryDependencies = Set(
    "com.vaadin" % "vaadin" % "6.4.0",
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "test->default",
    "org.scalatest" % "scalatest" %  "1.3"  %"test->default",
    "org.scala-tools.testing" %% "specs" % "1.6.6" % "test->default"
  ) ++ super.libraryDependencies
}
