//*****************************************************************
// Tratamiento de errores sintácticos
//
// Fichero:    SemanticFunctions.java
// Fecha:      03/03/2022
// Versión:    v1.0
// Asignatura: Procesadores de Lenguajes, curso 2021-2022
//*****************************************************************

package lib.tools;

import java.util.*;
import traductor.Token;
import lib.attributes.*;
import lib.symbolTable.*;
import lib.symbolTable.Symbol.Types;
import lib.symbolTable.exceptions.*;
import lib.errores.*;

public class SemanticFunctions {
	private ErrorSemantico errSem; //clase común de errores semánticos

	public SemanticFunctions() {
		errSem = new ErrorSemantico();
	}

	public void checkInt(Attributes at){
	//	System.err.println("CheckInt");
		if(at.type != Symbol.Types.INT){
			errSem.deteccion("Tipo esperado: INT", at.token);
		}
	}

	public void checkBool(Attributes at) {
	//	System.err.println("CheckBool");
		if(at.type != Symbol.Types.BOOL){
			errSem.deteccion("Tipo esperado: boolean",at.token);
		}
	}

	public void checkChar(Attributes at) {
	//	System.err.println("CheckChar");
		if(at.type != Symbol.Types.CHAR){
			errSem.deteccion("Tipo esperado: char",at.token);
		}
	}

	public void check2types(Attributes at1, Attributes at2, Attributes at3, Attributes at){
	//	System.err.println("check2types");
		if(at2.type == null || (at2.type != null && at1.type.equals(at2.type))){
		//	System.err.println("	1 componente");
			at.type = at1.type;
			at.token = at1.token;
		} else {
		//	System.err.println("	" + at2.type);
			at.type = Symbol.Types.UNDEFINED;
			at.token = new Token(at1.token.kind);
			at.token.beginLine = at1.token.beginLine;
			at.token.beginColumn = at1.token.beginColumn;
			at.token.endLine = at2.token.endLine;
			at.token.endColumn = at2.token.endColumn;
			at.token.image = at1.token.image + " " + at3.token.image + " " + at2.token.image;
			errSem.deteccion("Tipos incorrectos",at.token);
		}
	}

	public void check2typesWithOperator(Attributes at1, Attributes at2, Attributes at3, Attributes at) {
	//	System.err.println("check2typesWithOperator");
	//	System.err.println(at1.type == null);
		if(at3.type != null && at2.type != null && (at1.type != at3.type|| at2.type != at3.type)){
	//		System.err.println("	" + at2.type + " " + at3.type);
			at.type = Symbol.Types.UNDEFINED;
			at.token = new Token(at1.token.kind);
			at.token.beginLine = at1.token.beginLine;
			at.token.beginColumn = at1.token.beginColumn;
			at.token.endLine = at2.token.endLine;
			at.token.endColumn = at2.token.endColumn;
			at.token.image = at1.token.image + " " + at3.token.image + " " + at2.token.image;
			errSem.deteccion("Tipos incorrectos", at.token);
		} else { 
		//	System.err.println("	" + at1.type);
			at.type = at1.type;
			at.token = at1.token;
		}
	}

	public void checkArray(SymbolTable ts, Token t, Attributes at){
	//	System.err.println("checkArray");
		Symbol s;
		try {
			s = ts.getSymbol(t.image);
			if(s.type != Symbol.Types.ARRAY) { 
				errSem.deteccion("Identificador usado no es un array", t);
			}
			else { 
				SymbolArray sa = (SymbolArray) s; 
				at.type = sa.baseType; 
			}

		} catch(SymbolNotFoundException e){
			errSem.deteccion(e,t);
		}
	}

	public void checkVariable(SymbolTable ts, Token t, Attributes at){
	//	System.err.println("checkVariable");
		Symbol s;
		try {
			s = ts.getSymbol(t.image);
			at.type = s.type;
		} catch(SymbolNotFoundException e) {
			at.type = Types.UNDEFINED;
			errSem.deteccion(e, t);
		}
	}

	public void insertArraySymbolTab(SymbolTable ts, Token t1, Attributes at, Token t2){
		try{
			ts.insertSymbol(new SymbolArray(t1.image,0,Integer.parseInt(t2.image)-1,at.type));
		} catch (AlreadyDefinedSymbolException e) {
			errSem.deteccion(e, t1);
		}
	}

	public void insertVariableSymbolTab(SymbolTable ts, Token t1, Attributes at){
		try{
			if(at.type == Symbol.Types.INT){
				ts.insertSymbol(new SymbolInt(t1.image));
			} else if (at.type == Symbol.Types.CHAR){
				ts.insertSymbol(new SymbolChar(t1.image));
			} else if (at.type == Symbol.Types.BOOL){
				ts.insertSymbol(new SymbolBool(t1.image));
			}
		} catch (AlreadyDefinedSymbolException e) {
			errSem.deteccion(e, t1);
		}
	}


	public void insertProcedureSymbolTab(SymbolTable ts, Token t){
		try{
			ts.insertSymbol(new SymbolProcedure(t.image,null));
		} catch(AlreadyDefinedSymbolException e){
			errSem.deteccion(e, t);
		}
		ts.insertBlock();
	}

	public void insertFunctionSymbolTab(SymbolTable ts, Token t, Attributes at){
		try{
			ts.insertSymbol(new SymbolFunction(t.image,null,at.type));
		} catch(AlreadyDefinedSymbolException e){
			errSem.deteccion(e, t);
		}
		ts.insertBlock();
	}
}
