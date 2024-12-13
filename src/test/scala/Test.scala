import modelo.*
import org.scalatest.funsuite.*
import org.scalatest.matchers.should.Matchers

class Test extends AnyFunSuiteLike with Matchers {

  test("se juega o no se juega?") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("zeta"))
      .seJuega shouldBe false
  }

  test("partido con dos jugadores que se juega") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("zeta"))
      .anotarTitular(Jugador("palan"))
      .seJuega shouldBe true
  }

  test("partido en donde se anote mas gente de la que se necesita") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("zeta"))
      .anotarTitular(Jugador("palan"))
      .anotarTitular(Jugador("ramona"))
      .listaDeAnotados shouldBe List(
      Jugador("zeta"),
      Jugador("palan"),
      Jugador("ramona"),
    )
  }

  test("partido en donde se anote mas gente de la que se necesita se juega") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("zeta"))
      .anotarTitular(Jugador("palan"))
      .anotarTitular(Jugador("ramona"))
      .seJuega shouldBe true
  }

  test("partido en donde se anote un titular y un jugador de reserva se juega") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("zeta"))
      .anotarReserva(Jugador("ramona"))
      .seJuega shouldBe true
  }

  test("partido en donde se anote un titular y un jugador de reserva") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("zeta"))
      .anotarReserva(Jugador("ramona"))
      .listaDeAnotados shouldBe List(
      Jugador("zeta"),
      Jugador("ramona"),
    )
  }

  test("partido con dos jugadores y un titular se da de baja") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("zeta"))
      .anotarTitular(Jugador("palan"))
      .darDeBaja(Jugador("palan"))
      .seJuega shouldBe false
  }

  test("partido con dos jugadores y uno se da de baja") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("zeta"))
      .anotarReserva(Jugador("ramona"))
      .darDeBaja(Jugador("ramona"))
      .seJuega shouldBe false
  }

  test("se anotan dos personas con el mismo nombre") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("pedro"))
      .anotarTitular(Jugador("pedro"))
      .seJuega shouldBe true
  }

  test("se anotan dos personas con el mismo nombre y uno se da de baja") {
    Organizador
      .nuevoPartido(2)
      .anotarTitular(Jugador("pedro"))
      .anotarTitular(Jugador("pedro"))
      .darDeBaja(Jugador("pedro"))
      .listaDeAnotados shouldBe List(
      Jugador("pedro"),
    )
  }

}
