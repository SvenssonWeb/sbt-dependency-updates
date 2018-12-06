package org.jmotor.sbt.util

import sbt.ModuleID

object PluginParser {
  private[this] val addSbtPluginRegex = """addSbtPlugin\("([\w\.-]+)" *%{1,2} *"([\w\.-]+)"\ *% *"([\w\.-]+)"\)""".r

  def parseline(lines: Seq[String]): Seq[ModuleID] = {
    lines.map(_.trim).filter { line ⇒
      line.nonEmpty && line.startsWith("addSbtPlugin")
    }.flatMap {
      case addSbtPluginRegex(org, n, v) ⇒ Some(ModuleID(org, n, v))
      case _                            ⇒ None
    }
  }
}
