/* adac.java */
/* Generated By:JavaCC: Do not edit this line. adac.java */
package traductor;

import java.util.*;
import java.lang.Exception.*;
import lib.symbolTable.*;
import lib.symbolTable.exceptions.*;
import lib.errores.*;
import lib.attributes.*;
import lib.tools.*;


public class adac implements adacConstants {

        static SymbolTable ts = new SymbolTable();
//	static ErrorSemantico err =  new ErrorSemantico();
        static SemanticFunctions semFuncs = new SemanticFunctions();

    public static void main(String[] args) {
        adac parser;

        try {
                if(args.length == 0) { //entrada desde stdin
                                parser = new adac(System.in);
                        }
                        else { //entrada desde fichero en args[0]
                    parser = new adac(new java.io.FileInputStream(args[0]));
                        }
                        parser.S(); //invoca símbolo inicial de la gramática
                }
                catch (java.io.FileNotFoundException e) {
                        System.err.println ("Fichero " + args[0] + " no encontrado.");
                }
                catch (TokenMgrError e) {
                System.err.println("LEX_ERROR: " + e.getMessage());
        }
        catch (ParseException e) {
                System.err.println("SINT_ERROR: " + e.getMessage());
        }
    }

  static final public void S() throws ParseException {
    prog();
    jj_consume_token(0);
}

//------------ Símbolo inicial de la gramática. Para análisis léxico no hace falta más
  static final public void prog() throws ParseException {
    jj_consume_token(tPROC);
    jj_consume_token(tID);
    jj_consume_token(tIS);
    declaracion_variables();
    declaracion_procs_funcs();
    bloque_sentencias();
System.err.println(ts.toString());
}

