import java.util.Scanner;

public class buscaminas {

  public final int iTAMANO = 14;
  public final int iMOSTRAR = 0;
  public final int iMOSTRAR2 = 1;

  // Funcion para genedar partida.
  // ------------------------------------------------------------------------------------------------------------------
  public int[] fnGenedarTablero(char[][] cTablero, int[][] iTablero) {
    int iContador;

    for (int iPosicionX = 0; iPosicionX < iTablero.length; iPosicionX++) {
      for (int iPosicionY = 0; iPosicionY < iTablero.length; iPosicionY++) {
        iTablero[iPosicionX][iPosicionY] = 0;
      }
    }

    int iMinas = 0, iNoMinas = 0;

    // geneda las minas aleatoriamene.
    for (int iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
      for (int iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
        if ((int) ((Math.random() * iTAMANO) + 1) == iTAMANO / 2
        // | (int) ((Math.random() * iTAMANO) + 1) == iTAMANO
        // | (int) ((Math.random() * iTAMANO) + 1) == 1
        ) {
          cTablero[iPosicionX][iPosicionY] = 'X';
          iMinas++;
        } else {
          cTablero[iPosicionX][iPosicionY] = '0';
          iNoMinas++;
        }
      }
    }

    // cuentca el alrededor de las casilla para saver si ay mina y añadir la
    // cantodad de minas que hay en el alrededor.
    for (int iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
      for (int iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
        iContador = 0;

        if (cTablero[iPosicionX][iPosicionY] != 'X') {

          try {
            if (cTablero[iPosicionX - 1][iPosicionY - 1] == 'X') {
              iContador++;
            }
          } catch (Exception e) {
          }

          try {
            if (cTablero[iPosicionX - 1][iPosicionY] == 'X') {
              iContador++;
            }
          } catch (Exception e) {
          }

          try {
            if (cTablero[iPosicionX - 1][iPosicionY + 1] == 'X') {
              iContador++;
            }
          } catch (Exception e) {
          }

          try {
            if (cTablero[iPosicionX][iPosicionY - 1] == 'X') {
              iContador++;
            }
          } catch (Exception e) {
          }

          try {
            if (cTablero[iPosicionX][iPosicionY + 1] == 'X') {
              iContador++;
            }
          } catch (Exception e) {
          }

          try {
            if (cTablero[iPosicionX + 1][iPosicionY - 1] == 'X') {
              iContador++;
            }
          } catch (Exception e) {
          }

          try {
            if (cTablero[iPosicionX + 1][iPosicionY] == 'X') {
              iContador++;
            }
          } catch (Exception e) {
          }

          try {
            if (cTablero[iPosicionX + 1][iPosicionY + 1] == 'X') {
              iContador++;
            }
          } catch (Exception e) {
          }

          cTablero[iPosicionX][iPosicionY] = (char) (iContador + '0');
        }
      }
    }

    return new int[] { iMinas, iNoMinas }; // devuelve la cantidad de minas y los espacuios vacios.
  }

  // Funcion para marcarcasillas como minas.
  // -------------------------------------------------------------------------------------------------------------
  public boolean fnMinaSeleccionada(char[][] cTablero, int[][] iTablero, String sCordenadas) {
    int iPosicionX; // = 0; // = sc.nextInt();
    int iPosicionY; // = 0; // = sc.nextInt();

    // comprueba que que el primer cadacter introducido sea un "!" para indicar que
    // quierse marcaro desmarcar la casilla como mina.
    if (String.valueOf(sCordenadas.charAt(0)).equals("!")) {
      iPosicionX = Character.getNumericValue(sCordenadas.charAt(1)) - 10;
      iPosicionY = Integer.parseInt(sCordenadas.substring(2)) - 1;
      if (iTablero[iPosicionX][iPosicionY] != 1) {
        if (iTablero[iPosicionX][iPosicionY] == 2) {
          iTablero[iPosicionX][iPosicionY] = 0;
        } else {
          iTablero[iPosicionX][iPosicionY] = 2;
        }
      } else {
        return false; // si la funcion se ejcuta correctamente devolvera un true y si falla por el
                      // motivo que se devolvera un false.
      }
      return true;
    } else {
      return false;
    }
  }

