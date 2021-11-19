// Autogenerated AST node
package org.python.antlr.ast;
import org.antlr.runtime.Token;
import org.python.antlr.PythonTree;
import org.python.antlr.adapter.AstAdapters;
import org.python.antlr.base.mod;
import org.python.antlr.base.stmt;
import org.python.core.ArgParser;
import org.python.core.AstList;
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

import java.util.ArrayList;

@ExposedType(name = "_ast.Suite", base = mod.class)
public class Suite extends mod {
public static final PyType TYPE = PyType.fromClass(Suite.class);
    private java.util.List<stmt> body;
    public java.util.List<stmt> getInternalBody() {
        return body;
    }
    @ExposedGet(name = "body")
    public PyObject getBody() {
        return new AstList(body, AstAdapters.stmtAdapter);
    }
    @ExposedSet(name = "body")
    public void setBody(PyObject body) {
        this.body = AstAdapters.py2stmtList(body);
    }


    private final static PyUnicode[] fields =
    new PyUnicode[] {new PyUnicode("body")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes = new PyUnicode[0];
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public Suite(PyType subType) {
        super(subType);
    }
    public Suite() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void Suite___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("Suite", args, keywords, new String[]
            {"body"}, 1, true);
        setBody(ap.getPyObject(0, Py.None));
    }

    public Suite(PyObject body) {
        setBody(body);
    }

    public Suite(Token token, java.util.List<stmt> body) {
        super(token);
        this.body = body;
        if (body == null) {
            this.body = new ArrayList<stmt>();
        }
        for(PythonTree t : this.body) {
            addChild(t);
        }
    }

    public Suite(Integer ttype, Token token, java.util.List<stmt> body) {
        super(ttype, token);
        this.body = body;
        if (body == null) {
            this.body = new ArrayList<stmt>();
        }
        for(PythonTree t : this.body) {
            addChild(t);
        }
    }

    public Suite(PythonTree tree, java.util.List<stmt> body) {
        super(tree);
        this.body = body;
        if (body == null) {
            this.body = new ArrayList<stmt>();
        }
        for(PythonTree t : this.body) {
            addChild(t);
        }
    }

    @ExposedGet(name = "repr")
    public String toString() {
        return "Suite";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("Suite(");
        sb.append("body=");
        sb.append(dumpThis(body));
        sb.append(",");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        return visitor.visitSuite(this);
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
        if (body != null) {
            for (PythonTree t : body) {
                if (t != null)
                    t.accept(visitor);
            }
        }
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

    @Override
    public int getNodeType(){return SUITE;};

}