  static final public void declaracion_variables() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tBOOL:
      case tCHAR:
      case tINT:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      declaracion();
      jj_consume_token(tPC);
    }
}

  static final public void declaracion() throws ParseException {Symbol s = null;
        Token t1;
        Attributes at = new Attributes();
    tipo_variable(at);
    lista_vars(at);
}

  static final public void tipo_variable(Attributes at) throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tINT:{
      jj_consume_token(tINT);
at.type = Symbol.Types.INT;
      break;
      }
    case tCHAR:{
      jj_consume_token(tCHAR);
at.type = Symbol.Types.CHAR;
      break;
      }
    case tBOOL:{
      jj_consume_token(tBOOL);
at.type = Symbol.Types.BOOL;
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  static final public void lista_vars(Attributes at) throws ParseException {
    variable(at);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tCOMA:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(tCOMA);
      variable(at);
    }
}

  static final public void variable(Attributes at) throws ParseException {Token t1, t2;
    if (jj_2_1(2)) {
      t1 = jj_consume_token(tID);
      jj_consume_token(tCORCHETEOPEN);
      t2 = jj_consume_token(tNUM);
      jj_consume_token(tCORCHETECLOSE);
semFuncs.insertArraySymbolTab(ts,t1,at,t2);
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tID:{
        t1 = jj_consume_token(tID);
semFuncs.insertVariableSymbolTab(ts,t1,at);
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
}

  static final public void declaracion_procs_funcs() throws ParseException {
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tFUNC:
      case tPROC:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
      declaracion_proc_func();
    }
}

  static final public void declaracion_proc_func() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tPROC:{
      declaracion_procedimientos();
      break;
      }
    case tFUNC:{
      declaracion_funciones();
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  static final public void declaracion_procedimientos() throws ParseException {
    cabecera_procedimiento();
    declaracion_variables();
    bloque_sentencias();
ts.removeBlock();
}

  static final public void declaracion_funciones() throws ParseException {
    cabecera_funcion();
    declaracion_variables();
    bloque_sentencias();
ts.removeBlock();
}

  static final public void cabecera_procedimiento() throws ParseException {Token t;
    jj_consume_token(tPROC);
    t = jj_consume_token(tID);
semFuncs.insertProcedureSymbolTab(ts,t);
    jj_consume_token(tPOPEN);
    lista_parametros();
    jj_consume_token(tPCLOSE);
    jj_consume_token(tIS);
}

  static final public void cabecera_funcion() throws ParseException {Token t;
        Symbol.Types tipo;
        Attributes at = new Attributes();
    jj_consume_token(tFUNC);
    tipo_variable(at);
    t = jj_consume_token(tID);
semFuncs.insertFunctionSymbolTab(ts,t,at);
    jj_consume_token(tPOPEN);
    lista_parametros();
    jj_consume_token(tPCLOSE);
    jj_consume_token(tIS);
}

  static final public void lista_parametros() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tVAL:
    case tREF:{
      parametro();
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case tPC:{
          ;
          break;
          }
        default:
          jj_la1[6] = jj_gen;
          break label_4;
        }
        jj_consume_token(tPC);
        parametro();
      }
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      ;
    }
}

  static final public void parametro() throws ParseException {Symbol.Types tipo;
        Attributes at = new Attributes();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tVAL:{
      jj_consume_token(tVAL);
      break;
      }
    case tREF:{
      jj_consume_token(tREF);
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    tipo_variable(at);
    lista_vars(at);
}

  static final public void bloque_sentencias() throws ParseException {
    try {
      jj_consume_token(tBEGIN);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case tFUNC:
        case tPROC:
        case tRET:
        case tSkL:
        case tIF:
        case tWHILE:
        case tPUTLINE:
        case tPUT:
        case tGET:
        case tID:{
          ;
          break;
          }
        default:
          jj_la1[9] = jj_gen;
          break label_5;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case tRET:
        case tSkL:
        case tIF:
        case tWHILE:
        case tPUTLINE:
        case tPUT:
        case tGET:
        case tID:{
          inst();
          break;
          }
        case tFUNC:
        case tPROC:{
          declaracion_proc_func();
          break;
          }
        default:
          jj_la1[10] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      jj_consume_token(tEND);
    } catch (ParseException e) {
Set<Integer> conjSinc = infoParseException(e);
                conjSinc.add(tEND);
                recuperacionPanico(e.currentToken.next,conjSinc);
    }
}

  static final public void inst() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tRET:
    case tSkL:
    case tPUTLINE:
    case tPUT:
    case tGET:
    case tID:{
      inst_acaban_pc();
      break;
      }
    case tIF:
    case tWHILE:{
      inst_acaban_end();
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  static final public void inst_acaban_pc() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tGET:{
        inst_leer();
        break;
        }
      case tSkL:{
        inst_saltar_linea();
        break;
        }
      case tPUT:{
        inst_escribir();
        break;
        }
      case tPUTLINE:{
        inst_escribir_linea();
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        if (jj_2_2(2)) {
          inst_invoc_proc();
        } else {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case tID:{
            inst_asignacion();
            break;
            }
          case tRET:{
            inst_return();
            break;
            }
          default:
            jj_la1[13] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      }
      jj_consume_token(tPC);
    } catch (ParseException e) {
Set<Integer> conjSinc = infoParseException(e);
                conjSinc.add(tPC);
                recuperacionPanico(e.currentToken.next,conjSinc);
    }
}

  static final public void inst_acaban_end() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tWHILE:{
        inst_iteracion();
        break;
        }
      case tIF:{
        inst_seleccion();
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(tEND);
    } catch (ParseException e) {
Set<Integer> conjSinc = infoParseException(e);
                conjSinc.add(tEND);
                recuperacionPanico(e.currentToken.next,conjSinc);
    }
}

  static final public void inst_asignacion() throws ParseException {Attributes at1 = new Attributes(), at2 = new Attributes();
    asignable(at1);
    jj_consume_token(tASIG);
    expresion(at2);
semFuncs.checkAsignacion(at1,at2);
}

  static final public void asignable(Attributes at) throws ParseException {Attributes at1 = new Attributes();
        Token t;
    if (jj_2_3(2)) {
      t = jj_consume_token(tID);
      jj_consume_token(tCORCHETEOPEN);
      expresion(at1);
      jj_consume_token(tCORCHETECLOSE);
semFuncs.checkAsignable(ts,t,at1,at);
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tID:{
        t = jj_consume_token(tID);
semFuncs.checkAsignable(ts,t,at);
        break;
        }
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
}

  static final public void inst_iteracion() throws ParseException {Attributes at = new Attributes();
    jj_consume_token(tWHILE);
    expresion(at);
    jj_consume_token(tDO);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tRET:
      case tSkL:
      case tIF:
      case tWHILE:
      case tPUTLINE:
      case tPUT:
      case tGET:
      case tID:{
        ;
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        break label_6;
      }
      inst();
    }
}

  static final public void inst_return() throws ParseException {Attributes at = new Attributes();
    jj_consume_token(tRET);
    expresion(at);
}

  static final public void inst_leer() throws ParseException {Attributes at1 = new Attributes(), at2 = new Attributes();
    try {
      jj_consume_token(tGET);
      jj_consume_token(tPOPEN);
      asignable(at1);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case tCOMA:{
          ;
          break;
          }
        default:
          jj_la1[17] = jj_gen;
          break label_7;
        }
        jj_consume_token(tCOMA);
        asignable(at2);
      }
      jj_consume_token(tPCLOSE);
    } catch (ParseException e) {
Set<Integer> conjSinc = infoParseException(e);
                conjSinc.add(tPCLOSE);
                recuperacionPanico(e.currentToken.next,conjSinc);
    }
}

  static final public void inst_escribir() throws ParseException {Attributes at1 = new Attributes();
    try {
      jj_consume_token(tPUT);
      jj_consume_token(tPOPEN);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tPOPEN:
      case tNUM:
      case tINT2CHAR:
      case tCHAR2INT:
      case tNOT:
      case tTRUE:
      case tFALSE:
      case tSUM:
      case tRES:
      case tSTRING:
      case tCARACTER:
      case tID:{
        lista_una_o_mas_exps(at1);
        break;
        }
      default:
        jj_la1[18] = jj_gen;
        ;
      }
      jj_consume_token(tPCLOSE);
    } catch (ParseException e) {
Set<Integer> conjSinc = infoParseException(e);
                conjSinc.add(tPCLOSE);
                recuperacionPanico(e.currentToken.next,conjSinc);
    }
}

  static final public void inst_escribir_linea() throws ParseException {Attributes at1 = new Attributes();
    try {
      jj_consume_token(tPUTLINE);
      jj_consume_token(tPOPEN);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tPOPEN:
      case tNUM:
      case tINT2CHAR:
      case tCHAR2INT:
      case tNOT:
      case tTRUE:
      case tFALSE:
      case tSUM:
      case tRES:
      case tSTRING:
      case tCARACTER:
      case tID:{
        lista_una_o_mas_exps(at1);
        break;
        }
      default:
        jj_la1[19] = jj_gen;
        ;
      }
      jj_consume_token(tPCLOSE);
    } catch (ParseException e) {
Set<Integer> conjSinc = infoParseException(e);
                conjSinc.add(tPCLOSE);
                recuperacionPanico(e.currentToken.next,conjSinc);
    }
}

  static final public void inst_invoc_proc() throws ParseException {Attributes at1 = new Attributes();
    try {
      jj_consume_token(tID);
      jj_consume_token(tPOPEN);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tPOPEN:
      case tNUM:
      case tINT2CHAR:
      case tCHAR2INT:
      case tNOT:
      case tTRUE:
      case tFALSE:
      case tSUM:
      case tRES:
      case tSTRING:
      case tCARACTER:
      case tID:{
        lista_una_o_mas_exps(at1);
        break;
        }
      default:
        jj_la1[20] = jj_gen;
        ;
      }
      jj_consume_token(tPCLOSE);
    } catch (ParseException e) {
Set<Integer> conjSinc = infoParseException(e);
                conjSinc.add(tPCLOSE);
                recuperacionPanico(e.currentToken.next,conjSinc);
    }
}

  static final public void inst_seleccion() throws ParseException {Attributes at = new Attributes();
    jj_consume_token(tIF);
    expresion(at);
    jj_consume_token(tTHEN);
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tRET:
      case tSkL:
      case tIF:
      case tWHILE:
      case tPUTLINE:
      case tPUT:
      case tGET:
      case tID:{
        ;
        break;
        }
      default:
        jj_la1[21] = jj_gen;
        break label_8;
      }
      inst();
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tELSE:{
      jj_consume_token(tELSE);
      label_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case tRET:
        case tSkL:
        case tIF:
        case tWHILE:
        case tPUTLINE:
        case tPUT:
        case tGET:
        case tID:{
          ;
          break;
          }
        default:
          jj_la1[22] = jj_gen;
          break label_9;
        }
        inst();
      }
      break;
      }
    default:
      jj_la1[23] = jj_gen;
      ;
    }
}

  static final public void inst_saltar_linea() throws ParseException {
    try {
      jj_consume_token(tSkL);
      jj_consume_token(tPOPEN);
      jj_consume_token(tPCLOSE);
    } catch (ParseException e) {
Set<Integer> conjSinc = infoParseException(e);
                conjSinc.add(tPCLOSE);
                recuperacionPanico(e.currentToken.next,conjSinc);
    }
}

  static final public void lista_cero_o_mas_exps() throws ParseException {Attributes at = new Attributes();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tPOPEN:
    case tNUM:
    case tINT2CHAR:
    case tCHAR2INT:
    case tNOT:
    case tTRUE:
    case tFALSE:
    case tSUM:
    case tRES:
    case tSTRING:
    case tCARACTER:
    case tID:{
      lista_una_o_mas_exps(at);
      break;
      }
    default:
      jj_la1[24] = jj_gen;
      ;
    }
}

  static final public void lista_una_o_mas_exps(Attributes at) throws ParseException {Attributes at1 = new Attributes(), at2 = new Attributes(), at3 = new Attributes();
    expresion(at1);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tCOMA:{
      at3.token = jj_consume_token(tCOMA);
      lista_una_o_mas_exps(at2);
      break;
      }
    default:
      jj_la1[25] = jj_gen;
      ;
    }
}

  static final public void expresion(Attributes at) throws ParseException {Attributes at1 = new Attributes(), at2 = new Attributes(), at3 = new Attributes();
    expresion_simple(at1);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tMAYEQ:
    case tMENEQ:
    case tEQ:
    case tMAY:
    case tMEN:
    case tDIST:{
      operador_relacional(at3);
      expresion_simple(at2);
      break;
      }
    default:
      jj_la1[26] = jj_gen;
      ;
    }
