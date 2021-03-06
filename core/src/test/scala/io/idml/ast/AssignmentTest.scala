package io.idml.ast

import io.idml.datanodes._
import io.idml.{NoFields, PtolemyContext, PtolemyListener, PtolemyValue}
import org.mockito.Mockito._
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar

class AssignmentTest extends FunSuite with MockitoSugar {

  def updateCursor(value: PtolemyValue): Answer[Unit] = new Answer[Unit] {
    override def answer(invocation: InvocationOnMock) = {
      invocation.getArguments()(0).asInstanceOf[PtolemyContext].cursor = value
    }
  }

  test("Invokes right hand side expression") {
    val pipl   = mock[Pipeline]
    val assign = new Assignment(List("a"), pipl)
    val ctx    = new PtolemyContext()
    assign.invoke(ctx)
    verify(pipl).invoke(ctx)
  }

  test("Updates the output if the pipl updates the cursor to a value") {
    val pipl   = mock[Pipeline]
    val assign = new Assignment(List("a"), pipl)
    val ctx    = new PtolemyContext()
    when(pipl.invoke(ctx)).thenAnswer(updateCursor(PTrue))
    assign.invoke(ctx)
    assert(ctx.output === PObject("a" -> PTrue))
  }

  test("Need to move assignments into a new class and have a test for nested updates") {
    pending
  }

  test("Need to move assignments into a new class and have a test for overwriting old values") {
    pending
  }

  test("Doesn't update the output if nothing is returned") {
    val pipl   = mock[Pipeline]
    val assign = new Assignment(List("a"), pipl)
    val ctx    = new PtolemyContext()
    when(pipl.invoke(ctx)).thenAnswer(updateCursor(NoFields))
    assign.invoke(ctx)
    assert(ctx.output === PObject())
  }

  test("Triggers enter and exit events") {
    val pipl     = mock[Pipeline]
    val assign   = new Assignment(List("a"), pipl)
    val ctx      = new PtolemyContext()
    val listener = mock[PtolemyListener]
    ctx.listeners = List(listener)
    assign.invoke(ctx)
    verify(listener).enterAssignment(ctx, assign)
    verify(listener).exitAssignment(ctx, assign)
  }
}
