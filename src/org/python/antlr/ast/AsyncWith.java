// Autogenerated AST node
package org.python.antlr.ast;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.python.antlr.AST;
import org.python.antlr.PythonTree;
import org.python.antlr.adapter.AstAdapters;
import org.python.antlr.base.excepthandler;
import org.python.antlr.base.expr;
import org.python.antlr.base.mod;
import org.python.antlr.base.slice;
import org.python.antlr.base.stmt;
import org.python.core.ArgParser;
import org.python.core.AstList;
import org.python.core.Py;
import org.python.core.PyObject;

import org.python.core.PyUnicode;
import org.python.core.PyStringMap;
import org.python.core.PyType;
import org.python.core.Visitproc;
import org.python.expose.ExposedGet;
import org.python.expose.ExposedMethod;
import org.python.expose.ExposedNew;
import org.python.expose.ExposedSet;
import org.python.expose.ExposedType;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@ExposedType(name = "_ast.AsyncWith", base = stmt.class)
public class AsyncWith extends stmt {
public static final PyType TYPE = PyType.fromClass(AsyncWith.class);
    private java.util.List<withitem> items;
    public java.util.List<withitem> getInternalItems() {
        return items;
    }
    @ExposedGet(name = "items")
    public PyObject getItems() {
        return new AstList(items, AstAdapters.withitemAdapter);
    }
    @ExposedSet(name = "items")
    public void setItems(PyObject items) {
        this.items = AstAdapters.py2withitemList(items);
    }

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
    new PyUnicode[] {new PyUnicode("items"), new PyUnicode("body")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes =
    new PyUnicode[] {new PyUnicode("lineno"), new PyUnicode("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public AsyncWith(PyType subType) {
        super(subType);
    }
    public AsyncWith() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void AsyncWith___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("AsyncWith", args, keywords, new String[]
            {"items", "body", "lineno", "col_offset"}, 2, true);
        setItems(ap.getPyObject(0, Py.None));
        setBody(ap.getPyObject(1, Py.None));
        int lin = ap.getInt(2, -1);
        if (lin != -1) {
            setLineno(lin);
        }

        int col = ap.getInt(3, -1);
        if (col != -1) {
            setLineno(col);
        }

    }

    public AsyncWith(PyObject items, PyObject body) {
        setItems(items);
        setBody(body);
    }

    public AsyncWith(Token token, java.util.List<withitem> items, java.util.List<stmt> body) {
        super(token);
        this.items = items;
        if (items == null) {
            this.items = new ArrayList<withitem>();
        }
        for(PythonTree t : this.items) {
            addChild(t);
        }
        this.body = body;
        if (body == null) {
            this.body = new ArrayList<stmt>();
        }
        for(PythonTree t : this.body) {
            addChild(t);
        }
    }

    public AsyncWith(Integer ttype, Token token, java.util.List<withitem> items,
    java.util.List<stmt> body) {
        super(ttype, token);
        this.items = items;
        if (items == null) {
            this.items = new ArrayList<withitem>();
        }
        for(PythonTree t : this.items) {
            addChild(t);
        }
        this.body = body;
        if (body == null) {
            this.body = new ArrayList<stmt>();
        }
        for(PythonTree t : this.body) {
            addChild(t);
        }
    }

    public AsyncWith(PythonTree tree, java.util.List<withitem> items, java.util.List<stmt> body) {
        super(tree);
        this.items = items;
        if (items == null) {
            this.items = new ArrayList<withitem>();
        }
        for(PythonTree t : this.items) {
            addChild(t);
        }
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
        return "AsyncWith";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("AsyncWith(");
        sb.append("items=");
        sb.append(dumpThis(items));
        sb.append(",");
        sb.append("body=");
        sb.append(dumpThis(body));
        sb.append(",");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        R x = null;
        if (visitor==null){
            new RuntimeException("Unexpected node: " + this);
        }else{
            visitor.preVisit(this);
            x = visitor.visitAsyncWith(this);
            visitor.postVisit(this);
        }
        return x;
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
        if (items != null) {
            for (PythonTree t : items) {
                if (t != null)
                    t.accept(visitor);
            }
        }
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
    public int getNodeType(){return ASYNCWITH;};

}