semFuncs.check2typesWithRelationalOperator(at1, at2, at3, at);
}

  static final public void operador_relacional(Attributes at) throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tEQ:{
      t = jj_consume_token(tEQ);
at.token = t;
      break;
      }
    case tMAYEQ:{
      t = jj_consume_token(tMAYEQ);
at.token = t;
      break;
      }
    case tMENEQ:{
      t = jj_consume_token(tMENEQ);
at.token = t;
      break;
      }
    case tMAY:{
      t = jj_consume_token(tMAY);
at.token = t;
      break;
      }
    case tMEN:{
      t = jj_consume_token(tMEN);
at.token = t;
      break;
      }
    case tDIST:{
      t = jj_consume_token(tDIST);
at.token = t;
      break;
      }
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  static final public void expresion_simple(Attributes at) throws ParseException {Attributes at1 = new Attributes(), at2 = new Attributes(), at3 = new Attributes();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tSUM:
    case tRES:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tRES:{
        jj_consume_token(tRES);
        break;
        }
      case tSUM:{
        jj_consume_token(tSUM);
        break;
        }
      default:
        jj_la1[28] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[29] = jj_gen;
      ;
    }
    termino(at1);
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tOR:
      case tSUM:
      case tRES:{
        ;
        break;
        }
      default:
        jj_la1[30] = jj_gen;
        break label_10;
      }
      operador_aditivo(at3);
      termino(at2);
    }
