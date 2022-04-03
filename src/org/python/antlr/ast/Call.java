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

@ExposedType(name = "_ast.Call", base = expr.class)
public class Call extends expr {
public static final PyType TYPE = PyType.fromClass(Call.class);
    private expr func;
    public expr getInternalFunc() {
        return func;
    }
    @ExposedGet(name = "func")
    public PyObject getFunc() {
        return func;
    }
    @ExposedSet(name = "func")
    public void setFunc(PyObject func) {
        this.func = AstAdapters.py2expr(func);
    }

    private java.util.List<expr> args;
    public java.util.List<expr> getInternalArgs() {
        return args;
    }
    @ExposedGet(name = "args")
    public PyObject getArgs() {
        return new AstList(args, AstAdapters.exprAdapter);
    }
    @ExposedSet(name = "args")
    public void setArgs(PyObject args) {
        this.args = AstAdapters.py2exprList(args);
    }

    private java.util.List<keyword> keywords;
    public java.util.List<keyword> getInternalKeywords() {
        return keywords;
    }
    @ExposedGet(name = "keywords")
    public PyObject getKeywords() {
        return new AstList(keywords, AstAdapters.keywordAdapter);
    }
    @ExposedSet(name = "keywords")
    public void setKeywords(PyObject keywords) {
        this.keywords = AstAdapters.py2keywordList(keywords);
    }


    private final static PyUnicode[] fields =
    new PyUnicode[] {new PyUnicode("func"), new PyUnicode("args"), new PyUnicode("keywords")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes =
    new PyUnicode[] {new PyUnicode("lineno"), new PyUnicode("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public Call(PyType subType) {
        super(subType);
    }
    public Call() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void Call___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("Call", args, keywords, new String[]
            {"func", "args", "keywords", "lineno", "col_offset"}, 3, true);
        setFunc(ap.getPyObject(0, Py.None));
        setArgs(ap.getPyObject(1, Py.None));
        setKeywords(ap.getPyObject(2, Py.None));
        int lin = ap.getInt(3, -1);
        if (lin != -1) {
            setLineno(lin);
        }

        int col = ap.getInt(4, -1);
        if (col != -1) {
            setLineno(col);
        }

    }

    public Call(PyObject func, PyObject args, PyObject keywords) {
        setFunc(func);
        setArgs(args);
        setKeywords(keywords);
    }

    public Call(Token token, expr func, java.util.List<expr> args, java.util.List<keyword>
    keywords) {
        super(token);
        this.func = func;
        addChild(func);
        this.args = args;
        if (args == null) {
            this.args = new ArrayList<expr>();
        }
        for(PythonTree t : this.args) {
            addChild(t);
        }
        this.keywords = keywords;
        if (keywords == null) {
            this.keywords = new ArrayList<keyword>();
        }
        for(PythonTree t : this.keywords) {
            addChild(t);
        }
    }

    public Call(Integer ttype, Token token, expr func, java.util.List<expr> args,
    java.util.List<keyword> keywords) {
        super(ttype, token);
        this.func = func;
        addChild(func);
        this.args = args;
        if (args == null) {
            this.args = new ArrayList<expr>();
        }
        for(PythonTree t : this.args) {
            addChild(t);
        }
        this.keywords = keywords;
        if (keywords == null) {
            this.keywords = new ArrayList<keyword>();
        }
        for(PythonTree t : this.keywords) {
            addChild(t);
        }
    }

    public Call(PythonTree tree, expr func, java.util.List<expr> args, java.util.List<keyword>
    keywords) {
        super(tree);
        this.func = func;
        addChild(func);
        this.args = args;
        if (args == null) {
            this.args = new ArrayList<expr>();
        }
        for(PythonTree t : this.args) {
            addChild(t);
        }
        this.keywords = keywords;
        if (keywords == null) {
            this.keywords = new ArrayList<keyword>();
        }
        for(PythonTree t : this.keywords) {
            addChild(t);
        }
    }

    @ExposedGet(name = "repr")
    public String toString() {
        return "Call";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("Call(");
        sb.append("func=");
        sb.append(dumpThis(func));
        sb.append(",");
        sb.append("args=");
        sb.append(dumpThis(args));
        sb.append(",");
        sb.append("keywords=");
        sb.append(dumpThis(keywords));
        sb.append(",");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        return visitor.visitCall(this);
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
        if (func != null)
            func.accept(visitor);
        if (args != null) {
            for (PythonTree t : args) {
                if (t != null)
                    t.accept(visitor);
            }
        }
        if (keywords != null) {
            for (PythonTree t : keywords) {
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

}
