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

@ExposedType(name = "_ast.FormattedValue", base = expr.class)
public class FormattedValue extends expr {
public static final PyType TYPE = PyType.fromClass(FormattedValue.class);
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

    private Integer conversion;
    public Integer getInternalConversion() {
        return conversion;
    }
    @ExposedGet(name = "conversion")
    public PyObject getConversion() {
        return Py.newInteger(conversion);
    }
    @ExposedSet(name = "conversion")
    public void setConversion(PyObject conversion) {
        this.conversion = AstAdapters.py2int(conversion);
    }

    private expr format_spec;
    public expr getInternalFormat_spec() {
        return format_spec;
    }
    @ExposedGet(name = "format_spec")
    public PyObject getFormat_spec() {
        return format_spec;
    }
    @ExposedSet(name = "format_spec")
    public void setFormat_spec(PyObject format_spec) {
        this.format_spec = AstAdapters.py2expr(format_spec);
    }


    private final static PyUnicode[] fields =
    new PyUnicode[] {new PyUnicode("value"), new PyUnicode("conversion"), new
                      PyUnicode("format_spec")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes =
    new PyUnicode[] {new PyUnicode("lineno"), new PyUnicode("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public FormattedValue(PyType subType) {
        super(subType);
    }
    public FormattedValue() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void FormattedValue___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("FormattedValue", args, keywords, new String[]
            {"value", "conversion", "format_spec", "lineno", "col_offset"}, 3, true);
        setValue(ap.getPyObject(0, Py.None));
        setConversion(ap.getPyObject(1, Py.None));
        setFormat_spec(ap.getPyObject(2, Py.None));
        int lin = ap.getInt(3, -1);
        if (lin != -1) {
            setLineno(lin);
        }

        int col = ap.getInt(4, -1);
        if (col != -1) {
            setLineno(col);
        }

    }

    public FormattedValue(PyObject value, PyObject conversion, PyObject format_spec) {
        setValue(value);
        setConversion(conversion);
        setFormat_spec(format_spec);
    }

    public FormattedValue(Token token, expr value, Integer conversion, expr format_spec) {
        super(token);
        this.value = value;
        addChild(value);
        this.conversion = conversion;
        this.format_spec = format_spec;
        addChild(format_spec);
    }

    public FormattedValue(Integer ttype, Token token, expr value, Integer conversion, expr
    format_spec) {
        super(ttype, token);
        this.value = value;
        addChild(value);
        this.conversion = conversion;
        this.format_spec = format_spec;
        addChild(format_spec);
    }

    public FormattedValue(PythonTree tree, expr value, Integer conversion, expr format_spec) {
        super(tree);
        this.value = value;
        addChild(value);
        this.conversion = conversion;
        this.format_spec = format_spec;
        addChild(format_spec);
    }

    @ExposedGet(name = "repr")
    public String toString() {
        return "FormattedValue";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("FormattedValue(");
        sb.append("value=");
        sb.append(dumpThis(value));
        sb.append(",");
        sb.append("conversion=");
        sb.append(dumpThis(conversion));
        sb.append(",");
        sb.append("format_spec=");
        sb.append(dumpThis(format_spec));
        sb.append(",");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        return visitor.visitFormattedValue(this);
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
        if (value != null)
            value.accept(visitor);
        if (format_spec != null)
            format_spec.accept(visitor);
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