semFuncs.check2typesWithOperator(at1,at2,at3,at);
}

  static final public void operador_aditivo(Attributes at) throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tSUM:{
      t = jj_consume_token(tSUM);
at.type = Symbol.Types.INT;
                        at.token = t;
      break;
      }
    case tRES:{
      t = jj_consume_token(tRES);
at.type = Symbol.Types.INT;
                        at.token = t;
      break;
      }
    case tOR:{
      t = jj_consume_token(tOR);
at.type = Symbol.Types.BOOL;
                        at.token = t;
      break;
      }
    default:
      jj_la1[31] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  static final public void termino(Attributes at) throws ParseException {Attributes at1 = new Attributes(), at2 = new Attributes(), at3 = new Attributes();
        Token t;
        Symbol.Types expected;
    factor(at1);
    label_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tAND:
      case tMUL:
      case tDIV:
      case tMOD:{
        ;
        break;
        }
      default:
        jj_la1[32] = jj_gen;
        break label_11;
      }
      operador_multiplicativo(at3);
      factor(at2);
    }
semFuncs.check2typesWithOperator(at1,at2,at3,at);
}

  static final public void operador_multiplicativo(Attributes at) throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tMUL:{
      t = jj_consume_token(tMUL);
at.type = Symbol.Types.INT;
                        at.token = t;
      break;
      }
    case tMOD:{
      t = jj_consume_token(tMOD);
at.type = Symbol.Types.INT;
                        at.token = t;
      break;
      }
    case tDIV:{
      t = jj_consume_token(tDIV);
at.type = Symbol.Types.INT;
                        at.token = t;
      break;
      }
    case tAND:{
      t = jj_consume_token(tAND);
at.type = Symbol.Types.BOOL;
                        at.token = t;
      break;
      }
    default:
      jj_la1[33] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  static final public void factor(Attributes at) throws ParseException {Token t;
        Symbol s;
        Symbol.Types tipo;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case tNOT:{
      jj_consume_token(tNOT);
      factor(at);
semFuncs.checkBool(at);
      break;
      }
    case tPOPEN:{
      jj_consume_token(tPOPEN);
      expresion(at);
      jj_consume_token(tPCLOSE);
      break;
      }
    default:
      jj_la1[34] = jj_gen;
      if (jj_2_4(2)) {
        factor_con_par(at);
      } else if (jj_2_5(2)) {
        t = jj_consume_token(tID);
        jj_consume_token(tCORCHETEOPEN);
        expresion(at);
        jj_consume_token(tCORCHETECLOSE);
semFuncs.checkArray(ts,t,at);
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case tID:{
          t = jj_consume_token(tID);
// comprobar que está en la tabla de símbolos y asignarle el tipo;
                        at.token = t;
                        semFuncs.checkVariable(ts,t,at);
          break;
          }
        case tNUM:{
          t = jj_consume_token(tNUM);
at.type = Symbol.Types.INT;
                        at.token = t;
          break;
          }
        case tCARACTER:{
          t = jj_consume_token(tCARACTER);
at.type = Symbol.Types.CHAR;
                        at.token = t;
          break;
          }
        case tSTRING:{
          t = jj_consume_token(tSTRING);
at.type = Symbol.Types.CHAR;
                        at.token = t;
          break;
          }
        case tTRUE:{
          t = jj_consume_token(tTRUE);
at.type = Symbol.Types.BOOL;
                        at.token = t;
          break;
          }
        case tFALSE:{
          t = jj_consume_token(tFALSE);
at.type = Symbol.Types.BOOL;
                        at.token = t;
          break;
          }
        default:
          jj_la1[35] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
}

  static final public void factor_con_par(Attributes at) throws ParseException {Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case tINT2CHAR:{
        jj_consume_token(tINT2CHAR);
        jj_consume_token(tPOPEN);
        expresion(at);
        jj_consume_token(tPCLOSE);
semFuncs.checkInt(at);
        break;
        }
      case tCHAR2INT:{
        jj_consume_token(tCHAR2INT);
        jj_consume_token(tPOPEN);
        expresion(at);
        jj_consume_token(tPCLOSE);
semFuncs.checkChar(at);
        break;
        }
      default:
        jj_la1[36] = jj_gen;
        if (jj_2_6(2)) {
          t = jj_consume_token(tID);
          jj_consume_token(tPOPEN);
          lista_cero_o_mas_exps();
          jj_consume_token(tPCLOSE);
// comprobar que t está en la tabla de símbolos
                                // invocación a función
                                at.token = t;
                                semFuncs.checkFunction(ts,t,at);
        } else {
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (ParseException e) {
Set<Integer> conjSinc = infoParseException(e);
                conjSinc.add(tPCLOSE);
                recuperacionPanico(e.currentToken.next,conjSinc);
    }
}

  static Set<Integer> infoParseException(ParseException e) throws ParseException {Set<Integer> esperados = new HashSet<Integer>();
        System.err.println("ERROR SINT: ('" + e.currentToken.next.image + "'," + e.currentToken.next.beginLine + "," + e.currentToken.next.beginColumn + ")");
        System.err.println("Se esperaba uno de los siguientes tokens:");
        for (int i = 0; i < e.expectedTokenSequences.length ; i++){
                esperados.add(e.expectedTokenSequences[i][0]);
                System.err.println("\t" + adacConstants.tokenImage[e.expectedTokenSequences[i][0]]);
        }
        return esperados;
  }

  static void recuperacionPanico(Token tInesperado, Set<Integer> conjSinc) throws ParseException {System.err.println("----> Recupero modo p\u00e1nico: " + "\n----> Saltando todo hasta token de conjunto de sincronizaci\u00f3n");
        Token t = getNextToken();
        while(!conjSinc.contains(t.kind) && t.kind != EOF){
                System.err.println("Descartando token (" + adacConstants.tokenImage[t.kind] + "," + t.image + ")");
                t = getNextToken();
        }
  }

  static private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_1()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_2()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_3()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_4()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_5()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_6()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_3_2()
 {
    if (jj_3R_inst_invoc_proc_515_5_12()) return true;
    return false;
  }

  static private boolean jj_3R_factor_con_par_737_17_14()
 {
    if (jj_scan_token(tINT2CHAR)) return true;
    if (jj_scan_token(tPOPEN)) return true;
    return false;
  }

  static private boolean jj_3R_inst_invoc_proc_515_5_12()
 {
    if (jj_scan_token(tID)) return true;
    if (jj_scan_token(tPOPEN)) return true;
    return false;
  }

  static private boolean jj_3_5()
 {
    if (jj_scan_token(tID)) return true;
    if (jj_scan_token(tCORCHETEOPEN)) return true;
    return false;
  }

  static private boolean jj_3_4()
 {
    if (jj_3R_factor_con_par_736_9_13()) return true;
    return false;
  }

  static private boolean jj_3_6()
 {
    if (jj_scan_token(tID)) return true;
    if (jj_scan_token(tPOPEN)) return true;
    return false;
  }

  static private boolean jj_3R_factor_con_par_736_9_13()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_factor_con_par_737_17_14()) {
    jj_scanpos = xsp;
    if (jj_3R_factor_con_par_741_25_15()) {
    jj_scanpos = xsp;
    if (jj_3_6()) return true;
    }
    }
    return false;
  }

  static private boolean jj_3R_factor_con_par_741_25_15()
 {
    if (jj_scan_token(tCHAR2INT)) return true;
    if (jj_scan_token(tPOPEN)) return true;
    return false;
  }

  static private boolean jj_3_3()
 {
    if (jj_scan_token(tID)) return true;
    if (jj_scan_token(tCORCHETEOPEN)) return true;
    return false;
  }

  static private boolean jj_3_1()
 {
    if (jj_scan_token(tID)) return true;
    if (jj_scan_token(tCORCHETEOPEN)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public adacTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[37];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x1c0000,0x1c0000,0x1000,0x0,0x600000,0x600000,0x2000,0x3000000,0x3000000,0x64600000,0x64600000,0x64000000,0x20000000,0x4000000,0x40000000,0x0,0x64000000,0x1000,0x18010100,0x18010100,0x18010100,0x64000000,0x64000000,0x0,0x18010100,0x1000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x100,0x10000,0x18000000,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x0,0x4000000,0x0,0x0,0x0,0x0,0x0,0x40000e4,0x40000e4,0x40000e4,0xe0,0x4000000,0x4,0x4000000,0x40000e4,0x0,0x71e0400,0x71e0400,0x71e0400,0x40000e4,0x40000e4,0x1,0x71e0400,0x0,0x1f800,0x1f800,0x180000,0x180000,0x180200,0x180200,0xe00100,0xe00100,0x400,0x7060000,0x0,};
	}
  static final private JJCalls[] jj_2_rtns = new JJCalls[6];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public adac(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public adac(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new adacTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 37; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 37; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public adac(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new adacTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 37; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new adacTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 37; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public adac(adacTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 37; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(adacTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 37; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   if (++jj_gc > 100) {
		 jj_gc = 0;
		 for (int i = 0; i < jj_2_rtns.length; i++) {
		   JJCalls c = jj_2_rtns[i];
		   while (c != null) {
			 if (c.gen < jj_gen) c.first = null;
			 c = c.next;
		   }
		 }
	   }
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error {
    @Override
    public Throwable fillInStackTrace() {
      return this;
    }
  }
  static private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
	 if (jj_scanpos == jj_lastpos) {
	   jj_la--;
	   if (jj_scanpos.next == null) {
		 jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
	   } else {
		 jj_lastpos = jj_scanpos = jj_scanpos.next;
	   }
	 } else {
	   jj_scanpos = jj_scanpos.next;
	 }
	 if (jj_rescan) {
	   int i = 0; Token tok = token;
	   while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
	   if (tok != null) jj_add_error_token(kind, i);
	 }
	 if (jj_scanpos.kind != kind) return true;
	 if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
	 return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
	 if (pos >= 100) {
		return;
	 }

	 if (pos == jj_endpos + 1) {
	   jj_lasttokens[jj_endpos++] = kind;
	 } else if (jj_endpos != 0) {
	   jj_expentry = new int[jj_endpos];

	   for (int i = 0; i < jj_endpos; i++) {
		 jj_expentry[i] = jj_lasttokens[i];
	   }

	   for (int[] oldentry : jj_expentries) {
		 if (oldentry.length == jj_expentry.length) {
		   boolean isMatched = true;

		   for (int i = 0; i < jj_expentry.length; i++) {
			 if (oldentry[i] != jj_expentry[i]) {
			   isMatched = false;
			   break;
			 }

		   }
		   if (isMatched) {
			 jj_expentries.add(jj_expentry);
			 break;
		   }
		 }
	   }

	   if (pos != 0) {
		 jj_lasttokens[(jj_endpos = pos) - 1] = kind;
	   }
	 }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[60];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 37; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 60; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 jj_endpos = 0;
	 jj_rescan_token();
	 jj_add_error_token(0, 0);
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
	 jj_rescan = true;
	 for (int i = 0; i < 6; i++) {
	   try {
		 JJCalls p = jj_2_rtns[i];

		 do {
		   if (p.gen > jj_gen) {
			 jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
			 switch (i) {
			   case 0: jj_3_1(); break;
			   case 1: jj_3_2(); break;
			   case 2: jj_3_3(); break;
			   case 3: jj_3_4(); break;
			   case 4: jj_3_5(); break;
			   case 5: jj_3_6(); break;
			 }
		   }
		   p = p.next;
		 } while (p != null);

		 } catch(LookaheadSuccess ls) { }
	 }
	 jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
	 JJCalls p = jj_2_rtns[index];
	 while (p.gen > jj_gen) {
	   if (p.next == null) { p = p.next = new JJCalls(); break; }
	   p = p.next;
	 }

	 p.gen = jj_gen + xla - jj_la; 
	 p.first = token;
	 p.arg = xla;
  }

  static final class JJCalls {
	 int gen;
	 Token first;
	 int arg;
	 JJCalls next;
  }

}
