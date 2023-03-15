package org.python.antlr.ast;

import org.python.antlr.PythonTree;
import org.python.antlr.base.expr;
import org.python.core.ASTMatcher;

public class ErrorExpr extends expr {

    public ErrorExpr(PythonTree tree) {
        super(tree);
    }

    public String toString() {
        return "ErrorExpr";
    }

    public String toStringTree() {
        return "ErrorExpr";
    }

    public int getLineno() {
        return getLine();
    }

    public int getCol_offset() {
        return getCharPositionInLine();
    }

    public <R> R accept(VisitorIF<R> visitor) {
        return null;
    }

    public void traverse(VisitorIF visitor) throws Exception {
        //no op.
    }

    @Override
    public boolean subtreeMatch(ASTMatcher matcher, Object other) {
        return matcher.match(this, other);
    }


}
