import java.awt.*;
import java.awt.datatransfer.*;
import java.lang.reflect.*;
import java.util.*;
public class SetterGetterGenerator
{
private SetterGetterGenerator()
{

}
public static void main(String gg[])
{
if(gg.length==0)
{
System.out.println("Usage: java SetterGetterGenerator class_name ");
return;
}
try
{
Class c=Class.forName(gg[0]);
Field fields[]=c.getDeclaredFields();
StringBuffer stringBuffer;
stringBuffer=new StringBuffer();
//code to generate default constructor
stringBuffer.append("public ");
stringBuffer.append(c.getSimpleName());
stringBuffer.append("()\r\n");
stringBuffer.append("{\r\n");
int i;
Field field;
for(i=0;i<fields.length;i++)
{
field=fields[i];
stringBuffer.append("this.");
stringBuffer.append(field.getName());
stringBuffer.append(" = ");
stringBuffer.append(getDefaultValueString(field));
stringBuffer.append(";\r\n ");
}
stringBuffer.append("}\r\n ");
//code to generate default constructor ends here
//code to generate setter/getter for every field starts here
for(i=0;i<fields.length;i++)
{
field=fields[i];
//code to generate setter starts here
stringBuffer.append("public void ");
stringBuffer.append(getSetterName(field));
stringBuffer.append("(");
stringBuffer.append(field.getType().getName());
stringBuffer.append(" ");
stringBuffer.append(field.getName());
stringBuffer.append(")\r\n");
stringBuffer.append("{\r\n");
stringBuffer.append("this.");
stringBuffer.append(field.getName());
stringBuffer.append("=");
stringBuffer.append(field.getName());
stringBuffer.append(";\r\n");
stringBuffer.append("}\r\n");
//code to generate getter starts here
stringBuffer.append("public ");
stringBuffer.append(field.getType().getName());
stringBuffer.append(" ");
stringBuffer.append(getGetterName(field));
stringBuffer.append("()\r\n");
stringBuffer.append("{\r\n");
stringBuffer.append("return this.");
stringBuffer.append(field.getName());
stringBuffer.append(";\r\n");
stringBuffer.append("}\r\n");
}
//code to generate setter/getter for every field ends here
StringSelection stringSelection;
stringSelection=new StringSelection(stringBuffer.toString());
Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
clipboard.setContents(stringSelection,stringSelection);
}catch(ClassNotFoundException classNotFoundException)
{
System.out.println("Class not found,set classpath or class is missing "+gg[0]);
}
}
private static String getDefaultValueString(Field field)
{

Class fc=field.getType();
if(fc.getName().equals("long") || fc.getName().equals("java.lang.Long")) return "0";
if(fc.getName().equals("short") || fc.getName().equals("java.lang.Short")) return "(short)0";
if(fc.getName().equals("int") || fc.getName().equals("java.lang.Integer")) return "0";
if(fc.getName().equals("byte") || fc.getName().equals("java.lang.Byte")) return "(byte)0";
if(fc.getName().equals("double") || fc.getName().equals("java.lang.Double")) return "0.0";
if(fc.getName().equals("float") || fc.getName().equals("java.lang.Float")) return "0.0f";
if(fc.getName().equals("char") || fc.getName().equals("java.lang.Character")) return "(char)0";
if(fc.getName().equals("boolean") || fc.getName().equals("java.lang.Boolean")) return "false";
if( fc.getName().equals("java.lang.String")) return "\"\"";
return null;
}
private  static String getSetterName(Field field)
{
String name=field.getName();
if(name.length()==1) return "set"+name.toUpperCase();
return "set"+String.valueOf(name.charAt(0)).toUpperCase()+name.substring(1);
}
private  static String getGetterName(Field field)
{
String name=field.getName();
if(name.length()==1) return "get"+name.toUpperCase();
return "get"+String.valueOf(name.charAt(0)).toUpperCase()+name.substring(1);
}
}
