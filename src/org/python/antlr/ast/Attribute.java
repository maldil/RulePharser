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

@ExposedType(name = "_ast.Attribute", base = expr.class)
public class Attribute extends expr implements Context {
public static final PyType TYPE = PyType.fromClass(Attribute.class);
    private expr value;
    public expr getInternalValue() {
        return value;
    }
    @ExposedGet(name = "value")
    public PyObject getValue() {
        return value;
    }
    @ExposedSet(name = "value")
    public void setValue(PyObject value) {
        this.value = AstAdapters.py2expr(value);
    }

    private String attr;
    public String getInternalAttr() {
        return attr;
    }
    @ExposedGet(name = "attr")
    public PyObject getAttr() {
        if (attr == null) return Py.None;
        return new PyUnicode(attr);
    }
    @ExposedSet(name = "attr")
    public void setAttr(PyObject attr) {
        this.attr = AstAdapters.py2identifier(attr);
    }

    private expr_contextType ctx;
    public expr_contextType getInternalCtx() {
        return ctx;
    }
    @ExposedGet(name = "ctx")
    public PyObject getCtx() {
        return AstAdapters.expr_context2py(ctx);
    }
    @ExposedSet(name = "ctx")
    public void setCtx(PyObject ctx) {
        this.ctx = AstAdapters.py2expr_context(ctx);
    }


    private final static PyUnicode[] fields =
    new PyUnicode[] {new PyUnicode("value"), new PyUnicode("attr"), new PyUnicode("ctx")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes =
    new PyUnicode[] {new PyUnicode("lineno"), new PyUnicode("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public Attribute(PyType subType) {
        super(subType);
    }
    public Attribute() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void Attribute___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("Attribute", args, keywords, new String[]
            {"value", "attr", "ctx", "lineno", "col_offset"}, 3, true);
        setValue(ap.getPyObject(0, Py.None));
        setAttr(ap.getPyObject(1, Py.None));
        setCtx(ap.getPyObject(2, Py.None));
        int lin = ap.getInt(3, -1);
        if (lin != -1) {
            setLineno(lin);
        }

        int col = ap.getInt(4, -1);
        if (col != -1) {
            setLineno(col);
        }

    }

    public Attribute(PyObject value, PyObject attr, PyObject ctx) {
        setValue(value);
        setAttr(attr);
        setCtx(ctx);
    }

    public Attribute(Token token, expr value, String attr, expr_contextType ctx) {
        super(token);
        this.value = value;
        addChild(value);
        this.attr = attr;
        this.ctx = ctx;
    }

    public Attribute(Integer ttype, Token token, expr value, String attr, expr_contextType ctx) {
        super(ttype, token);
        this.value = value;
        addChild(value);
        this.attr = attr;
        this.ctx = ctx;
    }

    public Attribute(PythonTree tree, expr value, String attr, expr_contextType ctx) {
        super(tree);
        this.value = value;
        addChild(value);
        this.attr = attr;
        this.ctx = ctx;
    }

//    @ExposedGet(name = "repr")
//    public String toString() {
//        return "Attribute";
//    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("Attribute(");
        sb.append("value=");
        sb.append(dumpThis(value));
        sb.append(",");
        sb.append("attr=");
        sb.append(dumpThis(attr));
        sb.append(",");
        sb.append("ctx=");
        sb.append(dumpThis(ctx));
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
            x = visitor.visitAttribute(this);
            visitor.postVisit(this);
        }
        return x;
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
        if (value != null)
            value.accept(visitor);
        if (hole != null)
            hole.accept(visitor);
        if (ahole  != null)
            ahole.accept(visitor);
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

    public void setContext(expr_contextType c) {
        this.ctx = c;
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

    // Support for indexer below

    private Name attrName;
    private Hole hole;
    private AlphHole ahole;
    public Name getInternalAttrName() {
        return attrName;
    }
    public Hole getInternalHole() {
            return hole;
    }
    public AlphHole getInternalAlphHole() {
        return ahole;
    }

    public void setInternalHole(Hole hole) {
        this.hole=hole;
    }


    public Attribute(Token token, expr value, Name attr, expr_contextType ctx) {
        super(token);
        this.value = value;
        addChild(value);
        this.attr = attr.getText();
        this.attrName = attr;
        this.ctx = ctx;
    }

    public Attribute(Integer ttype, Token token, expr value, Name attr, expr_contextType ctx) {
        super(ttype, token);
        this.value = value;
        addChild(value);
        this.attr = attr.getText();
        this.attrName = attr;
        this.ctx = ctx;
    }

    public Attribute(Token token, expr value, Hole attr, expr_contextType ctx) {
            super(token);
            this.value = value;
            addChild(value);
            this.hole = attr;
            this.ctx = ctx;
     }

     public Attribute(Token token, expr value, AlphHole attr, expr_contextType ctx) {
                 super(token);
                 this.value = value;
                 addChild(value);
                 this.ahole = attr;
                 this.ctx = ctx;
      }

    @Override
    public int getNodeType(){return ATTRIBUTE;};
    // End indexer support
}
