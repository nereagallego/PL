//*****************************************************************
// File:   Attributes.java
// Author: Procesadores de Lenguajes-University of Zaragoza
// Date:   enero 2022
//         Clase única para almacenar los atributos que pueden aparecer en el traductor de adac
//         Se hace como clase plana, por simplicidad. Los atributos que se pueden almacenar
//         se autoexplican con el nombre
//*****************************************************************

package lib.attributes;
import lib.symbolTable.*;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;
import traductor.Token;

public class Attributes implements Cloneable {
    public Symbol.Types type;
    public Symbol.ParameterClass parClass;

    public int valInt;
    public boolean valBool;
    public char valChar;
    public String valString;
    //COMPLETAR

    public Token token;

    public Attributes(){
        type = null;
    }

    public Attributes(Symbol.Types tipo) {
        type = tipo;
    }

    

    public Attributes clone() {
    	try {
    		return (Attributes) super.clone();
    	}
    	catch (CloneNotSupportedException e) {
    		return null; 
    	}
    }

    public String toString() {
        return
            "type = " + type + "\n" +
            "parClass = " + parClass + "\n" +
            "token = " + token.image + "\n" ;
            //COMPLETAR
        
    }
}
