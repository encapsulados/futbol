/**
 * Partidos de fútbol
 *
 * Queremos saber si un partido se juega o no. Para que un partido se juegue
 * tiene que haberse anotado la cantidad de jugadores requeridos. Siempre hay
 * alguien que se baja a último momento...
 *
 * Queremos conocer en cualquier momento la lista de anotados. Los que se anoten
 * primero tienen prioridad.
 *
 * Uno puede anotarse en virtud de reserva, es decir que jugará solo si no se
 * anota alguien más para completar.
 */
object modelo:
  
  case class Jugador(nombre: String)

  case class Organizador(
    private val jugadoresRequeridos: Int,
    private val titulares: List[Jugador] = List.empty,
    private val reserva: List[Jugador] = List.empty,
  ):
    def anotarTitular(jugador: Jugador): Organizador = this.copy(titulares = jugador :: titulares)
    def anotarReserva(jugador: Jugador): Organizador = this.copy(reserva = jugador :: reserva)
    def seJuega: Boolean                             = (titulares.size + reserva.size) >= jugadoresRequeridos
    def listaDeAnotados: List[Jugador]               = titulares.reverse.concat(reserva.reverse)
    def darDeBaja(jugador: Jugador): Organizador     =
      this.copy(
        titulares = titulares.filterNot(_ == jugador),
        reserva = reserva.filterNot(_ == jugador),
      )

  object Organizador:
    def nuevoPartido(jugadoresRequeridos: Int): Organizador = Organizador(jugadoresRequeridos)
