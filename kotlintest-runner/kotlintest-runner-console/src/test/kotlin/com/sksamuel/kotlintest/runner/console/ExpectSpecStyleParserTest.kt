package com.sksamuel.kotlintest.runner.console

import io.kotlintest.Description
import io.kotlintest.runner.console.BehaviorSpecStyleParser
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class ExpectSpecStyleParserTest : FunSpec() {

  init {

    test("should parse given") {
      BehaviorSpecStyleParser.parse(Description.spec("myspec"), "Given: foo") shouldBe
          Description.spec("myspec").append("Given: foo")

      BehaviorSpecStyleParser.parse(Description.spec("myspec"), "Given:    foo!") shouldBe
          Description.spec("myspec").append("Given:    foo!")
    }

    test("should parse given when") {
      BehaviorSpecStyleParser.parse(Description.spec("myspec"),
          "Given: foo When: bar") shouldBe
          Description.spec("myspec").append("Given: foo").append("When: bar")

      BehaviorSpecStyleParser.parse(Description.spec("myspec"),
          "Given: foo     When: bar!!!") shouldBe
          Description.spec("myspec").append("Given: foo    ").append("When: bar!!!")
    }

    test("should parse given when then") {
      BehaviorSpecStyleParser.parse(Description.spec("myspec"),
          "Given: foo When: bar   Then: waz") shouldBe
          Description.spec("myspec").append("Given: foo").append("When: bar  ").append("Then: waz")

      BehaviorSpecStyleParser.parse(Description.spec("myspec"),
          "Given: foo234 When: bar-- Then: waz$$  ") shouldBe
          Description.spec("myspec").append("Given: foo234").append("When: bar--").append("Then: waz$$  ")
    }
  }
}