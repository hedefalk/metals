package scala.meta.internal.pc

import scala.reflect.internal.Reporter
import scala.tools.nsc.reporters.StoreReporter
import scala.tools.nsc.reporters.Reporter.AdaptedReporter

trait Compat { this: MetalsGlobal =>
  def metalsFunctionArgTypes(tpe: Type): List[Type] =
    definitions.functionOrPfOrSamArgTypes(tpe)

  val AssignOrNamedArg: NamedArgExtractor = NamedArg

  def storeReporter(r: Reporter): Option[StoreReporter] = r match {
    case s: StoreReporter => Some(s)
    case r: AdaptedReporter =>
      r.delegate match {
        case s: StoreReporter => Some(s)
        case _ => None
      }
    case _ => None
  }
}
