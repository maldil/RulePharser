// Autogenerated AST node
package org.python.antlr.ast;
import org.antlr.runtime.Token;
import org.python.antlr.PythonTree;
import org.python.antlr.adapter.AstAdapters;
import org.python.antlr.base.expr;
import org.python.core.ArgParser;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyUnicode;
import org.python.core.PyStringMap;
import org.python.core.PyType;
import org.python.expose.ExposedGet;
import org.python.expose.ExposedMethod;
import org.python.expose.ExposedNew;
import org.python.expose.ExposedSet;
import org.python.expose.ExposedType;

@ExposedType(name = "_ast.Num", base = expr.class)
public class Num extends expr {
public static final PyType TYPE = PyType.fromClass(Num.class);
    private Object n;
    public Object getInternalN() {
        return n;
    }
    @ExposedGet(name = "n")
    public PyObject getN() {
        return (PyObject)n;
    }
    @ExposedSet(name = "n")
    public void setN(PyObject n) {
        this.n = AstAdapters.py2object(n);
    }


    private final static PyUnicode[] fields =
    new PyUnicode[] {new PyUnicode("n")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes =
    new PyUnicode[] {new PyUnicode("lineno"), new PyUnicode("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public Num(PyType subType) {
        super(subType);
    }
    public Num() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void Num___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("Num", args, keywords, new String[]
            {"n", "lineno", "col_offset"}, 1, true);
        setN(ap.getPyObject(0, Py.None));
        int lin = ap.getInt(1, -1);
        if (lin != -1) {
            setLineno(lin);
        }

        int col = ap.getInt(2, -1);
        if (col != -1) {
            setLineno(col);
        }

    }

    public Num(PyObject n) {
        setN(n);
    }

    public Num(Token token, Object n) {
        super(token);
        this.n = n;
    }

    public Num(Integer ttype, Token token, Object n) {
        super(ttype, token);
        this.n = n;
    }

    public Num(PythonTree tree, Object n) {
        super(tree);
        this.n = n;
    }

    @ExposedGet(name = "repr")
    public String toString() {
        return "Num";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("Num(");
        sb.append("n=");
        sb.append(dumpThis(n));
        sb.append(",");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        return visitor.visitNum(this);
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
    }

    public PyObject __dict__;

    @Override
    public PyObject fastGetDict() {
        ensureDict();
        return __dict__;
    }

    @ExposedGet(name = "__dict__")
    public PyObject getDict() {
        return fastGetDict();
    }

    private void ensureDict() {
        if (__dict__ == null) {
            __dict__ = new PyStringMap();
        }
    }

    private int lineno = -1;
    @ExposedGet(name = "lineno")
    public int getLineno() {
        if (lineno != -1) {
            return lineno;
        }
        return getLine();
    }

    @ExposedSet(name = "lineno")
    public void setLineno(int num) {
        lineno = num;
    }

    private int col_offset = -1;
    @ExposedGet(name = "col_offset")
    public int getCol_offset() {
        if (col_offset != -1) {
            return col_offset;
        }
        return getCharPositionInLine();
    }

    @ExposedSet(name = "col_offset")
    public void setCol_offset(int num) {
        col_offset = num;
    }

    @Override
    public int getNodeType(){return NUM;};

}