  // funcion apara marcar las posico con "?"
  // --------------------------------------------------------------------------------------------------------
  public boolean fnPosibleMina(char[][] cTablero, int[][] iTablero, String sCordenadas) {
    try {
      int iPosicionX; // = 0; // = sc.nextInt();
      int iPosicionY; // = 0; // = sc.nextInt();

      // comprueba que el primer caracter sea "?"
      if ((String.valueOf(sCordenadas.charAt(0)).equals("?"))) {
        iPosicionX = Character.getNumericValue(sCordenadas.charAt(1)) - 10;
        iPosicionY = Integer.parseInt(sCordenadas.substring(2)) - 1;
        if (iTablero[iPosicionX][iPosicionY] != 1) {
          if (iTablero[iPosicionX][iPosicionY] == 3) {
            iTablero[iPosicionX][iPosicionY] = 0;
          } else {
            iTablero[iPosicionX][iPosicionY] = 3;
          }
        } else {
          return false; // si la funcion se ejcuta correctamente devolvera un true y si falla por el
                        // motivo que se devolvera un false.
        }
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  // Funcion para desvelar las casillas
  // ------------------------------------------------------------------------------------------
  public boolean fnDesbloquearCasilla(
      char[][] cTablero, int[][] iTablero, String sCordenadas) {
    try {
      int iPosicionX; // = 0; // = sc.nextInt();
      int iPosicionY; // = 0; // = sc.nextInt();

      iPosicionX = Character.getNumericValue(sCordenadas.charAt(0)) - 10;
      iPosicionY = Integer.parseInt(sCordenadas.substring(1)) - 1;

      // comprueba que la casilla se pueda de svelar
      if (iTablero[iPosicionX][iPosicionY] == 0 | iTablero[iPosicionX][iPosicionY] == 3) {
        iTablero[iPosicionX][iPosicionY] = 1;

        // Comprueba que la casilla desvelada este vacia.
        if (cTablero[iPosicionX][iPosicionY] == '0') {
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY);
        }
        return true; // si la funcion se ejcuta correctamente devolvera un true y si falla por el
                     // motivo que se devolvera un false.
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  // Funcion para develar casillas multiples en funcion de la s minjas detectadas.
  // --------------------------------------------------------------------------------------
  public boolean fnDesblquearCasillasMultiple(
      char[][] cTablero, int[][] iTablero, String sCordenadas) {

    int iPosicionX; // = 0; // = sc.nextInt();
    int iPosicionY; // = 0; // = sc.nextInt();

    iPosicionX = Character.getNumericValue(sCordenadas.charAt(0)) - 10;
    iPosicionY = Integer.parseInt(sCordenadas.substring(1)) - 1;

    // System.out.println("cossss");

    if (iTablero[iPosicionX][iPosicionY] != 1) {
      return false;
    }

    int iContadorMinas = 0;
    try {
      if (iTablero[iPosicionX - 1][iPosicionY - 1] == 2) {
        iContadorMinas++;
      }
    } catch (Exception e) {
    }

    try {
      if (iTablero[iPosicionX - 1][iPosicionY] == 2) {
        iContadorMinas++;
      }
    } catch (Exception e) {
    }

    try {
      if (iTablero[iPosicionX - 1][iPosicionY + 1] == 2) {
        iContadorMinas++;
      }
    } catch (Exception e) {

    }

    try {
      if (iTablero[iPosicionX][iPosicionY + 1] == 2) {
        iContadorMinas++;
      }
    } catch (Exception e) {
    }

    try {
      if (iTablero[iPosicionX][iPosicionY - 1] == 2) {
        iContadorMinas++;
      }
    } catch (Exception e) {
    }

    try {
      if (iTablero[iPosicionX + 1][iPosicionY + 1] == 2) {
        iContadorMinas++;
      }
    } catch (Exception e) {
    }

    try {
      if (iTablero[iPosicionX + 1][iPosicionY] == 2) {
        iContadorMinas++;
      }
    } catch (Exception e) {
    }

    try {
      if (iTablero[iPosicionX + 1][iPosicionY - 1] == 2) {
        iContadorMinas++;
      }
    } catch (Exception e) {
    }

    int iMinasDetectada = cTablero[iPosicionX][iPosicionY] - '0';

    // System.out.println("ctabledo: " + cTablero[iPosicionX][iPosicionY]);
    // System.out.println("ctabledo convertido a int: " + iMinasDetectada);
    // System.out.println("cantidad de mi: " + iContadorMinas);

    if (iMinasDetectada == iContadorMinas) {
      // System.out.println("cossss6666");
      try {
        if (iTablero[iPosicionX - 1][iPosicionY - 1] == 0) {
          iTablero[iPosicionX - 1][iPosicionY - 1] = 1;
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY);
        }
      } catch (Exception e) {
      }
      try {
        if (iTablero[iPosicionX - 1][iPosicionY] == 0) {
          iTablero[iPosicionX - 1][iPosicionY] = 1;
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY);
        }
      } catch (Exception e) {
      }
      try {
        if (iTablero[iPosicionX - 1][iPosicionY + 1] == 0) {
          iTablero[iPosicionX - 1][iPosicionY + 1] = 1;
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY);
        }
      } catch (Exception e) {
      }
      try {
        if (iTablero[iPosicionX][iPosicionY - 1] == 0) {
          iTablero[iPosicionX][iPosicionY - 1] = 1;
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY);
        }
      } catch (Exception e) {
      }
      try {
        if (iTablero[iPosicionX][iPosicionY + 1] == 0) {
          iTablero[iPosicionX][iPosicionY + 1] = 1;
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY);
        }
      } catch (Exception e) {
      }
      try {
        if (iTablero[iPosicionX + 1][iPosicionY + 1] == 0) {
          iTablero[iPosicionX + 1][iPosicionY + 1] = 1;
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY);
        }
      } catch (Exception e) {
      }
      try {
        if (iTablero[iPosicionX + 1][iPosicionY] == 0) {
          iTablero[iPosicionX + 1][iPosicionY] = 1;
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY);
        }
      } catch (Exception e) {
      }
      try {
        if (iTablero[iPosicionX + 1][iPosicionY - 1] == 0) {
          iTablero[iPosicionX + 1][iPosicionY - 1] = 1;
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY);
        }
      } catch (Exception e) {
      }

      return true;
    } else {
      return false;
    }
  }

  // Funciona para selecionar si se desvelan casillas o se marcan.
  // ----------------------------------------------------------------------------------------
  public boolean fnSeleccionarCasilla(char[][] cTablero, int[][] iTablero) {

    // String sCordenadas;

    System.out.print(" ---------------------------------------------");
    System.out.println("");
    System.out.print(" cordenadas de la casilla [A1, G7, etc..]: ");

    try {

      Scanner sc = new Scanner(System.in);
      String sCordenadas = sc.nextLine();
      //sc.close();

      if (fnPosibleMina(cTablero, iTablero, sCordenadas)) {
        return true;
      }

      if (fnMinaSeleccionada(cTablero, iTablero, sCordenadas)) {
        return true;
      } else if (fnDesbloquearCasilla(cTablero, iTablero, sCordenadas)) {
        return true;
      } else if (fnDesblquearCasillasMultiple(cTablero, iTablero, sCordenadas)) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  // ----------------------------------------------------------------------------------------
  public void pbMostrarTablro(char[][] cTablero, int[][] iTablero, int iCapa) {

    System.out.print("    ");

    for (int iContador = 1; iContador <= cTablero.length; iContador++) {
      if (iContador > 9) {
        System.out.print(" " + iContador + "");
      } else {
        System.out.print(" " + iContador + " ");
      }
    }

    System.out.println("");
    System.out.println(" ---------------------------------------------");

    for (int iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
      System.out.print(" " + Character.forDigit(iPosicionX + 10, 30) + " |");

      for (int iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {

        if (cTablero[iPosicionX][iPosicionY] == 'X' & iCapa == 1) {
          System.out.print(" " + cTablero[iPosicionX][iPosicionY] + " ");
        } else if (iTablero[iPosicionX][iPosicionY] == 1) {
          if (cTablero[iPosicionX][iPosicionY] == '0') {
            System.out.print(" " + " " + " ");
          } else {
            System.out.print(" " + cTablero[iPosicionX][iPosicionY] + " ");
          }
        } else if (iTablero[iPosicionX][iPosicionY] == 3) {
          System.out.print(" " + "?" + " ");
        } else if (iTablero[iPosicionX][iPosicionY] == 2) {

          System.out.print(" " + "!" + " ");

        } else {
          System.out.print(" " + "*" + " ");
        }
      }
      System.out.println("");
    }
  }

  public void pdDesbloquerarCasillasVacias(
      char[][] cTablero, int[][] iTablero, int iPosicionX, int iPosicionY) {

    try {
      if (iTablero[iPosicionX - 1][iPosicionY - 1] == 0) {
        iTablero[iPosicionX - 1][iPosicionY - 1] = 1;
        if (cTablero[iPosicionX - 1][iPosicionY - 1] == '0') {
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX - 1, iPosicionY - 1);
        }
      }

    } catch (Exception e) {

    }

    try {
      if (iTablero[iPosicionX - 1][iPosicionY] == 0) {
        iTablero[iPosicionX - 1][iPosicionY] = 1;
        if (cTablero[iPosicionX - 1][iPosicionY] == '0') {
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX - 1, iPosicionY);
        }
      }

    } catch (Exception e) {

    }

    try {
      if (iTablero[iPosicionX - 1][iPosicionY + 1] == 0) {
        iTablero[iPosicionX - 1][iPosicionY + 1] = 1;
        if (cTablero[iPosicionX - 1][iPosicionY + 1] == '0') {
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX - 1, iPosicionY + 1);
        }
      }

    } catch (Exception e) {

    }

    try {
      if (iTablero[iPosicionX][iPosicionY - 1] == 0) {
        iTablero[iPosicionX][iPosicionY - 1] = 1;
        if (cTablero[iPosicionX][iPosicionY - 1] == '0') {
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY - 1);
        }
      }

    } catch (Exception e) {

    }

    try {
      if (iTablero[iPosicionX][iPosicionY + 1] == 0) {
        iTablero[iPosicionX][iPosicionY + 1] = 1;
        if (cTablero[iPosicionX][iPosicionY + 1] == '0') {
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX, iPosicionY + 1);
        }
      }

    } catch (Exception e) {

    }

    try {
      if (iTablero[iPosicionX + 1][iPosicionY - 1] == 0) {
        iTablero[iPosicionX + 1][iPosicionY - 1] = 1;
        if (cTablero[iPosicionX + 1][iPosicionY - 1] == '0') {
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX + 1, iPosicionY - 1);
        }
      }

    } catch (Exception e) {

    }

    try {
      {
      }
      if (iTablero[iPosicionX + 1][iPosicionY] == 0) {
        iTablero[iPosicionX + 1][iPosicionY] = 1;
        if (cTablero[iPosicionX + 1][iPosicionY] == '0') {
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX + 1, iPosicionY);
        }
      }

    } catch (Exception e) {

    }
    try {
      if (iTablero[iPosicionX + 1][iPosicionY + 1] == 0) {
        iTablero[iPosicionX + 1][iPosicionY + 1] = 1;
        if (cTablero[iPosicionX + 1][iPosicionY + 1] == '0') {
          pdDesbloquerarCasillasVacias(cTablero, iTablero, iPosicionX + 1, iPosicionY + 1);
        }
      }

    } catch (Exception e) {

    }
  }

  public boolean fnVictoria(char[][] cTablero, int[][] iTablero, int iNoMinas) {

    int iDescubiertos = 0;
    boolean bMinaTocada = false;

    for (int iPosicionX = 0; iPosicionX < iTablero.length; iPosicionX++) {
      for (int iPosicionY = 0; iPosicionY < iTablero.length; iPosicionY++) {
        if (iTablero[iPosicionX][iPosicionY] == 1) {
          if (cTablero[iPosicionX][iPosicionY] == 'X') {
            bMinaTocada = true;
          }
          iDescubiertos++;
        }
      }
    }
    if (bMinaTocada) {
      pbMostrarTablro(cTablero, iTablero, iMOSTRAR2);
      System.out.println(" ---------------------------------------------");
      System.out.println("            !!! Has perdido !!!");
      return true;
    }
    if (iNoMinas == iDescubiertos++) {
      pbMostrarTablro(cTablero, iTablero, iMOSTRAR2);
      System.out.println(" ---------------------------------------------");
      System.out.println("            !!! Has ganado !!!");
      return true;
    } else {
      return false;
    }
  }

  public int fnMinasSeleccionadas(int[][] iTablero, int iCantidadMinas) {
    for (int[] iPosicionX : iTablero) {
      for (int iPosicionY : iPosicionX) {
        if (iPosicionY == 2) {
          iCantidadMinas--;
        }
      }
    }
    return iCantidadMinas;
  }

  buscaminas() {
    int iCantidadMinas = 0;
    int iBuecosSinMinas = 1;

    char[][] cTablero = new char[iTAMANO][iTAMANO];

    int[][] iTablero = new int[iTAMANO][iTAMANO];

    int[] iDatos = fnGenedarTablero(cTablero, iTablero);

    iCantidadMinas = iDatos[iCantidadMinas];
    iBuecosSinMinas = iDatos[iBuecosSinMinas];

    System.out.println("");

    do {

      System.out.println("Cantidad de minas: " + fnMinasSeleccionadas(iTablero, iCantidadMinas));

      pbMostrarTablro(cTablero, iTablero, iMOSTRAR);

      while (!fnSeleccionarCasilla(cTablero, iTablero)) {
        System.out.println("Error");
      }
    } while (!fnVictoria(cTablero, iTablero, iBuecosSinMinas));
  }

  public static void main(String[] args) {
    new buscaminas();
  }

}
